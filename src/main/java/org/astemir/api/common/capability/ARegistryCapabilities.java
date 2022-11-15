package org.astemir.api.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ARegistryCapabilities {

    private Map<Class<? extends ACapability>,CapabilityProperties> capabilitiesRegistry = new HashMap<>();
    private Map<Class<? extends ACapability>,Capability> linkedCapabilities = new HashMap<>();

    public <T extends ACapability> void registerCapability(ResourceLocation key, Class<T> capabilityClass, CapabilityTarget target){
        registerCapability(key,capabilityClass,target,false);
    }

    public <T extends ACapability> void registerCapability(ResourceLocation key, Class<T> capabilityClass, CapabilityTarget target,boolean sync){
        CapabilityManager.INSTANCE.register(capabilityClass, new ACapabilityStorage<>(),()->capabilityClass.newInstance());
        CapabilityProperties properties = CapabilityProperties.newProperties(key,target);
        if (sync){
            properties = properties.setSyncWithClient();
        }
        capabilitiesRegistry.put(capabilityClass,properties);
    }

    public void scanCapabilities(Class container){
        for (Field declaredField : container.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(CapabilityInject.class)){
                Class<? extends ACapability> capClass = (Class<? extends ACapability>) declaredField.getAnnotation(CapabilityInject.class).value();
                try {
                    linkedCapabilities.put(capClass, (Capability) declaredField.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        linkedCapabilities.forEach((type,capability)->{
            ACapability oldCap = CapabilityUtils.getCapability(capability,event.getOriginal());
            ACapability newCap = CapabilityUtils.getCapability(capability,event.getPlayer());
            if (oldCap != null && newCap != null) {
                newCap.cloneData(oldCap);
            }
        });
    }


    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        for (Map.Entry<Class<? extends ACapability>, CapabilityProperties> entry : capabilitiesRegistry.entrySet()) {
            if (entry.getValue().getTarget() == CapabilityTarget.PLAYER) {
                if (event.getObject() instanceof PlayerEntity) {
                    event.addCapability(entry.getValue().getKey(), newProvider(entry.getKey(), linkedCapabilities.get(entry.getKey())));
                } else {
                    continue;
                }
                continue;
            }else
            if (entry.getValue().getTarget() == CapabilityTarget.ENTITY){
                if (event.getObject() instanceof Entity){
                    event.addCapability(entry.getValue().getKey(), newProvider(entry.getKey(), linkedCapabilities.get(entry.getKey())));
                }
            }
        }
    }


    @SubscribeEvent
    public void onPlayerLog(PlayerEvent.PlayerLoggedInEvent e) {
        for (Map.Entry<Class<? extends ACapability>, CapabilityProperties> entry : capabilitiesRegistry.entrySet()) {
            if (entry.getValue().isSyncWithClient()) {
                Capability cap = linkedCapabilities.get(entry.getKey());
                if (e.getPlayer() instanceof ServerPlayerEntity) {
                    CapabilityUtils.sendCapabilityChangedPacket(cap, (ServerPlayerEntity) e.getPlayer());
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e){
        for (Map.Entry<Class<? extends ACapability>, CapabilityProperties> entry : capabilitiesRegistry.entrySet()) {
            if (entry.getValue().isSyncWithClient()) {
                Capability cap = linkedCapabilities.get(entry.getKey());
                if (e.getPlayer() instanceof ServerPlayerEntity) {
                    CapabilityUtils.sendCapabilityChangedPacket(cap, (ServerPlayerEntity) e.getPlayer());
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent e){
        for (Map.Entry<Class<? extends ACapability>, CapabilityProperties> entry : capabilitiesRegistry.entrySet()) {
            if (entry.getValue().isSyncWithClient()) {
                Capability cap = linkedCapabilities.get(entry.getKey());
                if (e.getPlayer() instanceof ServerPlayerEntity) {
                    CapabilityUtils.sendCapabilityChangedPacket(cap, (ServerPlayerEntity) e.getPlayer());
                }
            }
        }
    }

    public static <T extends ACapability> ICapabilitySerializable newProvider(Class<T> type, Capability<T> capability){
        return new ICapabilitySerializable<CompoundNBT>(){
            T instance;
            {
                try {
                    instance = type.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public CompoundNBT serializeNBT() {
                return instance.writeNBT();
            }

            @Override
            public void deserializeNBT(CompoundNBT nbt) {
                instance.readNBT(nbt);
            }

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == capability) {
                    return (LazyOptional<T>)LazyOptional.of(()-> instance);
                }
                return LazyOptional.empty();
            }
        };
    }

    public static <T extends ACapability> ICapabilitySerializable newProvider(T instance, Capability<T> capability){
        return new ICapabilitySerializable<CompoundNBT>(){

            @Override
            public CompoundNBT serializeNBT() {
                return instance.writeNBT();
            }

            @Override
            public void deserializeNBT(CompoundNBT nbt) {
                instance.readNBT(nbt);
            }

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == capability) {
                    return (LazyOptional<T>)LazyOptional.of(()-> instance);
                }
                return LazyOptional.empty();
            }
        };
    }

    private static class CapabilityProperties{

        private CapabilityTarget target;
        private boolean syncWithClient = false;
        private ResourceLocation key;

        public static CapabilityProperties newProperties(ResourceLocation loc,CapabilityTarget target){
            CapabilityProperties props = new CapabilityProperties();
            props.key = loc;
            props.target = target;
            return props;
        }

        public CapabilityProperties setSyncWithClient() {
            this.syncWithClient = true;
            return this;
        }

        public boolean isSyncWithClient() {
            return syncWithClient;
        }

        public CapabilityTarget getTarget() {
            return target;
        }

        public ResourceLocation getKey() {
            return key;
        }
    }

    public Map<Class<? extends ACapability>, CapabilityProperties> getCapabilitiesRegistry() {
        return capabilitiesRegistry;
    }

    public Map<Class<? extends ACapability>, Capability> getLinkedCapabilities() {
        return linkedCapabilities;
    }
}

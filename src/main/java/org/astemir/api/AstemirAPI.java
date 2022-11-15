package org.astemir.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.GenericEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import org.astemir.api.client.event.ClientRenderEventHandler;
import org.astemir.api.common.TaskManager;
import org.astemir.api.common.block.ARegistryBlocks;
import org.astemir.api.common.event.EntityEventHandler;
import org.astemir.api.common.event.ItemEventHandler;
import org.astemir.api.common.event.ServerTickEventHandler;
import org.astemir.api.network.*;
import org.astemir.api.common.capability.ACapabilities;
import org.astemir.api.common.capability.ARegistryCapabilities;
import org.astemir.api.common.capability.CapabilityTarget;
import org.astemir.api.common.capability.player.CapabilityPlayerData;
import org.astemir.api.common.event.AnimationEventHandler;
import org.astemir.api.common.item.ARegistryItems;
import org.astemir.forestcraft.client.event.ClientEvents;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class AstemirAPI {


    public static ClientAPI CLIENT;
    public static PacketHandler PACKET_HANDLER;
    public static TaskManager TASK_MANAGER;
    private static ARegistryCapabilities CAPABILITIES_REGISTRY;
    private String modId;

    public AstemirAPI(String modId) {
        this.modId = modId;
        registerGenericEvent(Block.class, ARegistryBlocks::registerBlocks);
        registerGenericEvent(Item.class, ARegistryBlocks::registerItems);
        registerGenericEvent(Item.class, ARegistryItems::registerItems);
        registerEvent(this::initializeClient);
        registerEvent(this::initializeCommon);
        CAPABILITIES_REGISTRY = new ARegistryCapabilities();
        TASK_MANAGER = new TaskManager();
        registerEvent(CAPABILITIES_REGISTRY);
        registerEvent(AnimationEventHandler.class);
        registerEvent(ItemEventHandler.class);
        registerEvent(EntityEventHandler.class);
        registerEvent(ServerTickEventHandler.class);
    }

    @SubscribeEvent
    public void initializeCommon(FMLCommonSetupEvent event){
        PACKET_HANDLER = new PacketHandler();
        CAPABILITIES_REGISTRY.registerCapability(new ResourceLocation(modId,"player_data"), CapabilityPlayerData.class, CapabilityTarget.PLAYER);
        CAPABILITIES_REGISTRY.scanCapabilities(ACapabilities.class);
        initializePacketHandler();
        onCommonSetup(event);
    }

    @SubscribeEvent
    public void initializeClient(FMLClientSetupEvent event){
        CLIENT = new ClientAPI();
        CLIENT.init();
        registerEvent(ClientRenderEventHandler.class);
        onClientSetup(event);
    }

    private void initializePacketHandler(){
        PACKET_HANDLER.startChannel(new ResourceLocation(modId,"network_channel"),"1.3");
        PACKET_HANDLER.registerMessage(100, EntityAnimationMessage.class, EntityAnimationMessage::encode, EntityAnimationMessage::decode, new EntityAnimationMessage.Handler());
        PACKET_HANDLER.registerMessage(101, TileEntityAnimationMessage.class, TileEntityAnimationMessage::encode, TileEntityAnimationMessage::decode, new TileEntityAnimationMessage.Handler());
        PACKET_HANDLER.registerMessage(102, BossMusicMessage.class, BossMusicMessage::encode,BossMusicMessage::decode,BossMusicMessage.Handler::checkConditions, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        PACKET_HANDLER.registerMessage(103, CapabilitySyncMessage.class,CapabilitySyncMessage::encode,CapabilitySyncMessage::decode,CapabilitySyncMessage.Handler::checkConditions,Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        PACKET_HANDLER.registerMessage(104, BossbarSyncMessage.class,BossbarSyncMessage::encode,BossbarSyncMessage::decode,BossbarSyncMessage.Handler::checkConditions,Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        PACKET_HANDLER.registerMessage(105, LeftClickMessage.class,LeftClickMessage::encode,LeftClickMessage::decode,LeftClickMessage.Handler::handle,Optional.of(NetworkDirection.PLAY_TO_SERVER));
        PACKET_HANDLER.registerMessage(106, AnimationsResetMessage.class,AnimationsResetMessage::encode,AnimationsResetMessage::decode,AnimationsResetMessage.Handler::onMessageReceived);
        PACKET_HANDLER.registerMessage(107, TileEntityAnimationResetMessage.class, TileEntityAnimationResetMessage::encode, TileEntityAnimationResetMessage::decode, TileEntityAnimationResetMessage.Handler::onMessageReceived);
        registerPackets(PACKET_HANDLER);
    }

    public abstract void onCommonSetup(FMLCommonSetupEvent event);

    public abstract void onClientSetup(FMLClientSetupEvent event);

    public void registerPackets(PacketHandler handler){};

    public static ARegistryCapabilities getCapabilityRegistry() {
        return CAPABILITIES_REGISTRY;
    }

    public static PacketHandler getPacketHandler() {
        return PACKET_HANDLER;
    }

    //Events
    public <T extends Event> void registerEvent(Consumer<T> event){ FMLJavaModLoadingContext.get().getModEventBus().addListener(event); }

    public <T extends GenericEvent<? extends F>, F> void registerGenericEvent(Class<F> className, Consumer<T> event){ FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(className,event); }

    public void registerEvent(Class className){
        MinecraftForge.EVENT_BUS.register(className);
    }

    public void registerEvent(Object event){
        MinecraftForge.EVENT_BUS.register(event);
    }
    //
}

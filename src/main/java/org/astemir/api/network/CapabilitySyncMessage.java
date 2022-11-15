package org.astemir.api.network;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.capability.ACapability;
import org.astemir.api.common.capability.CapabilityUtils;

import java.util.function.Supplier;


public class CapabilitySyncMessage {

    private int entityId;
    private String capabilityClass;
    private CompoundNBT data;

    public CapabilitySyncMessage(int entityId,String className, CompoundNBT data) {
        this.entityId = entityId;
        this.capabilityClass = className;
        this.data = data;
    }

    public static CapabilitySyncMessage decode(PacketBuffer buffer) {
        CapabilitySyncMessage message = new CapabilitySyncMessage(buffer.readInt(),buffer.readString(),buffer.readCompoundTag());
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeString(this.capabilityClass);
        buffer.writeCompoundTag(this.data);
    }

    public static class Handler {

        public static void checkConditions(final CapabilitySyncMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
            NetworkEvent.Context context = ctxSupplier.get();
            LogicalSide side = context.getDirection().getReceptionSide();
            context.setPacketHandled(true);
            if (!side.isClient()) {
                return;
            }
            ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
            if (playerEntity == null) {
                return;
            }
            context.enqueueWork(()->processMessage(message,playerEntity));
        }


        private static void processMessage(CapabilitySyncMessage message, ClientPlayerEntity player) {
            try {
                Entity entity = player.world.getEntityByID(message.entityId);
                if (entity != null) {
                    PlayerEntity playerEntity = (PlayerEntity) entity;
                    Class<? extends ACapability> capClass = (Class<? extends ACapability>) Class.forName(message.capabilityClass);
                    Capability cap = AstemirAPI.getCapabilityRegistry().getLinkedCapabilities().get(capClass);
                    ACapability aCap = CapabilityUtils.getCapability(cap, playerEntity);
                    if (aCap != null) {
                        aCap.readNBT(message.data);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}

package org.astemir.api.network;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.client.animator.IAnimated;

import java.util.function.Supplier;

public class AnimationsResetMessage {


    private int entityId = 0;


    public AnimationsResetMessage(int entityId) {
        this.entityId = entityId;
    }

    public static AnimationsResetMessage decode(PacketBuffer buffer) {
        return new AnimationsResetMessage(buffer.readInt());
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(entityId);
    }


    public static class Handler {


        public static void onMessageReceived(AnimationsResetMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
            NetworkEvent.Context context = ctxSupplier.get();
            LogicalSide side = context.getDirection().getReceptionSide();
            context.setPacketHandled(true);

            if(!side.isServer()) {
                return;
            }

            final ServerPlayerEntity playerEntity = context.getSender();
            if(playerEntity == null) {
                return;
            }

            context.enqueueWork(() -> processMessage(message, playerEntity));
        }


        private static void processMessage(AnimationsResetMessage message, ServerPlayerEntity playerEntity) {
            if (playerEntity != null) {
                if (playerEntity.world != null) {
                    Entity entity = playerEntity.world.getEntityByID(message.entityId);
                    if (entity != null) {
                        if (entity instanceof IAnimated) {
                            ((IAnimated) entity).getFactory().stopAllAnimations();
                        }
                    }
                }
            }
        }
    }
}

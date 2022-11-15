package org.astemir.api.network;


import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.client.animator.IAnimated;

import java.util.function.Supplier;

public class TileEntityAnimationResetMessage {


    private BlockPos pos;


    public TileEntityAnimationResetMessage(BlockPos pos) {
        this.pos = pos;
    }

    public static TileEntityAnimationResetMessage decode(PacketBuffer buffer) {
        return new TileEntityAnimationResetMessage(buffer.readBlockPos());
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeBlockPos(pos);
    }


    public static class Handler {


        public static void onMessageReceived(TileEntityAnimationResetMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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


        private static void processMessage(TileEntityAnimationResetMessage message, ServerPlayerEntity playerEntity) {
            if (playerEntity != null) {
                if (playerEntity.world != null) {
                    TileEntity entity = playerEntity.world.getTileEntity(message.pos);
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

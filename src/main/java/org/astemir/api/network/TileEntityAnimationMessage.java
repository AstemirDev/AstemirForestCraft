package org.astemir.api.network;


import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.client.animator.IAnimated;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TileEntityAnimationMessage {

    private int type;
    private int id;
    private BlockPos pos;

    public TileEntityAnimationMessage() {
    }

    public TileEntityAnimationMessage(int type, BlockPos pos, int id) {
        this.type = type;
        this.pos = pos;
        this.id = id;
    }


    public static void encode(final TileEntityAnimationMessage message, final PacketBuffer buf) {
        buf.writeVarInt(message.type);
        buf.writeBlockPos(message.pos);
        buf.writeVarInt(message.id);
    }

    public static TileEntityAnimationMessage decode(final PacketBuffer buf) {
        final TileEntityAnimationMessage message = new TileEntityAnimationMessage();
        message.type = buf.readVarInt();
        message.pos = buf.readBlockPos();
        message.id = buf.readVarInt();
        return message;
    }

    public static class Handler implements BiConsumer<TileEntityAnimationMessage, Supplier<NetworkEvent.Context>> {
        @Override
        public void accept(final TileEntityAnimationMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
            final NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                IAnimated entity = (IAnimated) Minecraft.getInstance().world.getTileEntity(message.pos);
                if (entity != null) {
                    if (message.type == 0) {
                        entity.getFactory().playAnimation(message.id);
                    }else
                    if (message.type == 1) {
                        entity.getFactory().stopAnimation(message.id);
                    }
                }
            });
            context.setPacketHandled(true);
        }
    }

}

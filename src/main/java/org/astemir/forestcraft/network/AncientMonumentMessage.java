package org.astemir.forestcraft.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientMonument;

import java.util.function.Supplier;

public class AncientMonumentMessage {

    private ItemStack item;
    private long blockPos;

    public AncientMonumentMessage(long blockPos, ItemStack item) {
        this.blockPos = blockPos;
        this.item = item;
    }


    public static AncientMonumentMessage read(PacketBuffer buf) {
        return new AncientMonumentMessage(buf.readLong(), buf.readItemStack());
    }

    public static void write(AncientMonumentMessage message, PacketBuffer buf) {
        buf.writeLong(message.blockPos);
        buf.writeItemStack(message.item);
    }

    public static class Handler {

        public static void handle(AncientMonumentMessage message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            PlayerEntity player = context.get().getSender();
            if (player != null) {
                if (player.world != null) {
                    BlockPos pos = BlockPos.fromLong(message.blockPos);
                    if (player.world.getTileEntity(pos) != null) {
                        if (player.world.getTileEntity(pos) instanceof TileEntityAncientMonument) {
                            TileEntityAncientMonument monument = (TileEntityAncientMonument) player.world.getTileEntity(pos);
                            monument.displayItem = message.item;
                        }
                    }
                }
            }
        }
    }

}

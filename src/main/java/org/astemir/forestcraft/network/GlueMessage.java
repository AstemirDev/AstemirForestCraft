package org.astemir.forestcraft.network;


import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.registries.FCItems;

import java.util.function.Supplier;

public class GlueMessage {

    public ItemStack glue;

    public ItemStack repaired;

    public GlueMessage() {
        this(ItemStack.EMPTY,ItemStack.EMPTY);
    }

    public GlueMessage(ItemStack bundle, ItemStack repaired) {
        this.glue = bundle;
        this.repaired = repaired;
    }

    public static GlueMessage decode(PacketBuffer buffer) {
        GlueMessage message = new GlueMessage();
        message.glue = buffer.readItemStack();
        message.repaired = buffer.readItemStack();
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeItemStack(this.glue);
        buffer.writeItemStack(this.repaired);
    }


    public static class Handler {


        public static void onMessageReceived(GlueMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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



        public static void dropDiamond(ItemStack item, World world, BlockPos pos){
            WorldUtils.dropItem(world,pos, Items.DIAMOND.getDefaultInstance());
            item.shrink(1);
        }

        private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
            return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
        }


        public static int getSlotFor(PlayerInventory inventory, ItemStack stack) {
            for(int i = 0; i < inventory.mainInventory.size(); ++i) {
                if (!inventory.mainInventory.get(i).isEmpty() && stackEqualExact(stack, inventory.mainInventory.get(i))) {
                    return i;
                }
            }
            return -1;
        }


        private static void processMessage(GlueMessage message, ServerPlayerEntity playerEntity) {
            ItemStack stack = playerEntity.inventory.getStackInSlot(getSlotFor(playerEntity.inventory,message.repaired));
            if (stack.getItem().equals(FCItems.BROKEN_DIAMOND)) {
                dropDiamond(stack,playerEntity.world,playerEntity.getPosition());
            }else{
                stack.setDamage(Math.max(0,stack.getDamage()-8));
            }
            playerEntity.inventory.getItemStack().shrink(1);
        }
    }
}

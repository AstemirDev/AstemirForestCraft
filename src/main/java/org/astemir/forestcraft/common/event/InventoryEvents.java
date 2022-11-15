package org.astemir.forestcraft.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.network.GlueMessage;
import org.astemir.forestcraft.registries.FCSounds;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InventoryEvents {


    @SubscribeEvent
    public static void onInventoryClick(GuiScreenEvent.MouseReleasedEvent e){
        if(!e.isCanceled() && e.getGui() instanceof ContainerScreen<?>) {
            ContainerScreen<?> containerScreen = (ContainerScreen<?>) e.getGui();
            Slot slot = containerScreen.getSlotUnderMouse();
            if (slot != null && !(slot instanceof CraftingResultSlot) && !(slot.inventory instanceof CraftingInventory)) {
                PlayerEntity player = Minecraft.getInstance().player;
                if(player != null) {
                    ItemStack draggedItemStack = player.inventory.getItemStack();
                    ItemStack slotStack = slot.getStack();
                    Container container = containerScreen.getContainer();
                    if(slot.canTakeStack(player) && slot.isEnabled() && container.canMergeSlot(draggedItemStack, slot) && slot.isItemValid(draggedItemStack) && slot.getHasStack() && e.getButton() == 1){
                        if (draggedItemStack.getItem().equals(FCItems.GLUE)){
                            if (slotStack.isRepairable() && slotStack.isDamaged() || slotStack.getItem().equals(FCItems.BROKEN_DIAMOND)){
                                ForestCraft.PACKET_HANDLER.getNetwork().sendToServer(new GlueMessage(draggedItemStack,slotStack));
                                player.inventory.getItemStack().shrink(1);
                                player.playSound(FCSounds.CREAM_USE.get(), 1, RandomUtils.randomFloat(0.9f, 1.1f));
                                e.setCanceled(true);
                                e.setResult(Event.Result.DENY);
                            }
                        }
                    }
                }
            }
        }
    }

}

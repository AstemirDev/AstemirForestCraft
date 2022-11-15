package org.astemir.forestcraft.client.container;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.SlotItemHandler;
import org.astemir.forestcraft.common.items.misc.ItemAlchemicalBag;

public class ContainerAlchemicalBag extends Container {


    public static ContainerAlchemicalBag createContainerServerSide(int windowID, PlayerInventory playerInventory, ItemAlchemicalBag.ItemStackHandlerAlchemicalBag bagContents, ItemStack AlchemicalBag) {
        return new ContainerAlchemicalBag(windowID, playerInventory, bagContents, AlchemicalBag);
    }


    public static ContainerAlchemicalBag createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        int numberOfAlchemicalSlots = extraData.readInt();

        try {
            ItemAlchemicalBag.ItemStackHandlerAlchemicalBag itemStackHandlerAlchemicalBag = new ItemAlchemicalBag.ItemStackHandlerAlchemicalBag(numberOfAlchemicalSlots);

            return new ContainerAlchemicalBag(windowID, playerInventory, itemStackHandlerAlchemicalBag, ItemStack.EMPTY);
        } catch (IllegalArgumentException iae) {
        }
        return null;
    }

    private final ItemAlchemicalBag.ItemStackHandlerAlchemicalBag itemStackHandlerAlchemicalBag;
    private final ItemStack itemStackBeingHeld;


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int BAG_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int MAX_EXPECTED_BAG_SLOT_COUNT = 16;

    public static final int BAG_INVENTORY_YPOS = 26;  // the ContainerScreenAlchemicalBag needs to know these so it can tell where to draw the Titles
    public static final int PLAYER_INVENTORY_YPOS = 84;

    private ContainerAlchemicalBag(int windowId, PlayerInventory playerInv,
                               ItemAlchemicalBag.ItemStackHandlerAlchemicalBag itemStackHandlerAlchemicalBag,
                               ItemStack itemStackBeingHeld) {
        super(FCContainers.ALCHEMICAL_BAG_CONTAINER.get(), windowId);
        this.itemStackHandlerAlchemicalBag = itemStackHandlerAlchemicalBag;
        this.itemStackBeingHeld = itemStackBeingHeld;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 142;
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlot(new Slot(playerInv, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(playerInv, slotNumber, xpos, ypos));
            }
        }

        int bagSlotCount = itemStackHandlerAlchemicalBag.getSlots();
        if (bagSlotCount < 1 || bagSlotCount > MAX_EXPECTED_BAG_SLOT_COUNT) {
            bagSlotCount = MathHelper.clamp(bagSlotCount, 1, MAX_EXPECTED_BAG_SLOT_COUNT);
        }

        final int BAG_SLOTS_PER_ROW = 8;
        final int BAG_INVENTORY_XPOS = 17;
        for (int bagSlot = 0; bagSlot < bagSlotCount; ++bagSlot) {
            int slotNumber = bagSlot;
            int bagRow = bagSlot / BAG_SLOTS_PER_ROW;
            int bagCol = bagSlot % BAG_SLOTS_PER_ROW;
            int xpos = BAG_INVENTORY_XPOS + SLOT_X_SPACING * bagCol;
            int ypos = BAG_INVENTORY_YPOS + SLOT_Y_SPACING * bagRow;
            addSlot(new SlotItemHandler(itemStackHandlerAlchemicalBag, slotNumber, xpos, ypos));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {

        ItemStack main = player.getHeldItemMainhand();
        ItemStack off = player.getHeldItemOffhand();
        return (!main.isEmpty() && main == itemStackBeingHeld) ||
                (!off.isEmpty() && off == itemStackBeingHeld);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int sourceSlotIndex) {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();
        final int BAG_SLOT_COUNT = itemStackHandlerAlchemicalBag.getSlots();

        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!mergeItemStack(sourceStack, BAG_INVENTORY_FIRST_SLOT_INDEX, BAG_INVENTORY_FIRST_SLOT_INDEX + BAG_SLOT_COUNT, false)){
                return ItemStack.EMPTY;
            }
        } else if (sourceSlotIndex >= BAG_INVENTORY_FIRST_SLOT_INDEX && sourceSlotIndex < BAG_INVENTORY_FIRST_SLOT_INDEX + BAG_SLOT_COUNT) {
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.putStack(ItemStack.EMPTY);
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }


    @Override
    public void detectAndSendChanges() {
        if (itemStackHandlerAlchemicalBag.isDirty()) {
            CompoundNBT nbt = itemStackBeingHeld.getOrCreateTag();
            int dirtyCounter = nbt.getInt("dirtyCounter");
            nbt.putInt("dirtyCounter", dirtyCounter + 1);
            itemStackBeingHeld.setTag(nbt);
        }
        super.detectAndSendChanges();
    }




}
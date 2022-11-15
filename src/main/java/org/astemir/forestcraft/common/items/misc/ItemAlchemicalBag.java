package org.astemir.forestcraft.common.items.misc;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.client.container.ContainerAlchemicalBag;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;

import javax.annotation.Nonnull;


public class ItemAlchemicalBag extends AItem {

    private final String BASE_NBT_TAG = "base";
    private final String CAPABILITY_NBT_TAG = "cap";

    public ItemAlchemicalBag() {
        super("forestcraft:alchemical_bag");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            INamedContainerProvider containerProviderPotionBag = new ContainerProviderAlchemicalBag(this, stack);
            final int NUMBER_OF_POTION_SLOTS = 16;
            NetworkHooks.openGui((ServerPlayerEntity) player,
                    containerProviderPotionBag,
                    (packetBuffer)->{packetBuffer.writeInt(NUMBER_OF_POTION_SLOTS);});
        }
        return ActionResult.resultSuccess(stack);
    }


    private static ItemStackHandlerAlchemicalBag getItemStackHandlerAlchemicalBag(ItemStack itemStack) {
        IItemHandler bag = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (bag == null || !(bag instanceof ItemStackHandlerAlchemicalBag)) {
            return new ItemStackHandlerAlchemicalBag(1);
        }
        return (ItemStackHandlerAlchemicalBag)bag;
    }

    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT baseTag = stack.getTag();
        ItemStackHandlerAlchemicalBag bagHandler = getItemStackHandlerAlchemicalBag(stack);
        CompoundNBT capabilityTag = bagHandler.serializeNBT();
        CompoundNBT combinedTag = new CompoundNBT();
        if (baseTag != null) {
            combinedTag.put(BASE_NBT_TAG, baseTag);
        }
        if (capabilityTag != null) {
            combinedTag.put(CAPABILITY_NBT_TAG, capabilityTag);
        }
        return combinedTag;
    }


    @Override
    public void readShareTag(ItemStack stack, CompoundNBT nbt) {
        if (nbt == null) {
            stack.setTag(null);
            return;
        }
        CompoundNBT baseTag = nbt.getCompound(BASE_NBT_TAG);
        CompoundNBT capabilityTag = nbt.getCompound(CAPABILITY_NBT_TAG);
        stack.setTag(baseTag);
        ItemStackHandlerAlchemicalBag bagHandler = getItemStackHandlerAlchemicalBag(stack);
        bagHandler.deserializeNBT(capabilityTag);
    }

    public static float getFullnessPropertyOverride(ItemStack itemStack, World world, LivingEntity livingEntity) {
        ItemStackHandlerAlchemicalBag bag = getItemStackHandlerAlchemicalBag(itemStack);
        float fractionEmpty = bag.getNumberOfEmptySlots() / (float)bag.getSlots();
        return 1.0F - fractionEmpty;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        return new CapabilityProviderAlchemicalBag();
    }

    @Override
    public ActionResultType onUseFirst(ItemStack stack, ItemUseContext ctx) {
        World world = ctx.getWorld();
        if (world.isRemote()) return ActionResultType.PASS;
        BlockPos pos = ctx.getPos();
        Direction side = ctx.getFace();
        ItemStack itemStack = ctx.getItem();
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity == null) return ActionResultType.PASS;
        if (world.isRemote()) return ActionResultType.SUCCESS; // always succeed on client side
        IItemHandler tileInventory;
        LazyOptional<IItemHandler> capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
        if (capability.isPresent()) {
            tileInventory = capability.orElseThrow(AssertionError::new);
        } else if (tileEntity instanceof IInventory) {
            tileInventory = new InvWrapper((IInventory)tileEntity);
        } else {
            return ActionResultType.FAIL;
        }
        ItemStackHandlerAlchemicalBag itemStackHandlerAlchemicalBag =  getItemStackHandlerAlchemicalBag(itemStack);
        for (int i = 0; i < itemStackHandlerAlchemicalBag.getSlots(); i++) {
            ItemStack potion = itemStackHandlerAlchemicalBag.getStackInSlot(i);
            ItemStack potionsWhichDidNotFit = ItemHandlerHelper.insertItemStacked(tileInventory, potion, false);
            itemStackHandlerAlchemicalBag.setStackInSlot(i, potionsWhichDidNotFit);
        }
        tileEntity.markDirty();
        CompoundNBT nbt = itemStack.getOrCreateTag();
        int dirtyCounter = nbt.getInt("dirtyCounter");
        nbt.putInt("dirtyCounter", dirtyCounter + 1);
        itemStack.setTag(nbt);

        return ActionResultType.SUCCESS;
    }



    public static class ItemStackHandlerAlchemicalBag extends ItemStackHandler {


        public ItemStackHandlerAlchemicalBag(int numberOfSlots) {
            super(MathHelper.clamp(numberOfSlots, 1, 16));
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if (stack.isEmpty()) return false;
            Item item = stack.getItem();
            return item instanceof PotionItem;
        }

        public int getNumberOfEmptySlots() {
            final int NUMBER_OF_SLOTS = getSlots();

            int emptySlotCount = 0;
            for (int i = 0; i < NUMBER_OF_SLOTS; ++i) {
                if (getStackInSlot(i) == ItemStack.EMPTY) {
                    ++emptySlotCount;
                }
            }
            return emptySlotCount;
        }

        public boolean isDirty() {
            boolean currentState = isDirty;
            isDirty = false;
            return currentState;
        }

        @Override
        protected void onContentsChanged(int slot) {
            isDirty = true;
        }

        private boolean isDirty = true;

    }



    private static class ContainerProviderAlchemicalBag implements INamedContainerProvider {

        private final ItemAlchemicalBag itemAlchemicalBag;
        private final ItemStack itemStackAlchemicalBag;

        public ContainerProviderAlchemicalBag(ItemAlchemicalBag itemBag, ItemStack itemStackBag) {
            this.itemStackAlchemicalBag = itemStackBag;
            this.itemAlchemicalBag = itemBag;
        }

        @Override
        public ITextComponent getDisplayName() {
            return itemStackAlchemicalBag.getDisplayName();
        }


        @Override
        public ContainerAlchemicalBag createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            ContainerAlchemicalBag newContainerServerSide = ContainerAlchemicalBag.createContainerServerSide(windowID, playerInventory, getItemStackHandlerAlchemicalBag(itemStackAlchemicalBag), itemStackAlchemicalBag);
            return newContainerServerSide;
        }

    }

    public class CapabilityProviderAlchemicalBag implements ICapabilitySerializable<INBT> {

        private final Direction NO_SPECIFIC_SIDE = null;

        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing) {
            if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == capability) return (LazyOptional<T>)(lazyInitialisionSupplier);
            return LazyOptional.empty();
        }

        @Override
        public INBT serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(getCachedInventory(), NO_SPECIFIC_SIDE);
        }

        @Override
        public void deserializeNBT(INBT nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(getCachedInventory(), NO_SPECIFIC_SIDE, nbt);
        }

        private ItemStackHandlerAlchemicalBag getCachedInventory() {
            if (itemStackHandlerAlchemicalBag == null) {
                itemStackHandlerAlchemicalBag = new ItemStackHandlerAlchemicalBag(MAX_NUMBER_OF_POTIONS_IN_BAG);
            }
            return itemStackHandlerAlchemicalBag;
        }

        private static final int MAX_NUMBER_OF_POTIONS_IN_BAG = 16;

        private ItemStackHandlerAlchemicalBag itemStackHandlerAlchemicalBag;

        private final LazyOptional<IItemHandler> lazyInitialisionSupplier = LazyOptional.of(this::getCachedInventory);
    }





}

package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.common.item.AItem;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCSounds;
import java.util.function.Supplier;

public class FCWashable extends AItem {

    private Supplier<Item> itemSupplier;
    private int itemMinCount = 1;
    private int itemMaxCount = 1;
    private int washDuration = 20;
    private ItemDropPool compactLootTable;

    public FCWashable(String registryName, Supplier<Item> item, int washDuration, int minCount, int maxCount) {
        super(registryName);
        this.itemSupplier = item;
        this.itemMaxCount = maxCount;
        this.washDuration = washDuration;
        lore(TextUtils.translate(FCStrings.WASHABLE,TextFormatting.GRAY));
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
    }

    public FCWashable(String registryName, Supplier<Item> item, int washDuration, int maxCount) {
        this(registryName,item,washDuration,1,maxCount);
    }

    public FCWashable(String registryName, Supplier<Item> item, int washDuration) {
        this(registryName,item,washDuration,1,1);
    }

    public FCWashable(String registryName, ItemDropPool lootTable, int washDuration) {
        super(registryName);
        this.compactLootTable = lootTable;
        this.washDuration = washDuration;
        lore(TextUtils.translate(FCStrings.WASHABLE,TextFormatting.GRAY));
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
    }



    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (!PlayerUtils.hasCooldown((PlayerEntity)livingEntityIn,stack.getItem())) {
            BlockPos hoveredBlock = EntityUtils.rayTraceFluid(worldIn,livingEntityIn,3).getPos();
            if (worldIn.getBlockState(hoveredBlock).getBlock().equals(Blocks.WATER)) {
                if (count % 5 == 0) {
                    for (int i = 0; i < 5; i++) {
                        worldIn.addParticle(ParticleTypes.FISHING, hoveredBlock.getX() + 0.5f + RandomUtils.randomFloat(-0.5f, 0.5f), hoveredBlock.getY() + 0.5f + RandomUtils.randomFloat(-0.25f, 0.5f), hoveredBlock.getZ() + 0.5f + RandomUtils.randomFloat(-0.5f, 0.5f), 0.01,0.01,0.01);
                    }
                }
            }else{
                livingEntityIn.stopActiveHand();
            }
        }
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote) {
            PlayerEntity playerEntity = (PlayerEntity)entityLiving;
            PlayerUtils.cooldownItem(playerEntity,stack,10);
            stack.shrink(1);
            ItemStack res = null;
            if (itemSupplier != null) {
                res = new ItemStack(itemSupplier.get());
            }
            if (compactLootTable != null){
                res = compactLootTable.drop();
            }
            if (itemMaxCount > 1) {
                res.setCount(RandomUtils.randomInt(itemMinCount, itemMaxCount));
            }
            PlayerUtils.giveItem((PlayerEntity)entityLiving, res);
            PlayerUtils.forceItemCooldown(playerEntity,res,20);
        }
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        BlockPos hoveredBlock = EntityUtils.rayTraceFluid(worldIn,playerIn,3).getPos();
        if (!worldIn.getBlockState(hoveredBlock).isAir()) {
            if (!PlayerUtils.hasCooldown(playerIn,itemstack)) {
                if (worldIn.getBlockState(hoveredBlock).getBlock().equals(Blocks.WATER)){
                    playerIn.setActiveHand(handIn);
                    return ActionResult.resultConsume(itemstack);
                }
            }
        }
        return ActionResult.resultFail(itemstack);
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return FCSounds.SIEVE_USE.get();
    }

    @Override
    public SoundEvent getEatSound() {
        return FCSounds.SIEVE_USE.get();
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return washDuration;
    }
}

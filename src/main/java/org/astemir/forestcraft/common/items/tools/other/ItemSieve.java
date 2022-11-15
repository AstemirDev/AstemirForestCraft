package org.astemir.forestcraft.common.items.tools.other;

import net.minecraft.block.Block;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;


public class ItemSieve extends AItem {



    public ItemSieve() {
        super("forestcraft:gold_panning_sieve");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(128);
        lore(new TranslationTextComponent(FCStrings.SIEVE).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 80;
    }

    public boolean isWater(Block block){
        return (block.equals(Blocks.WATER));
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (!((PlayerEntity)livingEntityIn).getCooldownTracker().hasCooldown(stack.getItem())) {
            BlockPos hoveredBlock = EntityUtils.rayTrace(worldIn,((PlayerEntity)livingEntityIn),3).getPos();
            if (isWater(worldIn.getBlockState(hoveredBlock).getBlock())) {
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
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity livingEntityIn) {
        if (!worldIn.isRemote) {
            PlayerUtils.cooldownItem((PlayerEntity)livingEntityIn,stack.getItem(),20);
            PlayerUtils.damageItem((PlayerEntity)livingEntityIn,stack,livingEntityIn.getActiveHand(),1);
            if (RandomUtils.doWithChance(80)){
                if (RandomUtils.doWithChance(50)) {
                    PlayerUtils.giveItem((PlayerEntity)livingEntityIn, FCItems.SAND_PILE);
                }else{
                    PlayerUtils.giveItem((PlayerEntity)livingEntityIn, FCItems.SAND_PILE_WITH_GOLD);
                }
            }
        }
        return super.onUseFinish(stack, worldIn, livingEntityIn);
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
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        BlockPos hoveredBlock = EntityUtils.rayTraceFluid(worldIn,playerIn,3).getPos();
        if (!worldIn.getBlockState(hoveredBlock).isAir()) {
            if (!playerIn.getCooldownTracker().hasCooldown(itemStack.getItem())) {
                if (isWater(worldIn.getBlockState(hoveredBlock).getBlock())){
                    playerIn.setActiveHand(handIn);
                    return ActionResult.resultConsume(itemStack);
                }
            }
        }
        return ActionResult.resultFail(itemStack);
    }

}

package org.astemir.forestcraft.common.items.tools.other;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCLootTables;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import java.util.List;

public class ItemFossilBrush extends AItem {


    public ItemFossilBrush() {
        super("forestcraft:fossil_brush");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(32);
        lore(new TranslationTextComponent(FCStrings.FOSSIL_BRUSH).mergeStyle(TextFormatting.GRAY));
    }




    public ItemDropPool selectLootTable(Block block){
        if (block.equals(Blocks.DIRT) || block.equals(Blocks.COARSE_DIRT)){
            return FCLootTables.DIRT;
        }else
        if (block.equals(Blocks.SAND)){
            return FCLootTables.SAND;
        }else
        if (block.equals(Blocks.GRAVEL)){
            return FCLootTables.GRAVEL;
        }else
        if (block.equals(FCBlocks.FOSSIL_BLOCK)){
            return FCLootTables.FOSSIL;
        }else
        if (block.equals(Blocks.SOUL_SAND) || block.equals(Blocks.SOUL_SOIL)){
            return FCLootTables.SOUL_SAND;
        }
        return FCLootTables.DIRT;
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 60;
    }

    public boolean isBlockClearable(Block block){
        return (
               block.equals(Blocks.GRAVEL) ||
               block.equals(FCBlocks.FOSSIL_BLOCK) ||
               block.equals(Blocks.SAND) ||
               block.equals(Blocks.DIRT) ||
               block.equals(Blocks.COARSE_DIRT) ||
               block.equals(Blocks.SOUL_SAND) ||
               block.equals(Blocks.SOUL_SOIL)
        );
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (!PlayerUtils.hasCooldown((PlayerEntity)livingEntityIn,stack)) {
            BlockPos hoveredBlock = EntityUtils.rayTrace(worldIn,livingEntityIn,3).getPos();
            if (isBlockClearable(worldIn.getBlockState(hoveredBlock).getBlock())) {
                if (count % 10 == 0) {
                    for (int i = 0; i < 5; i++) {
                        worldIn.addParticle(new BlockParticleData(ParticleTypes.BLOCK, worldIn.getBlockState(hoveredBlock)), hoveredBlock.getX() + 0.5f + RandomUtils.randomFloat(-0.5f, 0.5f), hoveredBlock.getY() + 0.5f + RandomUtils.randomFloat(-0.25f, 0.5f), hoveredBlock.getZ() + 0.5f + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-1f, 1f), RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-1f, 1f));
                    }
                    livingEntityIn.playSound(SoundEvents.BLOCK_GRAVEL_BREAK, 1, RandomUtils.randomFloat(1.1f, 1.2f));
                }
            }else{
                livingEntityIn.stopActiveHand();
            }
        }
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity livingEntityIn) {
        if (!worldIn.isRemote) {
            BlockPos hoveredBlock = EntityUtils.rayTrace(worldIn,livingEntityIn,3).getPos();
            PlayerUtils.cooldownItem((PlayerEntity)livingEntityIn,stack,40);
            PlayerUtils.damageItem((PlayerEntity)livingEntityIn,stack,livingEntityIn.getActiveHand(),1);
            ItemDropPool lootTable = selectLootTable(worldIn.getBlockState(hoveredBlock).getBlock());
            ItemStack loot = lootTable.drop();
            worldIn.destroyBlock(hoveredBlock, false);
            if (loot != null){
                WorldUtils.spawnItem(worldIn,hoveredBlock,loot);
            }
        }
        return super.onUseFinish(stack, worldIn, livingEntityIn);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        BlockPos hoveredBlock = EntityUtils.rayTrace(worldIn,playerIn,3).getPos();
        if (!worldIn.getBlockState(hoveredBlock).isAir()) {
            if (!PlayerUtils.hasCooldown(playerIn,itemstack)) {
                if (isBlockClearable(worldIn.getBlockState(hoveredBlock).getBlock())){
                    playerIn.setActiveHand(handIn);
                    return ActionResult.resultConsume(itemstack);
                }
            }
        }
        return ActionResult.resultFail(itemstack);
    }

}

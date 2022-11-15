package org.astemir.forestcraft.common.items.tools.other;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemNetheriteLighter extends AItem {

    public ItemNetheriteLighter() {
        super("forestcraft:netherite_lighter");
        rarity(Rarity.RARE);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
    }


    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public int getMaxDamage() {
        return 128;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        ItemStack itemstack = context.getItem();
        BlockState blockstate = world.getBlockState(blockpos);
        if (CampfireBlock.canBeLit(blockstate)) {
            world.playSound(player, blockpos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            world.setBlockState(blockpos, blockstate.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            if (player != null) {
                PlayerUtils.damageItem(player,itemstack,context.getHand(),1);
            }
            return ActionResultType.func_233537_a_(world.isRemote());
        } else {
            BlockPos blockpos1 = blockpos.offset(context.getFace());
            if (context.getFace().equals(Direction.UP) && AbstractFireBlock.canLightBlock(world, blockpos1, context.getPlacementHorizontalFacing())) {
                world.playSound(player, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = Blocks.SOUL_FIRE.getDefaultState();
                world.setBlockState(blockpos1, blockstate1, 11);
                if (player instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, blockpos1, itemstack);
                    PlayerUtils.damageItem(player,itemstack,context.getHand(),1);
                }
                return ActionResultType.func_233537_a_(world.isRemote());
            } else {
                return ActionResultType.FAIL;
            }
        }
    }


}

package org.astemir.api.common.item;


import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModShovelItem;


public class AItemShovel extends AItemTool{

    public AItemShovel(String registryName,IItemTier tier, float damage, float speed) {
        super(registryName,tier,damage,speed);
        addToolType(AItemToolType.SHOVEL);
    }

    public AItemShovel(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,-3);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModShovelItem shovelItem = (ModShovelItem) new ModShovelItem(getTier(), properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return shovelItem;
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (context.getFace() == Direction.DOWN) {
            return ActionResultType.PASS;
        } else {
            PlayerEntity playerentity = context.getPlayer();
            BlockState blockstate1 = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItem(), net.minecraftforge.common.ToolType.SHOVEL);
            BlockState blockstate2 = null;
            if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
                if (!world.isRemote()) {
                    world.playEvent((PlayerEntity)null, 1009, blockpos, 0);
                }

                CampfireBlock.extinguish(world, blockpos, blockstate);
                blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.valueOf(false));
            }

            if (blockstate2 != null) {
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate2, 11);
                    if (playerentity != null) {
                        context.getItem().damageItem(1, playerentity, (player) -> {
                            player.sendBreakAnimation(context.getHand());
                        });
                    }
                }

                return ActionResultType.func_233537_a_(world.isRemote);
            } else {
                return ActionResultType.PASS;
            }
        }
    }
}

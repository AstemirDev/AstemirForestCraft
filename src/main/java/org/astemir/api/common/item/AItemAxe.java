package org.astemir.api.common.item;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModAxeItem;


public class AItemAxe extends AItemTool{

    public AItemAxe( String registryName,IItemTier tier, float damage, float speed) {
        super(registryName,tier,damage,speed);
        addToolType(AItemToolType.AXE);
    }

    public AItemAxe(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,-3f);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModAxeItem axeItem = (ModAxeItem) new ModAxeItem(getTier(), properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return axeItem;
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return AItemToolType.getDestroySpeed(stack,state);
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        BlockState block = blockstate.getToolModifiedState(world, blockpos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.AXE);
        if (block != null) {
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                world.setBlockState(blockpos, block, 11);
                if (playerentity != null) {
                    context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {
                        p_220040_1_.sendBreakAnimation(context.getHand());
                    });
                }
            }
            return ActionResultType.func_233537_a_(world.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }
}

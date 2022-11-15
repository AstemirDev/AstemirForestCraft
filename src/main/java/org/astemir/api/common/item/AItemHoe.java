package org.astemir.api.common.item;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModHoeItem;


public class AItemHoe extends AItemTool{

    public AItemHoe(String registryName,IItemTier tier, float damage, float speed) {
        super(registryName,tier,damage,speed);
        addToolType(AItemToolType.HOE);
    }

    public AItemHoe(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,0f);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModHoeItem axeItem = (ModHoeItem) new ModHoeItem(getTier(), properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return axeItem;
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
            BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.HOE);
            if (blockstate != null) {
                PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    if (playerentity != null) {
                        context.getItem().damageItem(1, playerentity, (player) -> {
                            player.sendBreakAnimation(context.getHand());
                        });
                    }
                }

                return ActionResultType.func_233537_a_(world.isRemote);
            }
        }
        return ActionResultType.PASS;
    }
}

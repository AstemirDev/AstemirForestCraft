package org.astemir.forestcraft.common.items.misc;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemFertilizer extends AItem {


    public ItemFertilizer() {
        super("forestcraft:niter_fertilizer");
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        maxStack(64);
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockPos offset = blockpos.offset(context.getFace());
        if (WorldUtils.growPlants(context.getItem(), world, blockpos, context.getPlayer())) {
            if (!world.isRemote) {
                world.playEvent(2005, blockpos, 0);
            }
            return ActionResultType.func_233537_a_(world.isRemote);
        } else {
            BlockState blockstate = world.getBlockState(blockpos);
            boolean flag = blockstate.isSolidSide(world, blockpos, context.getFace());
            if (flag && WorldUtils.growSeagrass(context.getItem(), world, offset, context.getFace(),random)) {
                if (!world.isRemote) {
                    world.playEvent(2005, offset, 0);
                }

                return ActionResultType.func_233537_a_(world.isRemote);
            } else {
                return ActionResultType.PASS;
            }
        }
    }
}

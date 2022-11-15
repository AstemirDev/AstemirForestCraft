package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.forestcraft.common.blocks.plants.BlockSnowberryBush;
import org.astemir.api.math.RandomUtils;
import java.util.Random;

public class SnowberryPlacer extends BlockPlacer {
    @Override
    protected BlockPlacerType<?> getBlockPlacerType() {
        return BlockPlacerType.SIMPLE_BLOCK;
    }

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (RandomUtils.doWithChance(10)) {
            if (RandomUtils.doWithChance(50)) {
                world.setBlockState(pos, state, 2);
            }else{
                world.setBlockState(pos, state.with(BlockSnowberryBush.AGE,1), 2);
            }
        }
    }

}
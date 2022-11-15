package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;

public class BakudanTreasurePlacer extends BlockPlacer{

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        world.setBlockState(pos, FCBlocks.BAKUDAN_TREASURE.getDefaultState(), 2);
        world.setBlockState(pos.up(), FCBlocks.BAKUDAN_TREASURE.getDefaultState(), 2);
    }

    @Override
    protected BlockPlacerType<?> getBlockPlacerType(){
        return BlockPlacerType.SIMPLE_BLOCK;
    }
}

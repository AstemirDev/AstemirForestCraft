package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class DirtPilePlacer extends BlockPlacer
{
    @Override
    protected BlockPlacerType<?> getBlockPlacerType() {
        return BlockPlacerType.SIMPLE_BLOCK;
    }

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (world.getBiome(pos).getCategory().equals(Biome.Category.PLAINS) || world.getBiome(pos).getCategory().equals(Biome.Category.FOREST)) {
            if (RandomUtils.doWithChance(10)) {
                if (RandomUtils.doWithChance(10)) {
                    world.setBlockState(pos, state, 2);
                }
            }
        }
    }
}

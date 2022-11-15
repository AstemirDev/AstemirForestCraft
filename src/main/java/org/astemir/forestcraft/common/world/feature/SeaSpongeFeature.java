package org.astemir.forestcraft.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.*;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;

public class SeaSpongeFeature extends Feature<FeatureSpreadConfig> {

    public SeaSpongeFeature() {
        super(FeatureSpreadConfig.CODEC);
    }


    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, FeatureSpreadConfig config) {
        int j1 = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
        BlockPos blockpos = new BlockPos(pos.getX(), j1, pos.getZ());
        if (reader.getBlockState(blockpos.down()).isIn(Blocks.SAND)) {
            BlockState blockstate = FCBlocks.SEA_SPONGE.getDefaultState();
            if (reader.getBlockState(blockpos).isIn(Blocks.WATER) && blockstate.isValidPosition(reader, blockpos)) {
                reader.setBlockState(blockpos, blockstate, 2);
            }
        }
        return true;
    }
}

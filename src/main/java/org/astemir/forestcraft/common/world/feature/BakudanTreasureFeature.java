package org.astemir.forestcraft.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;

public class BakudanTreasureFeature extends Feature<NoFeatureConfig> {

    public BakudanTreasureFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }

    public boolean isValidSoil(BlockState state){
        return state.equals(Blocks.WARPED_NYLIUM.getDefaultState()) || state.equals(Blocks.NETHERRACK.getDefaultState());
    }

    public boolean isValidBlock(ISeedReader world,BlockPos pos){
        return world.getBlockState(pos).isAir() && isValidSoil(world.getBlockState(pos.add(0, -1, 0)));
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (RandomUtils.doWithChance(50)) {
            if (isValidBlock(world, pos)) {
                world.setBlockState(pos, FCBlocks.BAKUDAN_TREASURE.getDefaultState(), 3);
                return true;
            }
        }
        return true;
    }
}

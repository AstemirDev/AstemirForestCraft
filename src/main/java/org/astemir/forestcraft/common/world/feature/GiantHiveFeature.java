package org.astemir.forestcraft.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class GiantHiveFeature extends Feature<NoFeatureConfig> {

    public GiantHiveFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }


    public boolean hasWaterAround(ISeedReader world,BlockPos pos){
        for (int i = -3;i<3;i++){
            for (int j = -3;j<3;j++){
                for (int k = -3;k<3;k++){
                    BlockState b = world.getBlockState(pos.add(i,j,k));
                    if (b.equals(Blocks.WATER.getDefaultState())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidSoil(BlockState state){
        return state.equals(Blocks.GRASS_BLOCK.getDefaultState()) || state.equals(Blocks.DIRT.getDefaultState());
    }

    public boolean isValidBlock(ISeedReader world,BlockPos pos){
        return world.getBlockState(pos).isAir() && !hasWaterAround(world, pos) && isValidSoil(world.getBlockState(pos.add(0, -1, 0)));
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (RandomUtils.doWithChance(2)) {
            if (RandomUtils.doWithChance(2)) {
                if (RandomUtils.doWithChance(10)) {
                    if (isValidBlock(world, pos)) {
                        world.setBlockState(pos, FCBlocks.GIANT_HIVE.getDefaultState(), 3);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}

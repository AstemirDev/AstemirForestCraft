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

public class BlueberryBushFeature extends Feature<NoFeatureConfig> {

    public BlueberryBushFeature() {
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
        return state.equals(Blocks.GRASS_BLOCK.getDefaultState()) || state.equals(Blocks.DIRT.getDefaultState()) || state.equals(FCBlocks.BLUEBERRY_BUSH.getDefaultState()) || state.equals(FCBlocks.SHARPED_LEAVES.getDefaultState());
    }

    public boolean isValidBlock(ISeedReader world,BlockPos pos){
        return !world.getBlockState(pos).isSolid() && !hasWaterAround(world, pos) && isValidSoil(world.getBlockState(pos.add(0, -1, 0)));
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (rand.nextInt(100) == 0) {
            if (pos.getY() > world.getSeaLevel()) {
                for (int i = -RandomUtils.randomInt(1, 3); i < RandomUtils.randomInt(1, 3); i++) {
                    for (int j = -RandomUtils.randomInt(1, 3); j < RandomUtils.randomInt(1, 3); j++) {
                        for (int k = 0; k < RandomUtils.randomInt(1, 3); k++) {
                            if (RandomUtils.doWithChance(90)) {
                                if (RandomUtils.doWithChance(95)) {
                                    if (isValidBlock(world, pos.add(i, j, k))) {
                                        world.setBlockState(pos.add(i, j, k), FCBlocks.BLUEBERRY_BUSH.getDefaultState(), 3);
                                    }
                                } else {
                                    if (isValidBlock(world, pos.add(i, j, k))) {
                                        world.setBlockState(pos.add(i, j, k), FCBlocks.SHARPED_LEAVES.getDefaultState(), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}

package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class DesertBonesPlacer extends BlockPlacer {

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (RandomUtils.doWithChance(2) && RandomUtils.doWithChance(FCConfigurationValues.DESERT_BONES_RATE.getValue())) {
            if (pos.getY() > world.getSeaLevel()) {
                if (isValidBlock(world,pos)) {
                    if (RandomUtils.doWithChance(50)) {
                        world.setBlockState(pos, FCBlocks.BONE_STRUCTURE_0.getDefaultState(), 3);
                    }else
                    if (RandomUtils.doWithChance(50)) {
                        world.setBlockState(pos, FCBlocks.BONE_STRUCTURE_1.getDefaultState(), 3);
                    }else{
                        world.setBlockState(pos, FCBlocks.BONE_STRUCTURE_2.getDefaultState(), 3);
                    }
                }
            }
        }
    }

    @Override
    protected BlockPlacerType<?> getBlockPlacerType(){
        return BlockPlacerType.SIMPLE_BLOCK;
    }
    public boolean hasWaterAround(IWorld world, BlockPos pos){
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
    return state.equals(Blocks.SAND.getDefaultState()) || state.equals(FCBlocks.FOSSIL_BLOCK.getDefaultState());
    }

    public boolean isValidBlock(IWorld world,BlockPos pos){
    return !world.getBlockState(pos).isSolid() && !hasWaterAround(world, pos) && isValidSoil(world.getBlockState(pos.add(0, -1, 0)));
    }
}

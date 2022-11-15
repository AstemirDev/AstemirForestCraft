package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;

public class LightBulbPlacer extends BlockPlacer{

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (random.nextInt(20) == 0) {
            for (int i = 50;i>20;i--) {
                BlockPos randomPos = new BlockPos(pos.getX(),i,pos.getZ());
                if (isValidBlock(world, randomPos)) {
                    world.setBlockState(randomPos, FCBlocks.LIGHT_BULB.getDefaultState(), 3);
                    break;
                }
            }
        }
    }

    @Override
    protected BlockPlacerType<?> getBlockPlacerType(){
        return BlockPlacerType.SIMPLE_BLOCK;
    }


    public boolean isValidSoil(BlockState state){
        return state.equals(Blocks.STONE.getDefaultState());
    }

    public boolean isValidBlock(IWorld world,BlockPos pos){
        return world.getBlockState(pos).isAir() && isValidSoil(world.getBlockState(pos.add(0, -1, 0)));
    }
}

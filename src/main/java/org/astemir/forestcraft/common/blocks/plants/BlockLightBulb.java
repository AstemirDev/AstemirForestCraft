package org.astemir.forestcraft.common.blocks.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import org.astemir.forestcraft.registries.FCBlocks;


public class BlockLightBulb extends FlowerBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);


    public BlockLightBulb() {
        super(Effects.GLOWING,200,Properties.create(Material.PLANTS).zeroHardnessAndResistance().sound(SoundType.PLANT).doesNotBlockMovement().setLightLevel((v)->{return 8;}));
    }


    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }


    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(FCBlocks.PEARLSTONE) || state.isIn(Blocks.GRANITE) || state.isIn(Blocks.ANDESITE) || state.isIn(FCBlocks.DEEP_ROCK) || state.isIn(Blocks.DIORITE) || state.isIn(Blocks.STONE) || state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
    }

}

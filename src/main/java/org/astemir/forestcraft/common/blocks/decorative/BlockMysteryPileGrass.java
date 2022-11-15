package org.astemir.forestcraft.common.blocks.decorative;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;


public class BlockMysteryPileGrass extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 14.0D, 14.0D, 14.0D);


    public BlockMysteryPileGrass() {
        super(Properties.create(Material.CLAY).hardnessAndResistance(0.1f).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND).doesNotBlockMovement());
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d vector3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vector3d.x, vector3d.y, vector3d.z);
    }


    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return blockstate.getBlock().equals(Blocks.GRASS_BLOCK) || blockstate.getBlock().equals(Blocks.DIRT);
    }


}

package org.astemir.forestcraft.common.blocks.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;


public class BlockBlueberryBush extends BushBlock {

    private static final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(1D, 1D, 1D, 15.0D, 15D, 15D);
    private static final VoxelShape OUTLINE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;

    public BlockBlueberryBush() {
        super(Properties.create(Material.LEAVES).hardnessAndResistance(0.05f).sound(SoundType.VINE).notSolid().setOpaque(BlockBlueberryBush::isntSolid).setBlocksVision(BlockBlueberryBush::isntSolid).setSuffocates(BlockBlueberryBush::isntSolid).tickRandomly());
        setDefaultState(this.stateContainer.getBaseState().with(AGE,0));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.isAirBlock(blockpos)) {
            int i;
            for(i = 1; worldIn.getBlockState(pos.down(i)).isIn(this) || worldIn.getBlockState(pos.down(i)).isIn(FCBlocks.BLUEBERRY_BUSH_EMPTY); ++i) {
            }

            if (i < 4) {
                int j = state.get(AGE);
                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
                    if (j == 15) {
                        worldIn.setBlockState(blockpos, FCBlocks.BLUEBERRY_BUSH_EMPTY.getDefaultState());
                        BlockState blockstate = state.with(AGE, Integer.valueOf(0));
                        worldIn.setBlockState(pos, blockstate, 4);
                        blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
                    } else {
                        worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(FCBlocks.BLUEBERRY_BUSH) || state.isIn(FCBlocks.BLUEBERRY_BUSH_EMPTY) || state.isIn(FCBlocks.SHARPED_LEAVES) || state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
    }



    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1F);
        }
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return OUTLINE_SHAPE;
    }



    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return OUTLINE_SHAPE;
    }
}

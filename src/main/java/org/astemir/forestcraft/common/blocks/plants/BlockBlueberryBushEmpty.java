package org.astemir.forestcraft.common.blocks.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.common.blocks.ISeedable;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.api.math.RandomUtils;

import java.util.Random;


public class BlockBlueberryBushEmpty extends BushBlock implements IGrowable, ISeedable {

    private static final VoxelShape[] COLLISION_SHAPES = new VoxelShape[]{Block.makeCuboidShape(5D, 1D, 5D, 11.0D, 6D, 11D),Block.makeCuboidShape(3D, 1D, 3D, 12.0D, 9D, 12D),Block.makeCuboidShape(1D, 1D, 1D, 15.0D, 15D, 15D)};
    private static final VoxelShape[] OUTLINE_SHAPES = new VoxelShape[]{Block.makeCuboidShape(5D, 1D, 5D, 11.0D, 6D, 11D),Block.makeCuboidShape(3D, 1D, 3D, 12.0D, 9D, 12D),Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public static final IntegerProperty BLUEBERRY_AGE = BlockStateProperties.AGE_0_2;

    public BlockBlueberryBushEmpty() {
        super(Properties.create(Material.LEAVES).hardnessAndResistance(0.05f).sound(SoundType.VINE).notSolid().setOpaque(BlockBlueberryBushEmpty::isntSolid).setBlocksVision(BlockBlueberryBushEmpty::isntSolid).setSuffocates(BlockBlueberryBushEmpty::isntSolid));
        this.setDefaultState(this.stateContainer.getBaseState().with(BLUEBERRY_AGE, Integer.valueOf(0)));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BLUEBERRY_AGE);
    }


    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(FCBlocks.BLUEBERRY_BUSH) || (state.isIn(FCBlocks.BLUEBERRY_BUSH_EMPTY) && state.get(BLUEBERRY_AGE) == 2) || state.isIn(FCBlocks.SHARPED_LEAVES) || state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
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
        return OUTLINE_SHAPES[state.get(this.getAgeProperty())];
    }



    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPES[state.get(this.getAgeProperty())];
    }



    public IntegerProperty getAgeProperty() {
        return BLUEBERRY_AGE;
    }


    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(BLUEBERRY_AGE) <= 2;
    }


    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (RandomUtils.doWithChance(2)) {
            grow(worldIn,random,pos,state);
        }
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return OUTLINE_SHAPES[state.get(this.getAgeProperty())];
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (state.get(BLUEBERRY_AGE) < 2) {
            worldIn.setBlockState(pos, state.with(BLUEBERRY_AGE, state.get(BLUEBERRY_AGE) + 1), 2);
        }else {
            if (RandomUtils.doWithChance(5)) {
                worldIn.setBlockState(pos, FCBlocks.SHARPED_LEAVES.getDefaultState(), 2);
            }else{
                worldIn.setBlockState(pos, FCBlocks.BLUEBERRY_BUSH.getDefaultState(), 2);
            }
        }
    }

    @Override
    public Item getSeeds() {
        return FCItems.BLUEBERRY_SEEDS;
    }
}

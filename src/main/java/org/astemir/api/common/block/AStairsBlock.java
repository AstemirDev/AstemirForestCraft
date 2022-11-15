package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.block.vanilla.ModOreBlock;
import org.astemir.api.common.block.vanilla.ModStairsBlock;
import org.astemir.api.math.MapBuilder;
import org.astemir.api.math.RandomUtils;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static net.minecraft.block.StairsBlock.SHAPE;


public class AStairsBlock extends ABlock{

    private Supplier<BlockState> stateSupplier;

    public AStairsBlock(Supplier<BlockState> stateSupplier,String registryName) {
        super(registryName);
        this.stateSupplier = stateSupplier;
        states(new MapBuilder().put(StairsBlock.FACING,Direction.NORTH).put(StairsBlock.HALF, Half.BOTTOM).put(StairsBlock.SHAPE,StairsShape.STRAIGHT).put(StairsBlock.WATERLOGGED,Boolean.valueOf(false)).build());
    }

    @Override
    public <T extends Comparable<T>, V extends T> BlockState placement(Block block, BlockItemUseContext context) {
        Direction direction = context.getFace();
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        BlockState blockstate = block.getDefaultState().with(StairsBlock.FACING, context.getPlacementHorizontalFacing()).with(StairsBlock.HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getHitVec().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP).with(StairsBlock.WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        return blockstate;
    }

    @Override
    public void onClientTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        getModelBlock().animateTick(stateIn, worldIn, pos, rand);
    }


    public Supplier<BlockState> getStateSupplier() {
        return stateSupplier;
    }

    public BlockState getModelState(){
        return stateSupplier.get();
    }

    public Block getModelBlock(){
        return stateSupplier.get().getBlock();
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        Direction direction = state.get(StairsBlock.FACING);
        StairsShape stairsshape = state.get(SHAPE);
        switch(mirrorIn) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_LEFT);
                        default:
                            return state.rotate(Rotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).with(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(state, mirrorIn);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(StairsBlock.FACING, rot.rotate(state.get(StairsBlock.FACING)));
    }

    @Override
    public void onTick(BlockState state, ServerWorld worldIn, BlockPos posIn, Random randomIn) {
        getModelBlock().randomTick(state, worldIn, posIn, randomIn);
    }

    @Override
    public ActionResultType onRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return getModelBlock().onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        getModelBlock().onExplosionDestroy(worldIn, pos, explosionIn);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        getModelBlock().onPlayerDestroy(worldIn, pos, state);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        getModelBlock().onEntityWalk(worldIn, pos, entityIn);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(StairsBlock.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
    }


    public Block build(Block.Properties properties) {
        ModStairsBlock resultItem = (ModStairsBlock) new ModStairsBlock(stateSupplier,properties).blockConstructor(this);
        return resultItem;
    }
}
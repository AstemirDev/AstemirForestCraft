package org.astemir.api.common.block.vanilla;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.block.AOreBlock;
import org.astemir.api.common.block.AStairsBlock;
import org.astemir.api.common.block.IModBlock;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class ModStairsBlock extends StairsBlock implements IModBlock<AStairsBlock> {

    private AStairsBlock constructor;
    private Supplier<BlockState> stateSupplier;

    public ModStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
        this.stateSupplier = state;
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

    @Override
    public FluidState getFluidState(BlockState state) {
        return constructor.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return constructor.placement(this,context);
    }

    @Override
    public AStairsBlock blockConstructor() {
        return constructor;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return constructor.onRightClick(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return constructor.getRayTraceShape(state,reader,pos,context);
    }

    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return constructor.getCollisionShape(state,worldIn,pos,context);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        if (constructor.propagatesSkylightDown()){
            return true;
        }else {
            return super.propagatesSkylightDown(state,reader,pos);
        }
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return constructor.getAmbientOcclusionLightValue(state, worldIn, pos);
    }

    @Override
    public void defaultState(BlockState state) {
        setDefaultState(state);
    }

    @Override
    public IModBlock blockConstructor(AStairsBlock constructor) {
        this.constructor = constructor;
        return this;
    }


    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return constructor.getExpDrop(state, world, pos, fortune, silktouch);
    }

    @Override
    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        return constructor.getBlockHardness(state,player,worldIn,pos);
    }

    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot) {
        return constructor.rotate(state,rot);
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return constructor.mirror(state,mirrorIn);
    }

    @Override
    public float getExplosionResistance(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion) {
        return constructor.blastResistance();
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        constructor.onPlayerDestroy(worldIn, pos, state);
    }

    @Override
    public void fillWithRain(World worldIn, BlockPos pos) {
        constructor.onRainTick(worldIn,pos);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        constructor.onHarvest(worldIn, pos, state, player);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        constructor.onClientTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return constructor.dropFromExplosion(explosionIn);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        constructor.onEntityFall(worldIn, pos, entityIn, fallDistance);
    }

    @Override
    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        constructor.onEntityLand(worldIn, entityIn);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        constructor.onPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        constructor.onExplosionDestroy(worldIn, pos, explosionIn);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        constructor.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return constructor.renderType(state);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        constructor.onTick(state,worldIn,pos,rand);
    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return constructor.hasTileEntity(state);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return constructor.createTileEntity(state, world);
    }

    @Override
    public Item asItem() {
        return constructor.getBlockItem();
    }
}



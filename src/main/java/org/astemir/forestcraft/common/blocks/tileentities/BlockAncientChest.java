package org.astemir.forestcraft.common.blocks.tileentities;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import org.astemir.api.common.item.ITSRRendered;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCTileEntities;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientChest;
import org.astemir.forestcraft.common.world.FCWorldData;

import javax.annotation.Nullable;


import static org.astemir.forestcraft.common.blocks.decorative.BlockSeaSponge.WATERLOGGED;


public class BlockAncientChest extends AbstractChestBlock<TileEntityAncientChest> implements ITSRRendered {

    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);


    public BlockAncientChest() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(30,1000f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE), ()->FCTileEntities.ANCIENT_CHEST);
        this.setDefaultState(this.stateContainer.getBaseState().with(ChestBlock.TYPE, ChestType.SINGLE).with(ChestBlock.FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(LOCKED, Boolean.valueOf(false)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(ChestBlock.TYPE, ChestType.SINGLE).with(LOCKED,false).with(ChestBlock.FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (isLocked(worldIn,pos)) {
            if (player.getHeldItemMainhand().getItem().equals(FCItems.ARCHAIC_KEY)) {
                if (!worldIn.isRemote) {
                    FCWorldData data = FCWorldData.getData(worldIn);
                    if (!data.isRunestoneLordKilled()) {
                        player.sendStatusMessage(new TranslationTextComponent("message.forestcraft.warn_runestone_lord"), false);
                        return ActionResultType.SUCCESS;
                    }else{
                        player.getHeldItemMainhand().shrink(1);
                        worldIn.setBlockState(pos, state.with(LOCKED, false));
                        return ActionResultType.SUCCESS;
                    }
                }
                player.playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN, 1, 2);
            }else{
                player.playSound(SoundEvents.BLOCK_IRON_DOOR_CLOSE,1,2);
            }
        }else {
            INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
            player.openContainer(inamedcontainerprovider);
            PiglinTasks.func_234478_a_(player, true);
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        if (isLocked(worldIn,pos)) {
            return 0;
        }else {
            return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityAncientChest();
    }

    public boolean isLocked(World world,BlockPos pos){
        return world.getBlockState(pos).get(LOCKED);
    }

    public boolean isLocked(IBlockReader world,BlockPos pos){
        return world.getBlockState(pos).get(LOCKED);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ChestBlock.FACING, ChestBlock.TYPE,WATERLOGGED,LOCKED);
    }

    @Override
    public TileEntityMerger.ICallbackWrapper<? extends TileEntityAncientChest> combine(BlockState state, World world, BlockPos pos, boolean override) {
        return TileEntityMerger.ICallback::func_225537_b_;
    }

    @Nullable
    @Override
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return super.getContainer(state, worldIn, pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityAncientChest();
    }
}

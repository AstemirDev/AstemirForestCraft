package org.astemir.forestcraft.common.blocks.tileentities;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientChest;
import org.astemir.forestcraft.registries.FCTileEntities;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientMonument;


public class BlockAncientMonument extends Block {

    public static final VoxelShape DOWN  = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    public static final VoxelShape MIDDLE  = Block.makeCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);
    public static final VoxelShape UP  = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE  = VoxelShapes.or(DOWN,MIDDLE,UP);

    public BlockAncientMonument() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(50,1200).sound(SoundType.STONE));
    }



    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityAncientMonument) {
            TileEntityAncientMonument ancientMonument = (TileEntityAncientMonument) tileentity;
            ItemStack item = player.getHeldItemMainhand();
            if (item.isEmpty()) {
                if (!ancientMonument.getDisplayItem().isEmpty()) {
                    ancientMonument.dropItem();
                    return ActionResultType.SUCCESS;
                }else{
                    return ActionResultType.PASS;
                }
            }else{
                if (!ancientMonument.getDisplayItem().isEmpty()){
                    ancientMonument.dropItem();
                }
                ancientMonument.setItem(getOneOfItem(item));
                item.shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    private ItemStack getOneOfItem(ItemStack old){
        ItemStack itemStack = old.copy();
        if (itemStack.getCount() > 1) {
            itemStack.setCount(1);
        }
        return itemStack;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityAncientMonument) {
                TileEntityAncientMonument ancientMonument = (TileEntityAncientMonument) tileentity;
                ancientMonument.dropItem();
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return FCTileEntities.ANCIENT_MONUMENT.create();
    }
}

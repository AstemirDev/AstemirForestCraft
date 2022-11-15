package org.astemir.forestcraft.common.blocks.tileentities;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.registries.FCTileEntities;
import org.astemir.forestcraft.common.tileentity.TileEntityIguanaKingEgg;


public class BlockIguanaKingEgg extends Block {

    public static final VoxelShape EGG_SHAPE  = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 20.0D, 15.0D);

    public BlockIguanaKingEgg() {
        super(Properties.create(Material.DRAGON_EGG).hardnessAndResistance(1).sound(FCSounds.IGUANA_KING_EGG));
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return EGG_SHAPE;
    }

    @Override
    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        if (EntityUtils.canSpawnAtPosition(worldIn,pos.up(), FCEntities.IGUANA_KING)) {
            return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
        }else{
            if (!player.world.isRemote) {
                if (!PlayerUtils.hasCooldown(player, asItem())) {
                    player.sendStatusMessage(TextUtils.translate(FCStrings.NOT_ENOUGH_SPACE_BOSS, TextFormatting.RED), false);
                    PlayerUtils.cooldownItem(player, asItem(), 20);
                }
            }
            return 0;
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state,TileEntity te, ItemStack stack) {
        ((TileEntityIguanaKingEgg)te).spawnIguanaKing(player);
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return FCTileEntities.IGUANA_KING_EGG.create();
    }
}

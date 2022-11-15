package org.astemir.forestcraft.common.blocks.tileentities;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.astemir.api.common.item.ITSRRendered;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCTileEntities;
import org.astemir.forestcraft.common.tileentity.TileEntityGiantHive;


public class BlockGiantHive extends Block implements ITSRRendered {

    public static final VoxelShape HIVE_SHAPE  = Block.makeCuboidShape(-8.0D, 0.0D, -8.0D, 24.0D, 48.0D, 24.0D);

    public BlockGiantHive() {
        super(AbstractBlock.Properties.create(Material.ORGANIC).setRequiresTool().harvestTool(ToolType.AXE).hardnessAndResistance(7).sound(SoundType.HONEY));
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return false;
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return super.getRenderShape(state, worldIn, pos);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return super.getCollisionShape(state, reader, pos);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return HIVE_SHAPE;
    }

    @Override
    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        if (EntityUtils.canSpawnAtPosition(worldIn,pos.up(), FCEntities.BEEQUEEN)) {
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
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        TileEntityGiantHive giantHive = ((TileEntityGiantHive)world.getTileEntity(pos));
        giantHive.setWhoBroken(player);
        giantHive.getFactory().playAnimation(TileEntityGiantHive.EXPLODE);
        return false;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return FCTileEntities.GIANT_HIVE.create();
    }
}

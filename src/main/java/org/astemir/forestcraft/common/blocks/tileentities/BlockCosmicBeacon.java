package org.astemir.forestcraft.common.blocks.tileentities;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.astemir.api.common.block.AHorizontalBlock;
import org.astemir.api.common.item.AItemBlockItem;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.api.math.MapBuilder;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.tileentity.TileEntityCosmicBeacon;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.Map;

import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;


public class BlockCosmicBeacon extends AHorizontalBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final BooleanProperty USED = BooleanProperty.create("used");

    public BlockCosmicBeacon() {
        super("forestcraft:cosmic_beacon");
        material(Material.ROCK).hardness(30).resistance(1000f).soundType(SoundType.STONE).requiredTool(AItemToolType.PICKAXE).lightLevel(8);
        states(new MapBuilder().put(HORIZONTAL_FACING,Direction.NORTH).put(USED,false).build());
    }


    @Override
    public Item blockItem(Block block) {
        AItemBlockItem itemConstructor = (AItemBlockItem) new AItemBlockItem(getRegistryName(), block).itemGroup(FCItemGroups.BLOCKS).maxStack(1).rarity(FCRarity.MYTHICAL);
        itemConstructor.setupISTER(ForestCraft.PROXY::setupISTER);
        itemConstructor.lore(TextUtils.translate("item.forestcraft.cosmic_beacon_description",TextFormatting.GRAY));
        return itemConstructor.register();
    }

    @Override
    public float getBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        if (state.get(USED)) {
            return 0;
        }else {
            return super.getBlockHardness(state, player, worldIn, pos);
        }
    }

    @Override
    public Map<Property, Object> getPlacementState(BlockItemUseContext context) {
        return new MapBuilder().put(USED,false).put(HORIZONTAL_FACING,context.getPlacementHorizontalFacing().getOpposite()).build();
    }


    @Override
    public BlockRenderType renderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }



    @Override
    public ActionResultType onRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntityCosmicBeacon cosmicBeacon = (TileEntityCosmicBeacon) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote) {
            if (!PlayerUtils.hasCooldown(player, getBlockItem())) {
                if (worldIn.getDimensionKey() == World.OVERWORLD || worldIn.getDimensionKey() == World.THE_END) {
                    if (WorldUtils.isNight(worldIn)) {
                        if (pos.getY() >= 150) {
                            if (!state.get(USED)) {
                                worldIn.setBlockState(pos, state.with(USED, true));
                                cosmicBeacon.getFactory().playAnimation(TileEntityCosmicBeacon.OPEN);
                            }
                            return super.onRightClick(state, worldIn, pos, player, handIn, hit);
                        } else {
                            player.sendStatusMessage(new TranslationTextComponent("block.forestcraft.cosmic_beacon_height_error"), false);
                            PlayerUtils.cooldownItem(player, getBlockItem(), 20);
                            return super.onRightClick(state, worldIn, pos, player, handIn, hit);
                        }
                    }else {
                        player.sendStatusMessage(new TranslationTextComponent("block.forestcraft.cosmic_beacon_time_error"), false);
                        PlayerUtils.cooldownItem(player, getBlockItem(), 20);
                        return super.onRightClick(state, worldIn, pos, player, handIn, hit);
                    }
                }
            }
        }
        return super.onRightClick(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityCosmicBeacon();
    }

}

package org.astemir.forestcraft.registries;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.particles.ParticleTypes;
import org.astemir.api.common.block.*;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.forestcraft.common.blocks.decorative.FCSkyBlock;
import org.astemir.forestcraft.common.blocks.tileentities.BlockCosmicBeacon;

public class FCNewBlocks {

    public static final Block VITA_BLOCK = new ABlock("forestcraft:vita_block").itemGroup(FCItemGroups.BLOCKS).hardness(4).resistance(4).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.PINK).register();
    public static final Block VITA_ORE = new AOreBlock("forestcraft:vita_ore").expDrop(2,4).itemGroup(FCItemGroups.BLOCKS).hardness(3).resistance(3).harvestLevel(2).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.PINK).register();
    public static final Block SPECTRALUM_ORE = new ABlock("forestcraft:spectralum_ore").itemGroup(FCItemGroups.BLOCKS).hardness(5).resistance(5).harvestLevel(3).requiredTool(AItemToolType.SHOVEL).material(Material.SAND).materialColor(MaterialColor.LIGHT_BLUE).soundType(SoundType.SOUL_SOIL).register();
    public static final Block TURF = new ABlock("forestcraft:turf").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).requiredTool(AItemToolType.SHOVEL).material(Material.SAND).materialColor(MaterialColor.BROWN).soundType(SoundType.GROUND).fuel(8000).register();
    public static final Block GHOSTONE = new ABlock("forestcraft:ghostone").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.BLUE).soundType(SoundType.NETHERRACK).register();
    public static final Block JEWEL_WART_ORE = new AOreBlock("forestcraft:jewel_wart_ore").expDrop(2,3).itemGroup(FCItemGroups.BLOCKS).harvestLevel(3).hardness(2).resistance(2).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.PINK).soundType(SoundType.NETHERRACK).register();
    public static final Block BLOODY_GHOSTONE = new ABlock("forestcraft:bloody_ghostone").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.BLUE).soundType(SoundType.NETHERRACK).register();
    public static final Block PRISMATIC_ORE = new AOreBlock("forestcraft:prismatic_ore").expDrop(10,20).itemGroup(FCItemGroups.BLOCKS).harvestLevel(3).hardness(4).resistance(4).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.WHITE_TERRACOTTA).register();
    public static final Block SPORUM_BLOCK = new ABlock("forestcraft:sporum_block").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).requiredTool(AItemToolType.AXE).material(Material.NETHER_PLANTS).materialColor(MaterialColor.GREEN).soundType(SoundType.FUNGUS).register();
    public static final Block GEPODIUM_BLOCK = new ABlock("forestcraft:gepodium_block").itemGroup(FCItemGroups.BLOCKS).hardness(6).resistance(6).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.PURPLE).soundType(SoundType.ANCIENT_DEBRIS).register();
    public static final Block GEPODIUM_CLUSTER = new ABlock("forestcraft:gepodium_cluster").itemGroup(FCItemGroups.BLOCKS).harvestLevel(4).hardness(8).resistance(8).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.PURPLE).soundType(SoundType.ANCIENT_DEBRIS).register();
    public static final Block CRYO_ICE = new ABlock("forestcraft:cryo_ice").itemGroup(FCItemGroups.BLOCKS).hardness(4).harvestLevel(3).resistance(4).requiredTool(AItemToolType.PICKAXE).material(Material.GLASS).materialColor(MaterialColor.LIGHT_BLUE).soundType(SoundType.GLASS).register();
    public static final Block BLOOMING_BLOCK = new ABlock("forestcraft:blooming_block").itemGroup(FCItemGroups.BLOCKS).hardness(4).harvestLevel(3).resistance(4).requiredTool(AItemToolType.PICKAXE).material(Material.IRON).materialColor(MaterialColor.GREEN).soundType(SoundType.METAL).register();
    public static final Block PRISMATIC_BLOCK = new ABlock("forestcraft:prismatic_block").itemGroup(FCItemGroups.BLOCKS).hardness(5).harvestLevel(4).resistance(4).requiredTool(AItemToolType.PICKAXE).material(Material.IRON).materialColor(MaterialColor.WHITE_TERRACOTTA).soundType(SoundType.METAL).register();
    public static final Block BLOOMING_LOG = new ARotatedPillarBlock("forestcraft:blooming_log").itemGroup(FCItemGroups.BLOCKS).hardness(4).resistance(4).harvestLevel(2).requiredTool(AItemToolType.AXE).soundType(SoundType.WOOD).material(Material.WOOD).materialColor(MaterialColor.GREEN).register();
    public static final Block CHERRY_PLUM_LOG = new ARotatedPillarBlock("forestcraft:cherry_plum_log").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).soundType(SoundType.WOOD).material(Material.WOOD).materialColor(MaterialColor.GREEN).register();
    public static final Block CHERRY_PLUM_PLANKS = new ABlock("forestcraft:cherry_plum_planks").itemGroup(FCItemGroups.BLOCKS).hardness(2).resistance(2).soundType(SoundType.WOOD).material(Material.WOOD).materialColor(MaterialColor.GREEN).register();
    public static final Block COSMIC_BEACON  = new BlockCosmicBeacon().register();


    public static final Block SKY_STONE  = new FCSkyBlock("forestcraft:sky_stone").itemGroup(FCItemGroups.BLOCKS).hardness(3).resistance(3).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.BLUE).register();
    public static final Block SKY_WALL  = new FCSkyBlock("forestcraft:sky_wall").itemGroup(FCItemGroups.BLOCKS).hardness(3).resistance(3).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.BLUE).register();
    public static final Block SKY_BRICKS  = new FCSkyBlock("forestcraft:sky_bricks").itemGroup(FCItemGroups.BLOCKS).hardness(3).resistance(3).requiredTool(AItemToolType.PICKAXE).material(Material.ROCK).materialColor(MaterialColor.BLUE).register();
    public static final Block CLOUD_BLOCK  = new FCSkyBlock("forestcraft:cloud_block").itemGroup(FCItemGroups.BLOCKS).soundType(SoundType.CLOTH).hardness(0).resistance(0).material(FCMaterials.CLOUD).materialColor(MaterialColor.WHITE_TERRACOTTA).register();
    public static final Block WALL_SKY_TORCH = new AWallTorchBlock("forestcraft:wall_sky_torch", ParticleTypes.END_ROD).hardness(0).resistance(0).material(Material.WOOD).materialColor(MaterialColor.BLUE).lightLevel(14).register();
    public static final Block SKY_TORCH = new ATorchBlock("forestcraft:sky_torch", ParticleTypes.END_ROD,()->WALL_SKY_TORCH).itemGroup(FCItemGroups.BLOCKS).hardness(0).resistance(0).material(Material.WOOD).materialColor(MaterialColor.BLUE).lightLevel(14).register();


}

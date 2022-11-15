package org.astemir.forestcraft.registries;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.world.feature.FossilFeature;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.world.placers.*;

import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

public class FCConfiguredFeatures {

    public static final ConfiguredFeature GIANT_HIVE_FEATURE = FCFeatures.GIANT_HIVE_FEATURE.withConfiguration(NO_FEATURE_CONFIG).withPlacement(Features.Placements.FLOWER_TALL_GRASS_PLACEMENT.square().chance(1).range(50)).func_242731_b(100);
    public static final ConfiguredFeature BLUEBERRY_FEATURE = FCFeatures.BLUEBERRY_FEATURE.withConfiguration(NO_FEATURE_CONFIG).withPlacement(Features.Placements.FLOWER_TALL_GRASS_PLACEMENT.square().chance(1).range(20)).func_242731_b(100);
    public static final ConfiguredFeature FOSSIL_FEATURE = FCFeatures.FOSSIL_FEATURE.withConfiguration(new OreFeatureConfig(new FossilFeature.FossilRuleTest(), FCBlocks.FOSSIL_BLOCK.getDefaultState(), 33)).range(80).square().chance(5).func_242731_b(20);
    public static final ConfiguredFeature CRYSTAL_CAVE_FEATURE = FCFeatures.CRYSTAL_CAVE_FEATURE.withConfiguration(NO_FEATURE_CONFIG);
    public static final ConfiguredFeature SPORUM_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.SPORUM), SimpleBlockPlacer.PLACER).tries(FCConfigurationValues.SPORE_MUSH_RATE.getValue()).build());
    public static final ConfiguredFeature DEEP_ROCK_FEATURE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, FCStates.DEEP_ROCK, 32)).range(32).square().func_242731_b(10);
    public static final ConfiguredFeature NITER_ORE_FEATURE = Feature.ORE.withConfiguration(new OreFeatureConfig(FCFillerBlockTypes.DEEP_ROCK, FCStates.NITER_ORE, 8)).range(32).square().func_242731_b(5);
    public static final ConfiguredFeature SULFUR_ORE_FEATURE = Feature.ORE.withConfiguration(new OreFeatureConfig(FCFillerBlockTypes.DEEP_ROCK, FCStates.SULFUR_ORE, 8)).range(32).square().func_242731_b(5);
    public static final ConfiguredFeature DANDELION_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.GROWN_DANDELION), SimpleBlockPlacer.PLACER).tries(4).xSpread(2).zSpread(2).build());
    public static final ConfiguredFeature DANDELION_FIELD_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.GROWN_DANDELION), SimpleBlockPlacer.PLACER).xSpread(4).zSpread(4).tries(2).build());
    public static final ConfiguredFeature SPONGE_FEATURE = FCFeatures.SEA_SPONGE_FEATURE.withConfiguration(new FeatureSpreadConfig(20)).withPlacement(FCPlacements.SEAGRASS_DISK_PLACEMENT).withChance(8).feature.get();
    public static final ConfiguredFeature DESERT_BONES_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.BONE_STRUCTURE_0), FCBlockPlacers.DESERT_BONES_PLACER).tries(64).build());
    public static final ConfiguredFeature LIGHT_BULB_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.LIGHT_BULB), FCBlockPlacers.LIGHT_BULB_PLACER).tries(64).build());
    public static final ConfiguredFeature SNOWBERRY_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.SNOWBERRY_BUSH), FCBlockPlacers.SNOWBERRY_PLACER).tries(8).build());
    public static final ConfiguredFeature CICADA_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), FCBlockPlacers.CICADA_PLACER).tries(8).build());
    public static final ConfiguredFeature MESA_PILE_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.MESA_PILE), FCBlockPlacers.MESA_PILE_PLACER).tries(8).build());
    public static final ConfiguredFeature DIRT_PILE_FEATURE = Feature.RANDOM_PATCH.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FCStates.DIRT_PILE), FCBlockPlacers.DIRT_PILE_PLACER).tries(8).build());
    public static final ConfiguredFeature BAKUDAN_TREASURE_FEATURE =  FCFeatures.BAKUDAN_TREASURE_FEATURE.withConfiguration(NO_FEATURE_CONFIG).withPlacement(Features.Placements.FIRE_PLACEMENT).square().chance(1).range(50).func_242731_b(100);
    public static final ConfiguredFeature AQUAMARINE_ORE = FCFeatures.AQUAMARINE_ORE_FEATURE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, FCStates.AQUAMARINE_ORE, 1)).range(8).func_242731_b(2).square();
    public static final ConfiguredFeature JEWEL_WART_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, FCStates.JEWEL_WART_ORE, 5)).withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square().func_242731_b(5);
    public static final ConfiguredFeature SPECTRALUM_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(FCFillerBlockTypes.SOUL_SOIL, FCStates.SPECTRALUM_ORE, 5)).withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square().func_242731_b(5);
    public static final ConfiguredFeature VITA_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, FCStates.VITA_ORE, 6)).range(20).square();
    public static final ConfiguredFeature BLOOMING_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(FCFillerBlockTypes.JUNGLE_LOG, FCStates.BLOOMING_LOG, 24)).range(256).square().func_242731_b(20);
    public static final ConfiguredFeature PRISMATIC_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, FCStates.PRISMATIC_ORE, 2)).range(10).square();


    public static void registerConfiguredFeatures() {
        registerConfiguredFeature("configured_giant_hive",GIANT_HIVE_FEATURE);
        registerConfiguredFeature("configured_blueberry",BLUEBERRY_FEATURE);
        registerConfiguredFeature("configured_fossil",FOSSIL_FEATURE);
        registerConfiguredFeature("configured_crystal_cave",CRYSTAL_CAVE_FEATURE);
        registerConfiguredFeature("configured_sporum",SPORUM_FEATURE);
        registerConfiguredFeature("configured_deep_rock",DEEP_ROCK_FEATURE);
        registerConfiguredFeature("configured_niter_ore",NITER_ORE_FEATURE);
        registerConfiguredFeature("configured_sulfur_ore",SULFUR_ORE_FEATURE);
        registerConfiguredFeature("configured_dandelion",DANDELION_FEATURE);
        registerConfiguredFeature("configured_dandelion_field",DANDELION_FIELD_FEATURE);
        registerConfiguredFeature("configured_sea_sponge",SPONGE_FEATURE);
        registerConfiguredFeature("configured_desert_bones",DESERT_BONES_FEATURE);
        registerConfiguredFeature("configured_light_bulb",LIGHT_BULB_FEATURE);
        registerConfiguredFeature("configured_snowberry",SNOWBERRY_FEATURE);
        registerConfiguredFeature("configured_cicada",CICADA_FEATURE);
        registerConfiguredFeature("configured_mesa_pile",MESA_PILE_FEATURE);
        registerConfiguredFeature("configured_dirt_pile",DIRT_PILE_FEATURE);
        registerConfiguredFeature("configured_bakudan_treasure",BAKUDAN_TREASURE_FEATURE);
        registerConfiguredFeature("configured_aquamarine_ore",AQUAMARINE_ORE);
        registerConfiguredFeature("configured_jewel_wart_ore",JEWEL_WART_ORE);
        registerConfiguredFeature("configured_vita_ore",VITA_ORE);
        registerConfiguredFeature("configured_blooming_log",BLOOMING_ORE);
        registerConfiguredFeature("configured_spectralum_ore",SPECTRALUM_ORE);
    }


    public static void registerConfiguredFeature(String name, ConfiguredFeature feature){
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(ForestCraft.MOD_ID, name), feature);
    }

    public static final class FCFillerBlockTypes {
        public static final RuleTest DEEP_ROCK = new BlockMatchRuleTest(FCBlocks.DEEP_ROCK);
        public static final RuleTest JUNGLE_LOG = new BlockMatchRuleTest(Blocks.JUNGLE_LOG);
        public static final RuleTest SOUL_SOIL = new BlockMatchRuleTest(Blocks.SOUL_SOIL);

    }

    public static final class FCPlacements{
        public static final ConfiguredPlacement<NoPlacementConfig> KELP_PLACEMENT = Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG);
        public static final ConfiguredPlacement<?> SEAGRASS_DISK_PLACEMENT = KELP_PLACEMENT.square();

    }

    public static final class FCBlockPlacers{
        protected static final BlockPlacer DESERT_BONES_PLACER = new DesertBonesPlacer();
        protected static final BlockPlacer CICADA_PLACER = new CicadaPlacer();
        protected static final BlockPlacer DIRT_PILE_PLACER = new DirtPilePlacer();
        protected static final BlockPlacer LIGHT_BULB_PLACER = new LightBulbPlacer();
        protected static final BlockPlacer MESA_PILE_PLACER = new MesaPilePlacer();
        protected static final BlockPlacer SNOWBERRY_PLACER = new SnowberryPlacer();
    }

    public static final class FCStates {
        protected static final BlockState BONE_STRUCTURE_0 = FCBlocks.BONE_STRUCTURE_0.getDefaultState();
        protected static final BlockState LIGHT_BULB = FCBlocks.LIGHT_BULB.getDefaultState();
        protected static final BlockState SPORUM = FCBlocks.SPORUM.getDefaultState();
        protected static final BlockState SNOWBERRY_BUSH = FCBlocks.SNOWBERRY_BUSH.getDefaultState();
        protected static final BlockState MESA_PILE = FCBlocks.MYSTERY_PILE_MESA.getDefaultState();
        protected static final BlockState DIRT_PILE = FCBlocks.MYSTERY_PILE_GRASS.getDefaultState();
        protected static final BlockState DEEP_ROCK = FCBlocks.DEEP_ROCK.getDefaultState();
        protected static final BlockState NITER_ORE = FCBlocks.NITER_ORE.getDefaultState();
        protected static final BlockState SULFUR_ORE = FCBlocks.SULFUR_ORE.getDefaultState();
        protected static final BlockState GROWN_DANDELION = FCBlocks.GROWN_DANDELION.getDefaultState();
        protected static final BlockState AQUAMARINE_ORE = FCBlocks.AQUAMARINE_ORE.getDefaultState();
        protected static final BlockState JEWEL_WART_ORE = FCNewBlocks.JEWEL_WART_ORE.getDefaultState();
        protected static final BlockState VITA_ORE = FCNewBlocks.VITA_ORE.getDefaultState();
        protected static final BlockState BLOOMING_LOG = FCNewBlocks.BLOOMING_LOG.getDefaultState();
        protected static final BlockState SPECTRALUM_ORE = FCNewBlocks.SPECTRALUM_ORE.getDefaultState();
        protected static final BlockState PRISMATIC_ORE = FCNewBlocks.PRISMATIC_ORE.getDefaultState();
        protected static final BlockState BAKUDAN_TREASURE = FCBlocks.BAKUDAN_TREASURE.getDefaultState();
    }
}

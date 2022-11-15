package org.astemir.forestcraft.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.forestcraft.common.entities.FCEntityClassification;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.registries.FCStructures;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import static org.astemir.forestcraft.registries.FCConfiguredFeatures.*;
import static org.astemir.forestcraft.registries.FCConfiguredStructures.*;

public class FCWorldGenerator {


    private static Method GETCODEC_METHOD;
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                System.out.println("Was unable to check if " + serverWorld.getDimensionType().toString() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkProvider().generator instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)){
                return;
            }
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());

            if(serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)){
                return;
            }
            tempMap.putIfAbsent(FCStructures.DOGS_LAIR, DimensionStructuresSettings.field_236191_b_.get(FCStructures.DOGS_LAIR));
            tempMap.putIfAbsent(FCStructures.IGUANA_KING_NEST, DimensionStructuresSettings.field_236191_b_.get(FCStructures.IGUANA_KING_NEST));
            tempMap.putIfAbsent(FCStructures.DANDELION_HILL, DimensionStructuresSettings.field_236191_b_.get(FCStructures.DANDELION_HILL));
            tempMap.putIfAbsent(FCStructures.ANCIENT_RUNESTONE_TEMPLE, DimensionStructuresSettings.field_236191_b_.get(FCStructures.ANCIENT_RUNESTONE_TEMPLE));
            tempMap.putIfAbsent(FCStructures.CROSSED_TREASURE, DimensionStructuresSettings.field_236191_b_.get(FCStructures.CROSSED_TREASURE));
            tempMap.putIfAbsent(FCStructures.FERTILE_SOIL, DimensionStructuresSettings.field_236191_b_.get(FCStructures.FERTILE_SOIL));
            tempMap.putIfAbsent(FCStructures.OLD_PLANTERS, DimensionStructuresSettings.field_236191_b_.get(FCStructures.OLD_PLANTERS));
            tempMap.putIfAbsent(FCStructures.MUTATED_BLUEBERRY, DimensionStructuresSettings.field_236191_b_.get(FCStructures.MUTATED_BLUEBERRY));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }

    public static boolean isEnabledInConfig(FCConfigurationValues.ConfigBoolean bool){
        return bool.getValue();
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (WorldUtils.isBiomes(biome,Biomes.FOREST,Biomes.BIRCH_FOREST,Biomes.FLOWER_FOREST,Biomes.DARK_FOREST,Biomes.TALL_BIRCH_FOREST)){
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.CHANGELING, FCConfigurationValues.CHANGELING_SPAWN_WEIGHT.getValue(), FCConfigurationValues.CHANGELING_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.CHANGELING_SPAWN_MAX_COUNT.getValue()));
        }
        if (WorldUtils.isBiomes(biome, Biomes.FOREST, Biomes.PLAINS,Biomes.BIRCH_FOREST,Biomes.FLOWER_FOREST,Biomes.DARK_FOREST,Biomes.TALL_BIRCH_FOREST)) {
            event.getSpawns().getSpawner(FCEntityClassification.WORMS).add(new MobSpawnInfo.Spawners(FCEntities.WORM, FCConfigurationValues.WORM_SPAWN_WEIGHT.getValue(), FCConfigurationValues.WORM_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.WORM_SPAWN_MAX_COUNT.getValue()));
            generation.withStructure(FERTILE_SOIL_FEATURE);
            generation.withStructure(OLD_PLANTERS_FEATURE);
        }
        if (WorldUtils.isBiomes(biome,Biomes.BEACH)){
            generation.withStructure(CROSSED_TREASURE_FEATURE);
        }
        if (event.getCategory() == Biome.Category.NETHER){
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,JEWEL_WART_ORE);
        }
        if (WorldUtils.isBiomes(biome,Biomes.WARPED_FOREST)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_BAKUDANS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.BAKUDAN, FCConfigurationValues.BAKUDAN_SPAWN_WEIGHT.getValue(), FCConfigurationValues.BAKUDAN_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.BAKUDAN_SPAWN_MAX_COUNT.getValue()));
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,BAKUDAN_TREASURE_FEATURE);
            }
        }
        if (WorldUtils.isBiome(biome,Biomes.SOUL_SAND_VALLEY)){
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,SPECTRALUM_ORE);
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.DESECRATED_SOUL, FCConfigurationValues.DESECRATED_SOUL_SPAWN_WEIGHT.getValue(), FCConfigurationValues.DESECRATED_SOUL_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.DESECRATED_SOUL_SPAWN_MAX_COUNT.getValue()));
        }
        if (WorldUtils.isBiomes(biome,Biomes.NETHER_WASTES)) {
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.NETHER_BAT, FCConfigurationValues.NETHER_BAT_SPAWN_WEIGHT.getValue(), FCConfigurationValues.NETHER_BAT_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.NETHER_BAT_SPAWN_MAX_COUNT.getValue()));
        }
        if (!WorldUtils.isBiomes(biome,Biomes.MUSHROOM_FIELD_SHORE,Biomes.MUSHROOM_FIELDS)) {
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.CLOUD_RAY, FCConfigurationValues.CLOUD_RAY_SPAWN_WEIGHT.getValue(), FCConfigurationValues.CLOUD_RAY_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.CLOUD_RAY_SPAWN_MAX_COUNT.getValue()));
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.NIGHT_BAT, FCConfigurationValues.NIGHT_BAT_SPAWN_WEIGHT.getValue(), FCConfigurationValues.NIGHT_BAT_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.NIGHT_BAT_SPAWN_MAX_COUNT.getValue()));
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_THUNDER_BIRDS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.THUNDER_SCREAMER, FCConfigurationValues.THUNDER_SCREAMER_SPAWN_WEIGHT.getValue(), FCConfigurationValues.THUNDER_SCREAMER_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.THUNDER_SCREAMER_SPAWN_MAX_COUNT.getValue()));
            }
        }
        if (isEnabledInConfig(FCConfigurationValues.SPAWN_CRYSTAL_BIOME)) {
            if (!WorldUtils.isBiomes(biome,Biomes.OCEAN,Biomes.COLD_OCEAN,Biomes.DEEP_COLD_OCEAN,Biomes.DEEP_FROZEN_OCEAN,Biomes.DEEP_LUKEWARM_OCEAN,Biomes.DEEP_OCEAN,Biomes.DEEP_WARM_OCEAN,Biomes.FROZEN_OCEAN,Biomes.LUKEWARM_OCEAN,Biomes.WARM_OCEAN)){
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, CRYSTAL_CAVE_FEATURE);
            }
        }
        if (isEnabledInConfig(FCConfigurationValues.SPAWN_MESA_PILES)) {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, MESA_PILE_FEATURE);
        }
        if (isEnabledInConfig(FCConfigurationValues.SPAWN_DIRT_PILES)) {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DIRT_PILE_FEATURE);
        }
        if (WorldUtils.isBiomes(biome,Biomes.DESERT,Biomes.DESERT_HILLS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DOGS)) {
                generation.withStructure(DOGS_LAIR_FEATURE);
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_FOSSIL)) {
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FOSSIL_FEATURE);
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DESERT_BONES)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DESERT_BONES_FEATURE);
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.FOREST,Biomes.BIRCH_FOREST,Biomes.FLOWER_FOREST)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DANDELION_HILLS)) {
                generation.withStructure(DANDELION_HILL_FEATURE);
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.FOREST,Biomes.BIRCH_FOREST,Biomes.FLOWER_FOREST,Biomes.TALL_BIRCH_FOREST,Biomes.BIRCH_FOREST_HILLS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DANDELIONS)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DANDELION_FEATURE);
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.PLAINS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DANDELION_FIELDS)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DANDELION_FIELD_FEATURE);
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_TERROR_BIRDS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.TERROR_BRINGER, FCConfigurationValues.TERROR_BRINGER_SPAWN_WEIGHT.getValue(), FCConfigurationValues.TERROR_BRINGER_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.TERROR_BRINGER_SPAWN_MAX_COUNT.getValue()));
            }
        }


        if (WorldUtils.isBiomes(biome,Biomes.JUNGLE_HILLS,Biomes.JUNGLE,Biomes.JUNGLE_EDGE,Biomes.MODIFIED_JUNGLE_EDGE)) {
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.IGUANA, FCConfigurationValues.IGUANA_SPAWN_WEIGHT.getValue(), FCConfigurationValues.IGUANA_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.IGUANA_SPAWN_MAX_COUNT.getValue()));
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.JUNGLE_WASP, FCConfigurationValues.JUNGLE_WASP_SPAWN_WEIGHT.getValue(), FCConfigurationValues.JUNGLE_WASP_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.JUNGLE_WASP_SPAWN_MAX_COUNT.getValue()));
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_CROCUS_FLOWERS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.CROCUS_FLOWER, FCConfigurationValues.CROCUS_SPAWN_WEIGHT.getValue(), FCConfigurationValues.CROCUS_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.CROCUS_SPAWN_MAX_COUNT.getValue()));
            }
            generation.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION,BLOOMING_ORE);
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_IGUANA_KING_NESTS)) {
                generation.withStructure(IGUANA_KING_NEST_FEATURE);
            }
        }


        if (WorldUtils.isBiomes(biome,Biomes.BADLANDS,Biomes.BADLANDS_PLATEAU,Biomes.ERODED_BADLANDS,Biomes.MODIFIED_BADLANDS_PLATEAU,Biomes.WOODED_BADLANDS_PLATEAU)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_KROCKS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.KROCK, FCConfigurationValues.KROCK_SPAWN_WEIGHT.getValue(), FCConfigurationValues.KROCK_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.KROCK_SPAWN_MAX_COUNT.getValue()));
            }
        }
        if (WorldUtils.isBiomes(biome,Biomes.OCEAN,Biomes.DEEP_OCEAN,Biomes.DEEP_COLD_OCEAN,Biomes.DEEP_WARM_OCEAN,Biomes.DEEP_LUKEWARM_OCEAN,Biomes.WARM_OCEAN,Biomes.LUKEWARM_OCEAN,Biomes.COLD_OCEAN)){
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_SEA_SPONGES)) {
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SPONGE_FEATURE);
            }
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,AQUAMARINE_ORE);
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.SEA_DEVIL, FCConfigurationValues.SEA_DEVIL_SPAWN_WEIGHT.getValue(), FCConfigurationValues.SEA_DEVIL_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.SEA_DEVIL_SPAWN_MAX_COUNT.getValue()));
            event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(FCEntities.KELPY, FCConfigurationValues.KELPY_SPAWN_WEIGHT.getValue(), FCConfigurationValues.KELPY_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.KELPY_SPAWN_MAX_COUNT.getValue()));
        }

        if (WorldUtils.isBiomes(biome,Biomes.DEEP_OCEAN,Biomes.DEEP_COLD_OCEAN,Biomes.DEEP_WARM_OCEAN,Biomes.DEEP_LUKEWARM_OCEAN)){
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_AIR_SUCKERS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.AIR_SUCKER, FCConfigurationValues.AIR_SUCKER_SPAWN_WEIGHT.getValue(), FCConfigurationValues.AIR_SUCKER_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.AIR_SUCKER_SPAWN_MAX_COUNT.getValue()));
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_EATERS_OF_THE_DEPTHS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.EATER_OF_THE_DEPTHS, FCConfigurationValues.EATER_OF_THE_DEPTHS_SPAWN_WEIGHT.getValue(), FCConfigurationValues.EATER_OF_THE_DEPTHS_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.EATER_OF_THE_DEPTHS_SPAWN_MAX_COUNT.getValue()));
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.BADLANDS,Biomes.ERODED_BADLANDS,Biomes.BADLANDS_PLATEAU,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU)){
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_ANCIENT_TEMPLES)) {
                generation.withStructure(ANCIENT_RUNESTONE_TEMPLE_FEATURE);
            }
        }
        if (WorldUtils.isBiomes(biome,Biomes.RIVER,Biomes.SWAMP)){
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.WATER_BUG, FCConfigurationValues.WATER_BUG_SPAWN_WEIGHT.getValue(), FCConfigurationValues.WATER_BUG_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.WATER_BUG_SPAWN_MAX_COUNT.getValue()));
        }

        if (isEnabledInConfig(FCConfigurationValues.SPAWN_DEEP_ROCK)) {
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEEP_ROCK_FEATURE);
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,PRISMATIC_ORE);
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,VITA_ORE);

            if (isEnabledInConfig(FCConfigurationValues.SPAWN_NITER_ORES)) {
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, NITER_ORE_FEATURE);
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_SULFUR_ORES)) {
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SULFUR_ORE_FEATURE);
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.FOREST,Biomes.BIRCH_FOREST,Biomes.BIRCH_FOREST_HILLS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_SNOWBERRY)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SNOWBERRY_FEATURE);
            }
        }

        if (isEnabledInConfig(FCConfigurationValues.SPAWN_LIGHT_BULBS)) {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,LIGHT_BULB_FEATURE);
        }

        if (WorldUtils.isBiomes(biome,Biomes.SWAMP,Biomes.SWAMP_HILLS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_SPORE_MUSH)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SPORUM_FEATURE);
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_SPORE_ZOMBIE)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.INFECTED_ZOMBIE, FCConfigurationValues.INFECTED_ZOMBIE_SPAWN_WEIGHT.getValue(), FCConfigurationValues.INFECTED_ZOMBIE_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.INFECTED_ZOMBIE_SPAWN_MAX_COUNT.getValue()));
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.FLOWER_FOREST,Biomes.PLAINS)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_BEE_HIVE)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GIANT_HIVE_FEATURE);
            }
        }

        if (WorldUtils.isBiomes(biome,Biomes.FOREST,Biomes.GIANT_SPRUCE_TAIGA,Biomes.GIANT_SPRUCE_TAIGA_HILLS,Biomes.GIANT_TREE_TAIGA,Biomes.GIANT_TREE_TAIGA_HILLS,Biomes.TAIGA,Biomes.BIRCH_FOREST,Biomes.TALL_BIRCH_FOREST,Biomes.BIRCH_FOREST_HILLS,Biomes.FLOWER_FOREST)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_CICADAS)) {
                generation.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, CICADA_FEATURE);
            }
        }


        if (WorldUtils.isBiomes(biome,Biomes.DARK_FOREST,Biomes.DARK_FOREST_HILLS,Biomes.SNOWY_TUNDRA)) {
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_DEERS)) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FCEntities.DEER, FCConfigurationValues.DEER_SPAWN_WEIGHT.getValue(), FCConfigurationValues.DEER_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.DEER_SPAWN_MAX_COUNT.getValue()));
            }
        }
        if (WorldUtils.isBiomes(biome,Biomes.DARK_FOREST,Biomes.DARK_FOREST_HILLS)) {
            generation.withStructure(MUTATED_BLUEBERRY_FEATURE);
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_WOOD_ABOMINATIONS)) {
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.WOOD_ABOMINATION, FCConfigurationValues.WOOD_ABOMINATION_SPAWN_WEIGHT.getValue(), FCConfigurationValues.WOOD_ABOMINATION_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.WOOD_ABOMINATION_SPAWN_MAX_COUNT.getValue()));
            }
            if (isEnabledInConfig(FCConfigurationValues.SPAWN_BLUEBERRY)) {
                generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BLUEBERRY_FEATURE);
            }
        }
        if (isEnabledInConfig(FCConfigurationValues.SPAWN_GEM_GOLEMS)) {
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FCEntities.GEM_GOLEM, FCConfigurationValues.GEM_GOLEM_SPAWN_WEIGHT.getValue(), FCConfigurationValues.GEM_GOLEM_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.GEM_GOLEM_SPAWN_MAX_COUNT.getValue()));
        }
    }


}

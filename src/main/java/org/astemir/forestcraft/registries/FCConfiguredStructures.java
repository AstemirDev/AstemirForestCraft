package org.astemir.forestcraft.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCStructures;

import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

public class FCConfiguredStructures {

    public static final StructureFeature DOGS_LAIR_FEATURE = FCStructures.DOGS_LAIR.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature IGUANA_KING_NEST_FEATURE = FCStructures.IGUANA_KING_NEST.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature DANDELION_HILL_FEATURE = FCStructures.DANDELION_HILL.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature ANCIENT_RUNESTONE_TEMPLE_FEATURE = FCStructures.ANCIENT_RUNESTONE_TEMPLE.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature CROSSED_TREASURE_FEATURE = FCStructures.CROSSED_TREASURE.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature FERTILE_SOIL_FEATURE = FCStructures.FERTILE_SOIL.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature OLD_PLANTERS_FEATURE = FCStructures.OLD_PLANTERS.withConfiguration(NO_FEATURE_CONFIG);
    public static final StructureFeature MUTATED_BLUEBERRY_FEATURE = FCStructures.MUTATED_BLUEBERRY.withConfiguration(NO_FEATURE_CONFIG);

    public static void registerConfiguredStructures(){
        registerConfiguredStructure("configured_dogs_lair", FCStructures.DOGS_LAIR,DOGS_LAIR_FEATURE);
        registerConfiguredStructure("configured_iguana_king_nest", FCStructures.IGUANA_KING_NEST,IGUANA_KING_NEST_FEATURE);
        registerConfiguredStructure("configured_dandelion_hill",  FCStructures.DANDELION_HILL,DANDELION_HILL_FEATURE);
        registerConfiguredStructure("configured_ancient_temple", FCStructures.ANCIENT_RUNESTONE_TEMPLE,ANCIENT_RUNESTONE_TEMPLE_FEATURE);
        registerConfiguredStructure("configured_crossed_treasure", FCStructures.CROSSED_TREASURE,CROSSED_TREASURE_FEATURE);
        registerConfiguredStructure("configured_fertile_soil", FCStructures.FERTILE_SOIL,FERTILE_SOIL_FEATURE);
        registerConfiguredStructure("configured_old_planters", FCStructures.OLD_PLANTERS,OLD_PLANTERS_FEATURE);
        registerConfiguredStructure("configured_mutated_blueberry", FCStructures.MUTATED_BLUEBERRY,MUTATED_BLUEBERRY_FEATURE);
    }


    public static void registerConfiguredStructure(String name, Structure structure, StructureFeature feature){
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(ForestCraft.MOD_ID, name), feature);
        FlatGenerationSettings.STRUCTURES.put(structure,feature);
    }
}

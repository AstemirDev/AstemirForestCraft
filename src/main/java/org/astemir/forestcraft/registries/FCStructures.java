package org.astemir.forestcraft.registries;



import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.forestcraft.common.world.structure.*;
import org.astemir.forestcraft.configuration.FCConfigurationValues;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class FCStructures {


    public static final StructureDogsLair DOGS_LAIR = (StructureDogsLair) new StructureDogsLair().setRegistryName("forestcraft:dogs_lair");
    public static final StructureIguanaKingNest IGUANA_KING_NEST = (StructureIguanaKingNest) new StructureIguanaKingNest().setRegistryName("forestcraft:iguana_king_nest");
    public static final StructureDandelionHill DANDELION_HILL = (StructureDandelionHill) new StructureDandelionHill().setRegistryName("forestcraft:dandelion_hill");
    public static final StructureAncientRunestoneTemple ANCIENT_RUNESTONE_TEMPLE = (StructureAncientRunestoneTemple) new StructureAncientRunestoneTemple().setRegistryName("forestcraft:ancient_runestone_temple");
    public static final StructureCrossedTreasure CROSSED_TREASURE = (StructureCrossedTreasure) new StructureCrossedTreasure().setRegistryName("forestcraft:crossed_treasure");
    public static final StructureFertileSoil FERTILE_SOIL = (StructureFertileSoil) new StructureFertileSoil().setRegistryName("forestcraft:fertile_soil");
    public static final StructureOldPlanters OLD_PLANTERS = (StructureOldPlanters) new StructureOldPlanters().setRegistryName("forestcraft:old_planters");
    public static final StructureMutatedBlueberry MUTATED_BLUEBERRY = (StructureMutatedBlueberry) new StructureMutatedBlueberry().setRegistryName("forestcraft:mutated_blueberry");

    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Structure<?>> event) {
        try {
            for (Field f : FCStructures.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Structure) {
                    event.getRegistry().register((Structure<?>) obj);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setupMapSpacingAndLand(DOGS_LAIR, new StructureSeparationSettings( FCConfigurationValues.MAX_DOGS_CHUNK_DISTANCE.getValue(), FCConfigurationValues.MIN_DOGS_CHUNK_DISTANCE.getValue(), 823467110 ), true);
        setupMapSpacingAndLand(IGUANA_KING_NEST,new StructureSeparationSettings(20, 10, 923457210), true);
        setupMapSpacingAndLand(DANDELION_HILL,new StructureSeparationSettings(25, 20, 423471115), true);
        setupMapSpacingAndLand(ANCIENT_RUNESTONE_TEMPLE,new StructureSeparationSettings(50, 40, 133578120), true);
        setupMapSpacingAndLand(CROSSED_TREASURE,new StructureSeparationSettings(15, 10, 813588144), true);
        setupMapSpacingAndLand(FERTILE_SOIL,new StructureSeparationSettings(20, 15, 112585144), true);
        setupMapSpacingAndLand(OLD_PLANTERS,new StructureSeparationSettings(25, 20, 322515114), true);
        setupMapSpacingAndLand(MUTATED_BLUEBERRY,new StructureSeparationSettings(25, 20, 492511252), true);
        FCConfiguredStructures.registerConfiguredStructures();
    }


    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
        if (structure != null && structureSeparationSettings != null) {
            Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);
            if (transformSurroundingLand) {
                Structure.field_236384_t_ =
                        ImmutableList.<Structure<?>>builder()
                                .addAll(Structure.field_236384_t_)
                                .add(structure)
                                .build();
            }
            DimensionStructuresSettings.field_236191_b_ =
                    ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                            .putAll(DimensionStructuresSettings.field_236191_b_)
                            .put(structure, structureSeparationSettings)
                            .build();

            WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().field_236193_d_;
                if (structureMap instanceof ImmutableMap) {
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                    if (structure != null && structureSeparationSettings != null) {
                        tempMap.put(structure, structureSeparationSettings);
                        settings.getValue().getStructures().field_236193_d_ = tempMap;
                    }
                } else {
                    if (structureMap != null) {
                        if (structure != null && structureSeparationSettings != null) {
                            structureMap.put(structure, structureSeparationSettings);
                        }
                    }
                }
            });
        }
    }

}

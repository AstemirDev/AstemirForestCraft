package org.astemir.forestcraft.common.world.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEntities;

import java.util.List;

public class StructureDogsLair extends Structure<NoFeatureConfig> {

    private static final List<MobSpawnInfo.Spawners> SPAWN_LIST = ImmutableList.of(new MobSpawnInfo.Spawners(FCEntities.INSANE_DOG, FCConfigurationValues.INSANE_DOG_SPAWN_WEIGHT.getValue(), FCConfigurationValues.INSANE_DOG_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.INSANE_DOG_SPAWN_MAX_COUNT.getValue()),new MobSpawnInfo.Spawners(FCEntities.ALPHA_INSANE_DOG, FCConfigurationValues.ALPHA_INSANE_DOG_SPAWN_WEIGHT.getValue(), FCConfigurationValues.ALPHA_INSANE_DOG_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.ALPHA_INSANE_DOG_SPAWN_MAX_COUNT.getValue()));

    public StructureDogsLair() {
        super(NoFeatureConfig.field_236558_a_);
    }


    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return SPAWN_LIST;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return StructureDogsLair.Start::new;
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockpos = new BlockPos(x, 0, z);
            JigsawManager.func_242837_a(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(ForestCraft.MOD_ID, "dogs_lair/start_pool")),
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    blockpos,
                    this.components,
                    this.rand,
                    false,
                    true);
            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);
            this.recalculateStructureSize();
        }
    }
}

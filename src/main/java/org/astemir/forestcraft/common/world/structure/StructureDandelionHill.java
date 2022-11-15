package org.astemir.forestcraft.common.world.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEntities;

import java.util.List;
import java.util.Random;

public class StructureDandelionHill extends Structure<NoFeatureConfig> {

    private static final List<MobSpawnInfo.Spawners> SPAWN_LIST = ImmutableList.of(new MobSpawnInfo.Spawners(FCEntities.DANDELIONEER, FCConfigurationValues.DANDELIONEER_SPAWN_WEIGHT.getValue(), FCConfigurationValues.DANDELIONEER_SPAWN_MIN_COUNT.getValue(), FCConfigurationValues.DANDELIONEER_SPAWN_MAX_COUNT.getValue()));


    public StructureDandelionHill() {
        super(NoFeatureConfig.field_236558_a_);
    }


    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return SPAWN_LIST;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return StructureDandelionHill.Start::new;
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

            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            //ISeedReader world = (ISeedReader) chunkGenerator.func_230348_a_(x,z);
            //if (!hasWaterInPosition(world,new BlockPos(x,chunkGenerator.getGroundHeight(),z))) {
            /*
             * We pass this into func_242837_a to tell it where to generate the structure.
             * If func_242837_a's last parameter is true, blockpos's Y value is ignored and the
             * structure will spawn at terrain height instead. Set that parameter to false to
             * force the structure to spawn at blockpos's Y value instead. You got options here!
             */
            BlockPos blockpos = new BlockPos(x, -1, z);

            // All a structure has to do is call this method to turn it into a jigsaw based structure!
            JigsawManager.func_242837_a(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
                            // The path to the starting Template Pool JSON file to read.
                            //
                            // Note, this is "structure_tutorial:run_down_house/start_pool" which means
                            // the game will automatically look into the following path for the template pool:
                            // "resources/data/structure_tutorial/worldgen/template_pool/run_down_house/start_pool.json"
                            // This is why your pool files must be in "data/<modid>/worldgen/template_pool/<the path to the pool here>"
                            // because the game automatically will check in worldgen/template_pool for the pools.
                            .getOrDefault(new ResourceLocation(ForestCraft.MOD_ID, "dandelion_hill/start_pool")),

                            // How many pieces outward from center can a recursive jigsaw structure spawn.
                            // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
                            // However, I recommend you keep this a decent value like 10 so people can use datapacks to add additional pieces to your structure easily.
                            // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
                            10),
                    StructureDandelionHillPiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
                    this.components, // The list that will be populated with the jigsaw pieces after this method.
                    this.rand,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    true); // Place at heightmap (top land). Set this to false for structure to be place at blockpos's Y value instead

            this.components.forEach(piece -> piece.offset(0, 0, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

            this.recalculateStructureSize();
            //}
        }
    }


    public static class StructureDandelionHillPiece extends AbstractVillagePiece {

        public StructureDandelionHillPiece(TemplateManager p_i242036_1_, JigsawPiece p_i242036_2_, BlockPos p_i242036_3_, int p_i242036_4_, Rotation p_i242036_5_, MutableBoundingBox p_i242036_6_) {
            super(p_i242036_1_, p_i242036_2_, p_i242036_3_, p_i242036_4_, p_i242036_5_, p_i242036_6_);
        }


        public boolean func_230383_a_(ISeedReader reader, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) { ;
            return BlockPos.getAllInBox(boundingBox).filter((pos)->{
                FluidState state = reader.getFluidState(pos);
                if (state.isTagged(FluidTags.WATER)){
                    return true;
                }
                return false;
            }).count() == 0;
        }
    }
}

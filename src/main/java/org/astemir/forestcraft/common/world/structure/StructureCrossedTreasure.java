package org.astemir.forestcraft.common.world.structure;

import net.minecraft.block.Blocks;
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
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.astemir.forestcraft.ForestCraft;

import java.util.Random;


public class StructureCrossedTreasure extends Structure<NoFeatureConfig> {



    public StructureCrossedTreasure() {
        super(NoFeatureConfig.field_236558_a_);
    }



    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return StructureCrossedTreasure.Start::new;
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
            BlockPos blockpos = new BlockPos(x, -1, z);
            JigsawManager.func_242837_a(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(ForestCraft.MOD_ID, "crossed_treasure/start_pool")),
                            10),
                    StructureCrossedTreasurePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    blockpos,
                    this.components,
                    this.rand,
                    false,
                    true);
            this.components.forEach(piece -> piece.offset(0, 0, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

            this.recalculateStructureSize();
        }
    }


    public static class StructureCrossedTreasurePiece extends AbstractVillagePiece {

        public StructureCrossedTreasurePiece(TemplateManager p_i242036_1_, JigsawPiece p_i242036_2_, BlockPos p_i242036_3_, int p_i242036_4_, Rotation p_i242036_5_, MutableBoundingBox p_i242036_6_) {
            super(p_i242036_1_, p_i242036_2_, p_i242036_3_, p_i242036_4_, p_i242036_5_, p_i242036_6_);
        }

        public boolean func_230383_a_(ISeedReader reader, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) { ;
            boolean hasNoWater = BlockPos.getAllInBox(boundingBox).filter((pos)->{
                FluidState state = reader.getFluidState(pos);
                if (state.isTagged(FluidTags.WATER)){
                    return true;
                }
                return false;
            }).count() == 0;
            if (hasNoWater) {
                return reader.getBlockState(blockPos).equals(Blocks.SAND.getDefaultState());
            }
            return false;
        }
    }
}

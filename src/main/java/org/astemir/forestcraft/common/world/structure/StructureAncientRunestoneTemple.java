package org.astemir.forestcraft.common.world.structure;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import org.astemir.forestcraft.client.render.renderer.block.RendererAncientChest;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientMonument;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;


public class StructureAncientRunestoneTemple extends Structure<NoFeatureConfig> {



    public StructureAncientRunestoneTemple() {
        super(NoFeatureConfig.field_236558_a_);
    }



    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return StructureAncientRunestoneTemple.Start::new;
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
            BlockPos blockpos = new BlockPos(x, -20, z);
            JigsawManager.func_242837_a(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(ForestCraft.MOD_ID, "ancient_runestone_temple/start_pool")),
                            10),
                    StructureAncientRunestoneTemplePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    blockpos,
                    this.components,
                    this.rand,
                    false,
                    true);
            this.components.forEach(piece -> piece.offset(0, 0, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 20);

            this.recalculateStructureSize();
        }
    }


    public static class StructureAncientRunestoneTemplePiece extends AbstractVillagePiece {

        public StructureAncientRunestoneTemplePiece(TemplateManager p_i242036_1_, JigsawPiece p_i242036_2_, BlockPos p_i242036_3_, int p_i242036_4_, Rotation p_i242036_5_, MutableBoundingBox p_i242036_6_) {
            super(p_i242036_1_, p_i242036_2_, p_i242036_3_, p_i242036_4_, p_i242036_5_, p_i242036_6_);

        }

    }
}

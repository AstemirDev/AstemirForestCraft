package org.astemir.forestcraft.client.managers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.astemir.forestcraft.client.render.renderer.block.*;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCNewBlocks;
import org.astemir.forestcraft.registries.FCTileEntities;

public class FCBlockRenderManager {

    @OnlyIn(Dist.CLIENT)
    public static void register(){
        registerBlockRenderers();
        registerTileEntityRenderers();
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerBlockRenderers(){
        RenderTypeLookup.setRenderLayer(FCBlocks.MYSTERY_WOOD_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.MYSTERY_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.MYSTERY_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.BLUEBERRY_BUSH, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.BLUEBERRY_BUSH_EMPTY, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.SHARPED_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.LIGHT_BULB, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.POMIDOR_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.CUCUMBER_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.POTTED_LIGHT_BULB, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.POTTED_DANDELION_GROWN, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.GROWN_DANDELION, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.SPORUM, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.ANCIENT_MONUMENT, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.SNOWBERRY_BUSH,RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.GEM_CRYSTAL, RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.CLOUD_BLOCK, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.SKY_BRICKS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.SKY_STONE, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.SKY_WALL, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.SKY_TORCH,RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCNewBlocks.WALL_SKY_TORCH,RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.MYSTERY_WOOD_DOOR,RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.MYSTERY_WOOD_TRAPDOOR,RenderType.getCutout());
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerTileEntityRenderers(){
        ClientRegistry.bindTileEntityRenderer(FCTileEntities.IGUANA_KING_EGG, tileEntityRendererDispatcher -> new RendererIguanaKingEgg());
        ClientRegistry.bindTileEntityRenderer(FCTileEntities.ANCIENT_MONUMENT, tileEntityRendererDispatcher -> new RendererAncientMonument());
        ClientRegistry.bindTileEntityRenderer(FCTileEntities.ANCIENT_CHEST, tileEntityRendererDispatcher -> new RendererAncientChest());
        ClientRegistry.bindTileEntityRenderer(FCTileEntities.COSMIC_BEACON, tileEntityRendererDispatcher -> new RendererCosmicBeacon());
        ClientRegistry.bindTileEntityRenderer(FCTileEntities.GIANT_HIVE, tileEntityRendererDispatcher -> new RendererGiantHive());
    }
}

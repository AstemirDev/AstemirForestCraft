package org.astemir.forestcraft.client.managers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.item.renderer.*;
import org.astemir.forestcraft.client.FCItemModelTextures;
import org.astemir.forestcraft.client.render.model.item.ModelDarkMatter;
import org.astemir.forestcraft.client.render.model.item.ModelDaybreak;
import org.astemir.forestcraft.client.render.model.item.ModelOldCompass;
import org.astemir.forestcraft.client.render.model.item.ModelVileTentacle;
import org.astemir.forestcraft.client.render.renderer.item.RendererElegantBalloon;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.client.render.renderer.block.RendererAncientChest;
import org.astemir.forestcraft.client.render.renderer.block.RendererCosmicBeacon;
import org.astemir.forestcraft.client.render.renderer.block.RendererGiantHive;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientChest;
import org.astemir.forestcraft.common.tileentity.TileEntityCosmicBeacon;
import org.astemir.forestcraft.common.tileentity.TileEntityGiantHive;
import org.astemir.forestcraft.registries.FCNewBlocks;


@OnlyIn(Dist.CLIENT)
public class FCItemRenderManager extends ItemStackTileEntityRenderer {


    public static AItemStackRenderManager manager = new AItemStackRenderManager();

    public static void registerRenderers(){
        manager.registerRenderer(()->FCBlocks.ANCIENT_CHEST.asItem(),new AItemTileEntityRenderer(new TileEntityAncientChest(),new RendererAncientChest()));
        manager.registerRenderer(()-> FCNewBlocks.COSMIC_BEACON.asItem(),new AItemTileEntityRenderer(new TileEntityCosmicBeacon(),new RendererCosmicBeacon()));
        manager.registerRenderer(()->FCBlocks.GIANT_HIVE.asItem(), new AItemTileEntityRenderer(new TileEntityGiantHive(),new RendererGiantHive()));
        manager.registerRenderer(()-> FCItems.ARCHAIC_SHIELD,new AItemShieldRenderer(FCItemModelTextures.ANCIENT_RUNESTONE_SHIELD));
        manager.registerRenderer(()-> FCItems.ELEGANT_BALLOON,new RendererElegantBalloon(FCItemModelTextures.ELEGANT_BALLOON));
        manager.registerRenderer(() -> FCItems.DARK_MATTER, new AnimatedItemStackRenderer(FCItemModelTextures.DARK_MATTER,ModelDarkMatter.class));
        manager.registerRenderer(() -> FCItems.DAYBREAK, new AnimatedItemStackRenderer(FCItemModelTextures.DAYBREAK, ModelDaybreak.class));
        manager.registerRenderer(() -> FCItems.VILE_TENTACLE, new AnimatedItemStackRenderer(FCItemModelTextures.VILE_TENTACLE, ModelVileTentacle.class));
        manager.registerRenderer(() -> FCItems.OLD_COMPASS, new AnimatedItemStackRenderer(FCItemModelTextures.OLD_COMPASS, ModelOldCompass.class));

        manager.registerRenderer(()-> FCItems.HONEY_KEEPER,new AItemShieldRenderer(FCItemModelTextures.HONEY_KEEPER));
        manager.registerRenderer(()-> FCItems.PREHISTORIC_SHIELD,new AItemShieldRenderer(FCItemModelTextures.PREHISTORIC_SHIELD));
    }


    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        manager.render(stack,transform,matrixStack,buffer,combinedLight,combinedOverlay);
    }
}

package org.astemir.forestcraft.client.render.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.common.tileentity.TileEntityIguanaKingEgg;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RendererIguanaKingEgg extends TileEntityRenderer<TileEntityIguanaKingEgg> {


    public RendererIguanaKingEgg() {
        super(TileEntityRendererDispatcher.instance);
    }

    @Override
    public void render(TileEntityIguanaKingEgg tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tileEntityIn.getWorld();
        if (world != null) {
            BlockModelRenderer.enableCache();
            matrixStackIn.push();
            matrixStackIn.translate(-0.25f,0,-0.25f);
            matrixStackIn.scale(1.5f,1.5f,1.5f);
            matrixStackIn.rotate(Vector3f.ZP.rotation(-(float) Math.sin(tileEntityIn.getTicks()/2)/50));
            matrixStackIn.rotate(Vector3f.XP.rotation(-(float) Math.sin(tileEntityIn.getTicks()/2)/50));
            BlockRendererDispatcher blockRendererDispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
            blockRendererDispatcher.getBlockModelRenderer().renderModel(world, blockRendererDispatcher.getModelForState(tileEntityIn.getBlockState()), tileEntityIn.getBlockState(), tileEntityIn.getPos(), matrixStackIn, bufferIn.getBuffer(RenderType.getCutout()), false, new Random(), 0, OverlayTexture.NO_OVERLAY);
            matrixStackIn.pop();
            BlockModelRenderer.disableCache();
        }
    }
}

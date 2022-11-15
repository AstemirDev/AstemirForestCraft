package org.astemir.forestcraft.client.render.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.item.renderer.AnimatedTileEntityRenderer;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.block.ModelCosmicBeaconModel;
import org.astemir.forestcraft.common.tileentity.TileEntityCosmicBeacon;

@OnlyIn(Dist.CLIENT)
public class RendererCosmicBeacon extends AnimatedTileEntityRenderer<TileEntityCosmicBeacon, ModelCosmicBeaconModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cosmic_beacon/cosmic_beacon.png");
    protected static final ResourceLocation BEAM_TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cosmic_beacon/cosmic_beacon_beam.png");

    public RendererCosmicBeacon() {
        super(new ModelCosmicBeaconModel());
    }

    @Override
    public void render(TileEntityCosmicBeacon tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if (tileEntityIn.getFactory().isPlaying(TileEntityCosmicBeacon.LOOP)) {
            int j = 0;
            long i = tileEntityIn.getWorld().getGameTime();
            for (int k = 0; k < 200; ++k) {
                renderBeamSegment(matrixStackIn, bufferIn, partialTicks, i, j, k == 200 - 1 ? 1024 : 1, new float[]{166 / 255f, 19 / 255f, 171 / 255f});
                j += 1;
            }
        }
    }

    private static void renderBeamSegment(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, float partialTicks, long totalWorldTime, int yOffset, int height, float[] colors) {
        BeaconTileEntityRenderer.renderBeamSegment(matrixStackIn, bufferIn, BEAM_TEXTURE, partialTicks, 1.0F, totalWorldTime, yOffset, height, colors, 0.25F, 0.25F);
    }

    @Override
    public boolean isGlobalRenderer(TileEntityCosmicBeacon te) {
        return true;
    }

    @Override
    public ResourceLocation getTexture(TileEntityCosmicBeacon tileEntity) {
        return TEXTURE;
    }
}

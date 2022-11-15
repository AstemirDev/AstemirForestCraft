package org.astemir.api.client.item.renderer;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.astemir.api.client.animator.AnimatedTileEntityModel;
import org.astemir.api.common.tileentity.AnimatedTileEntity;

public abstract class AnimatedTileEntityRenderer<T extends AnimatedTileEntity,E extends AnimatedTileEntityModel> extends TileEntityRenderer<T> {

    private E model;

    public AnimatedTileEntityRenderer(E model) {
        super(TileEntityRendererDispatcher.instance);
        this.model = model;
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0.5f,1.5f,0.5f);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(180));
        model.animate(tileEntityIn,partialTicks);
        model.render(matrixStackIn,bufferIn.getBuffer(RenderType.getEntityCutout(getTexture(tileEntityIn))),combinedLightIn,combinedOverlayIn,1,1,1,1);
        matrixStackIn.pop();
    }

    @Override
    public boolean isGlobalRenderer(T te) {
        return true;
    }


    public abstract ResourceLocation getTexture(T tileEntity);
}

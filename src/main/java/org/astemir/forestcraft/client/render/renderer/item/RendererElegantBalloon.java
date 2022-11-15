package org.astemir.forestcraft.client.render.renderer.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.ItemStack;
import org.astemir.api.client.item.renderer.AItemStackRenderer;
import org.astemir.forestcraft.client.render.model.item.ModelElegantBalloon;

public class RendererElegantBalloon extends AItemStackRenderer {

    private final ModelElegantBalloon model;
    private final RenderMaterial material;

    public RendererElegantBalloon(RenderMaterial material) {
        this.material = material;
        this.model = new ModelElegantBalloon();
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.translate(0, -1.5f, 0);
        IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, this.model.getRenderType(material.getAtlasLocation()), true, stack.hasEffect()));
        model.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }
}

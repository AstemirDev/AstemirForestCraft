package org.astemir.api.client.item.renderer;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.ItemStack;

public class AItemShieldRenderer extends AItemStackRenderer {

    private final ShieldModel modelShield;
    private final RenderMaterial material;

    public AItemShieldRenderer(RenderMaterial material) {
        this.material = material;
        this.modelShield =  new ShieldModel();;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, this.modelShield.getRenderType(material.getAtlasLocation()), true, stack.hasEffect()));
        this.modelShield.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.modelShield.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

}

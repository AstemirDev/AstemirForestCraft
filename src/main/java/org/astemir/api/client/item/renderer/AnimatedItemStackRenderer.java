package org.astemir.api.client.item.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.astemir.api.AstemirAPI;

public class AnimatedItemStackRenderer extends AItemStackRenderer {

    private Class<? extends Model> modelClass;
    private RenderMaterial renderMaterial;

    public AnimatedItemStackRenderer(RenderMaterial texture,Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
        this.renderMaterial = texture;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Model model = AstemirAPI.CLIENT.ITEM_MODELS_HANDLER.getModelOrCreate(stack,modelClass);
        if (model != null) {
            IVertexBuilder ivertexbuilder = renderMaterial.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, model.getRenderType(renderMaterial.getAtlasLocation()), true, stack.hasEffect()));
            matrixStack.push();
            model.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStack.pop();
        }
    }
}

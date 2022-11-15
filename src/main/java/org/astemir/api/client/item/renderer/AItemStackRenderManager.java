package org.astemir.api.client.item.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AItemStackRenderManager {

    private Map<Supplier<Item>,AItemStackRenderer> renderers = new HashMap<>();


    public AItemStackRenderer registerRenderer(Supplier<Item> item, AItemStackRenderer renderer){
        this.renderers.put(item,renderer);
        return renderer;
    }

    public void render(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay){
        renderers.forEach((item,renderer)->{
            if (item.get().asItem().equals(stack.getItem())){
                renderer.render(stack,transform,matrixStack,buffer,combinedLight,combinedOverlay);
            }
        });
    }


    public Map<Supplier<Item>, AItemStackRenderer> getRenderers() {
        return renderers;
    }
}

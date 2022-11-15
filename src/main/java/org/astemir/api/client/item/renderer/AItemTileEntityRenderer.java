package org.astemir.api.client.item.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class AItemTileEntityRenderer extends AItemStackRenderer{

    private final TileEntity dummy;
    private final TileEntityRenderer renderer;

    public AItemTileEntityRenderer(TileEntity dummy,TileEntityRenderer renderer) {
        this.dummy = dummy;
        this.renderer = renderer;
    }

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        renderer.render(dummy,0,matrixStack,buffer,combinedLight,combinedOverlay);
    }
}

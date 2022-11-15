package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.FontRenderer;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;

public class UISprite extends UIElement {

    private RenderImage image;

    @Override
    public void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
        if (image() != null) {
            RenderSystem.pushMatrix();
            RenderSystem.color4f(color().r,color().g,color().b,color().a);
            MatrixStack matrix = new MatrixStack();
            matrix.push();
            RenderUtils.drawImage(image(), position(), new Vector2(windowPosX,windowPosY),scale(),centered());
            matrix.pop();
            RenderSystem.popMatrix();
        }
    }


    @Override
    public boolean clicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public RenderImage image() {
        return image;
    }

    public UISprite image(RenderImage image) {
        this.image = image;
        return this;
    }

}

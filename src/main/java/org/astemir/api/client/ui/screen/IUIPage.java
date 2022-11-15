package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

public interface IUIPage {

    void load(IUIContainer container);

    void unload(IUIContainer container);

    void render(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks);

    void clicked(double mouseX, double mouseY, int button);

    IUIContainer getContainer();
}

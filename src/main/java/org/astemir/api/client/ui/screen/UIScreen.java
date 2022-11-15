package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import org.astemir.api.client.Tween;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.math.Vector2;

public class UIScreen extends Screen implements IUIContainer{

    private IUIPage currentPage;
    private RenderImage screenBackground;
    private Vector2 windowSize = new Vector2(284,181);
    private Vector2 windowPosition = new Vector2(0,32);
    public Tween tween;


    protected UIScreen(ITextComponent titleIn, RenderImage screenBackground) {
        super(titleIn);
        this.screenBackground = screenBackground;
        this.tween = new Tween();
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(screenBackground.getTexture());
        int windowPosX = (int) ((int) ((this.width - getWindowSize().x) / 2)+getWindowPosition().x);
        int windowPosY = (int) getWindowPosition().y;
        this.blit(matrixStack, windowPosX,windowPosY , 0, 0, (int) getWindowSize().x, (int) getWindowSize().y, (int) screenBackground.getAtlasSize().x, (int) screenBackground.getAtlasSize().y);
        currentPage.render(matrixStack,mouseX,mouseY,windowPosX, windowPosY,partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        tween.update(partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        currentPage.clicked(mouseX,mouseY,button);
        return false;
    }

    @Override
    public void setCurrentPage(IUIPage page) {
        if (this.currentPage != null) {
            this.currentPage.unload(this);
        }
        this.currentPage = page;
        this.currentPage.load(this);
    }

    public void setScreenBackground(RenderImage screenBackground) {
        this.screenBackground = screenBackground;
    }

    public void setWindowSize(Vector2 windowSize) {
        this.windowSize = windowSize;
    }

    public void setWindowPosition(Vector2 windowPosition) {
        this.windowPosition = windowPosition;
    }

    public Vector2 getWindowSize() {
        return windowSize;
    }

    public Vector2 getWindowPosition() {
        return windowPosition;
    }

    @Override
    public Tween getTween() {
        return tween;
    }

    @Override
    public IUIPage getCurrentPage() {
        return currentPage;
    }
}

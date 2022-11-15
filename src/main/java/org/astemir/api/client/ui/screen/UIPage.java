package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.concurrent.CopyOnWriteArrayList;

public class UIPage implements IUIPage {

    private CopyOnWriteArrayList<UIElement> elements;
    private FontRenderer fontRenderer;
    private IUIContainer container;
    private long ticks = 0 ;


    public UIPage(IUIContainer container) {
        this.container = container;
        this.elements = new CopyOnWriteArrayList<>();
        this.fontRenderer = Minecraft.getInstance().fontRenderer;
    }

    public UIPage add(UIElement element){
        this.elements.add(element);
        return this;
    }


    @Override
    public void load(IUIContainer container) {
    }

    @Override
    public void unload(IUIContainer container) {
        elements.clear();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX,int windowPosY,float partialTicks){
        ticks++;
        elements.forEach((e)->{
            e.hover(mouseX, mouseY, windowPosX, windowPosY);
            e.render(matrixStack,fontRenderer,mouseX,mouseY,windowPosX,windowPosY,partialTicks);
        });
    }

    @Override
    public void clicked(double mouseX, double mouseY, int button) {
        if (ticks > 20) {
            elements.forEach((e) -> e.clicked(mouseX, mouseY, button));
        }
    }

    @Override
    public IUIContainer getContainer() {
        return container;
    }

}

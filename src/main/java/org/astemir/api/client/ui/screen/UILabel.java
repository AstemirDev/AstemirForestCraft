package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.api.utils.TextUtils;

public class UILabel extends UIElement {

    private ITextComponent[] lines;


    public UILabel text(ITextComponent component){
        String[] split = component.getString().split("\\|");
        ITextComponent[] components = new ITextComponent[split.length];
        for (int i = 0; i < split.length; i++) {
            components[i] = TextUtils.text(split[i]);
        }
        text(components);
        return this;
    }

    public UILabel text(ITextComponent[] component){
        this.lines = component;
        return this;
    }


    @Override
    public void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
        RenderUtils.drawText(lines,position(),new Vector2(windowPosX,windowPosY),scale(),2,centered());
    }


    @Override
    public boolean clicked(double mouseX, double mouseY, int button) {
        return false;
    }

}

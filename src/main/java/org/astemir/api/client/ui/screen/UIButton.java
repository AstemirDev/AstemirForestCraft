package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;

public class UIButton extends UISprite {

    private TranslationTextComponent buttonText;
    private ClickEvent event;
    private SoundEvent sound = SoundEvents.ITEM_BOOK_PAGE_TURN;
    private RenderImage hoverImage;

    @Override
    public void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
        super.render(matrixStack,fontRenderer,mouseX,mouseY,windowPosX,windowPosY,partialTicks);
        RenderSystem.pushMatrix();
            MatrixStack stack = new MatrixStack();
            stack.push();
                if (buttonText != null) {
                    RenderUtils.drawText(buttonText,position(),new Vector2(windowPosX,windowPosY),scale(),0,centered());
                    fontRenderer.func_243248_b(stack, buttonText, windowPosX+position().x, windowPosY+position().y, 0);
                }
            stack.pop();
        RenderSystem.popMatrix();
    }



    @Override
    public boolean clicked(double mouseX, double mouseY, int button) {
        boolean hovered = isHovered();
        if (hovered){
            Minecraft.getInstance().player.playSound(sound,1,1);
            if (event != null){
                return event.click(mouseX,mouseY,button);
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public void hover(double mouseX, double mouseY,double windowPosX,double windowPosY){
        if (image() == null) {
            float sizeX = textSize().x * scale().x;
            float sizeY = textSize().y * scale().y;
            if (centered()) {
                if (mouseX >= windowPosX + position().x-sizeX/2 && mouseY >= windowPosY + position().y-sizeY/2 && mouseX <= windowPosX + position().x-sizeX/2 + sizeX * scale().x && mouseY <= windowPosY + position().y-sizeY/2 + sizeY * scale().y) {
                    if (!isHovered()) {
                        setHovered(true);
                    }
                } else {
                    if (isHovered()) {
                        setHovered(false);
                    }
                }
            }else{
                if (mouseX >= windowPosX + position().x && mouseY >= windowPosY + position().y && mouseX <= windowPosX + position().x + sizeX * scale().x && mouseY <= windowPosY + position().y + sizeY * scale().y) {
                    if (!isHovered()) {
                        setHovered(true);
                    }
                } else {
                    if (isHovered()) {
                        setHovered(false);
                    }
                }
            }
        }else{
            super.hover(mouseX,mouseY,windowPosX,windowPosY);
        }
    }


    public Vector2 textSize(){
        if (buttonText != null) {
            String string = LanguageMap.getInstance().func_230503_a_(buttonText.getKey());
            return new Vector2(Minecraft.getInstance().fontRenderer.getStringWidth(string), Minecraft.getInstance().fontRenderer.FONT_HEIGHT);
        }
        return new Vector2(32,Minecraft.getInstance().fontRenderer.FONT_HEIGHT);
    }

    public TranslationTextComponent text() {
        return buttonText;
    }

    public UIButton text(TranslationTextComponent buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public UIButton click(ClickEvent e){
        this.event = e;
        return this;
    }

    public UIButton sound(SoundEvent sound){
        this.sound = sound;
        return this;
    }

    @Override
    public RenderImage image() {
        if (isHovered()){
            if (hoverImage != null) {
                return hoverImage();
            }
        }
        return super.image();
    }

    public RenderImage hoverImage() {
        return hoverImage;
    }

    public UISprite hoverImage(RenderImage hoverImage) {
        this.hoverImage = hoverImage;
        return this;
    }

}

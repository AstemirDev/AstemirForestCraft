package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import org.astemir.api.client.AColor;
import org.astemir.api.math.Vector2;

public abstract class UIElement {

    private Vector2 position = new Vector2(0,0);
    private Vector2 size = new Vector2(0,0);
    private Vector2 scale = new Vector2(1,1);
    private AColor color = AColor.WHITE;
    private boolean hovered = false;
    private boolean centered = false;
    private HoverEvent hoverEvent;




    public UIElement tweenPosition(IUIPage page,Vector2 a, Vector2 b, float t){
        page.getContainer().getTween().runTask(a, b, t, this::position);
        return this;
    }

    public UIElement tweenScale(IUIPage page,Vector2 a, Vector2 b, float t){
        page.getContainer().getTween().runTask(a, b, t, this::scale);
        return this;
    }

    public UIElement tweenColor(IUIPage page, AColor a, AColor b, float t){
        page.getContainer().getTween().runTask(a, b, t, this::color);
        return this;
    }


    public void hover(double mouseX, double mouseY,double windowPosX,double windowPosY){
        float sizeX = size().x*scale.x;
        float sizeY = size().y*scale.y;
        if (centered()) {
            if (mouseX >= windowPosX + position().x-sizeX/2 && mouseY >= windowPosY + position().y-sizeY/2 && mouseX <= windowPosX + position().x-sizeX/2 + sizeX && mouseY <= windowPosY + position().y-sizeY/2 + sizeY) {
                if (!hovered) {
                    setHovered(true);
                }
            } else {
                if (hovered) {
                    setHovered(false);
                }
            }
        }else{
            if (mouseX >= windowPosX + position().x && mouseY >= windowPosY + position().y && mouseX <= windowPosX + position().x + sizeX && mouseY <= windowPosY + position().y + sizeY) {
                if (!hovered) {
                    setHovered(true);
                }
            } else {
                if (hovered) {
                    setHovered(false);
                }
            }
        }
    }

    public abstract void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowWidth,int windowHeight,float partialTicks);

    public abstract boolean clicked(double mouseX, double mouseY, int button);

    public boolean isHovered() {
        return hovered;
    }

    public UIElement position(Vector2 position) {
        this.position = position;
        return this;
    }

    public UIElement size(Vector2 size) {
        this.size = size;
        return this;
    }

    public UIElement scale(Vector2 scale){
        this.scale = scale;
        return this;
    }

    public UIElement center(){
        this.centered = true;
        return this;
    }

    public UIElement color(AColor color){
        this.color = color;
        return this;
    }

    public Vector2 position() {
        return position;
    }

    public Vector2 size() {
        return size;
    }

    public Vector2 scale(){
        return scale;
    }

    public boolean centered(){
        return centered;
    }

    public AColor color(){return color;}

    public void setHovered(boolean b) {
        this.hovered = b;
        if (hoverEvent != null){
            hoverEvent.hover(this,hovered);
        }
    }

    public HoverEvent hoverEvent() {
        return hoverEvent;
    }

    public UIElement hoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
        return this;
    }
}

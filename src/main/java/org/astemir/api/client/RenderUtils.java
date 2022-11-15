package org.astemir.api.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.math.Vector2;
import org.astemir.api.utils.TextUtils;

public class RenderUtils {


    public static void drawEntity(LivingEntity livingEntity, Vector2 position, int scale, float ticks) {
        float f1 = -1;
        RenderSystem.pushMatrix();
        RenderSystem.translatef(position.x,position.y,1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.multiply(quaternion1);
        matrixstack.rotate(quaternion);
        float f4 = livingEntity.rotationPitch;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
        quaternion1.conjugate();
        entityrenderermanager.setCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        matrixstack.push();
        matrixstack.rotate(Vector3f.YP.rotationDegrees(210));
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.renderEntityStatic(livingEntity, 0.0D, 0.0D, 0.0D, 0, 0, matrixstack, irendertypebuffer$impl, 15728880);
        });
        matrixstack.pop();
        irendertypebuffer$impl.finish();
        entityrenderermanager.setRenderShadow(true);
        livingEntity.rotationPitch = f4;
        RenderSystem.popMatrix();
    }

    public static void drawImage(RenderImage image, Vector2 position, Vector2 windowPosition, Vector2 scale,boolean centered){
        Minecraft.getInstance().textureManager.bindTexture(image.getTexture());
        RenderSystem.pushMatrix();
        RenderSystem.translatef(windowPosition.x,windowPosition.y, 0.0F);
        RenderSystem.scalef(scale.x,scale.y,1);
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.push();
        if (centered){
            float width = image.getImageRect().getSize().x;
            float height = image.getImageRect().getSize().y;
            matrixStack.translate((position.x-(width*scale.x/2)) / scale.x,(position.y-(height*scale.y/2))/ scale.y,0);
            blit(matrixStack, 0, 0, image.getImageRect().getPosition().x, image.getImageRect().getPosition().y,  width, height, image.getAtlasSize().x, image.getAtlasSize().y);
        }else {
            matrixStack.translate(position.x / scale.x,position.y / scale.y,0);
            blit(matrixStack,0,0, image.getImageRect().getPosition().x, image.getImageRect().getPosition().y,  image.getImageRect().getSize().x, image.getImageRect().getSize().y, image.getAtlasSize().x, image.getAtlasSize().y);
        }
        matrixStack.pop();
        RenderSystem.popMatrix();
    }

    public static void drawImage(RenderImage image, Vector2 position, Vector2 windowPosition, Vector2 size,Vector2 scale){
        Minecraft.getInstance().textureManager.bindTexture(image.getTexture());
        RenderSystem.pushMatrix();
        RenderSystem.translatef(windowPosition.x,windowPosition.y, 0.0F);
        RenderSystem.scalef(scale.x,scale.y,1);
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.push();
        float width = size.x;
        float height = size.y;
        matrixStack.translate((position.x-(width*scale.x/2)) / scale.x,(position.y-(height*scale.y/2))/ scale.y,0);
        blit(matrixStack, 0, 0, image.getImageRect().getPosition().x, image.getImageRect().getPosition().y,  image.getImageRect().getSize().x,  image.getImageRect().getSize().y,  image.getAtlasSize().x, image.getAtlasSize().y);
        matrixStack.pop();
        RenderSystem.popMatrix();
    }

    public static void drawTextZIndex(ITextComponent[] text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing,boolean centered,int zIndex){
        FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
        int linePos = 0;
        for (ITextComponent ITextComponent : text) {
            RenderSystem.pushMatrix();
            MatrixStack stack = new MatrixStack();
            stack.translate(0.0D, 0.0D, zIndex);
            stack.push();
            RenderSystem.translatef(windowPosition.x,windowPosition.y, 0.0F);
            RenderSystem.scalef(scale.x,scale.y,1);
            if (centered) {
                int textWidth = TextUtils.getWidth(fontRenderer, (TextComponent) ITextComponent);
                fontRenderer.func_243248_b(stack, ITextComponent, (position.x - (textWidth*scale.x / 2))/scale.x, (position.y+linePos)/scale.y, 0);
            }else {
                fontRenderer.func_243248_b(stack, ITextComponent, (position.x)/scale.x, (position.y+linePos)/scale.y, 0);
            }
            linePos+=(fontRenderer.FONT_HEIGHT+spacing)*scale.y;
            stack.pop();
            RenderSystem.popMatrix();
        }
    }

    public static void drawText(ITextComponent[] text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing,boolean centered){
        FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
        int linePos = 0;
        for (ITextComponent ITextComponent : text) {
            RenderSystem.pushMatrix();
            MatrixStack stack = new MatrixStack();
            stack.push();
            RenderSystem.translatef(windowPosition.x,windowPosition.y, 0.0F);
            RenderSystem.scalef(scale.x,scale.y,1);
            if (centered) {
                int textWidth = TextUtils.getWidth(fontRenderer, (TextComponent) ITextComponent);
                fontRenderer.func_243248_b(stack, ITextComponent, (position.x - (textWidth*scale.x / 2))/scale.x, (position.y+linePos)/scale.y, 0);
            }else {
                fontRenderer.func_243248_b(stack, ITextComponent, (position.x)/scale.x, (position.y+linePos)/scale.y, 0);
            }
            linePos+=(fontRenderer.FONT_HEIGHT+spacing)*scale.y;
            stack.pop();
            RenderSystem.popMatrix();
        }
    }

    public static void drawText(ITextComponent[] text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing){
        drawText(text,position,windowPosition,scale,spacing,false);
    }

    public static void drawText(ITextComponent[] text,Vector2 position, Vector2 windowPosition, Vector2 scale){
        drawText(text,position,windowPosition,scale,2,false);
    }

    public static void drawText(ITextComponent text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing,boolean centered) {
        drawText(new ITextComponent[]{text},position,windowPosition,scale,spacing,centered);
    }

    public static void drawTextZIndex(ITextComponent text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing,boolean centered,int zIndex) {
        drawTextZIndex(new ITextComponent[]{text},position,windowPosition,scale,spacing,centered,zIndex);
    }


    public static void drawText(ITextComponent text,Vector2 position, Vector2 windowPosition, Vector2 scale, double spacing){
        drawText(text,position,windowPosition,scale,spacing,false);
    }

    public static void drawText(ITextComponent text,Vector2 position, Vector2 windowPosition, Vector2 scale){
        drawText(text,position,windowPosition,scale,2,false);
    }

    public static void drawItemStack(ItemStack stack, Vector2 position,Vector2 windowPosition ,int scale) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef(windowPosition.x, windowPosition.y, 32.0F);
        RenderSystem.scalef((float)scale, (float)scale,1);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.push();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = Minecraft.getInstance().fontRenderer;
        itemRenderer.renderItemAndEffectIntoGUI(stack, (int)position.x/scale, (int)position.y/scale);
        itemRenderer.renderItemOverlayIntoGUI(font, stack, (int)position.x/scale, (int)position.y/scale, "");
        itemRenderer.zLevel = 0.0F;
        matrixstack.pop();
        RenderSystem.popMatrix();
    }


    public static void blit(MatrixStack matrixStack, float x, float y, int blitOffset, float uOffset, float vOffset, float uWidth, float vHeight, float textureHeight, float textureWidth) {
        innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(MatrixStack matrixStack, float x, float y, float width, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
        innerBlit(matrixStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(MatrixStack matrixStack, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        blit(matrixStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    private static void innerBlit(MatrixStack matrixStack, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
        innerBlit(matrixStack.getLast().getMatrix(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(matrix, x1, y2, blitOffset).tex(minU, maxV).endVertex();
        bufferbuilder.pos(matrix, x2, y2, blitOffset).tex(maxU, maxV).endVertex();
        bufferbuilder.pos(matrix, x2, y1, blitOffset).tex(maxU, minV).endVertex();
        bufferbuilder.pos(matrix, x1, y1, blitOffset).tex(minU, minV).endVertex();
        bufferbuilder.finishDrawing();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.draw(bufferbuilder);
    }
}

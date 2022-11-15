package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.projectiles.ModelBakudanBall;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityBakudanBall;

@OnlyIn(Dist.CLIENT)
public class RendererBakudanBall extends EntityRenderer<EntityBakudanBall> {

    public static final ResourceLocation BALL = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/bakudan/bakudan.png");
    public static final ResourceLocation RED_BALL = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/bakudan/true_bakudan_ball.png");

    private ModelBakudanBall ballModel = new ModelBakudanBall();

    public RendererBakudanBall(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }


    @Override
    public void render(EntityBakudanBall entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0,-1,0);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(entityIn.rotationYaw));
        ballModel.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySolid(getEntityTexture(entityIn))), packedLightIn, getPackedOverlay(getOverlayProgress(entityIn)),1.0F, 1.0F, 1.0F, 1.0F);
        ballModel.getBall().rotateAngleX = -((float) Math.abs(entityIn.getMotion().x)+(float)Math.abs(entityIn.getMotion().z)*100);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    public static int getPackedOverlay(float uIn) {
        return OverlayTexture.getPackedUV(OverlayTexture.getU(uIn), OverlayTexture.getV(false));
    }

    protected float getOverlayProgress(EntityBakudanBall entity) {
        if (entity.ticksExisted > 50){
            return (float)Math.sin(entity.ticksExisted);
        }
        return 0;
    }



    @Override
    public ResourceLocation getEntityTexture(EntityBakudanBall entity) {
        if (entity.isRed()){
            return RED_BALL;
        }
        return BALL;
    }
}


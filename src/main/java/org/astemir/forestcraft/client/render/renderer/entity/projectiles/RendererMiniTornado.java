package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.projectiles.ModelMiniTornado;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityMiniTornadoProjectile;

public class RendererMiniTornado extends EntityRenderer<EntityMiniTornadoProjectile> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/mini_tornado.png");

    private ModelMiniTornado model = new ModelMiniTornado();

    public RendererMiniTornado(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityMiniTornadoProjectile entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0,1.25f,0);
        matrixStackIn.rotate(Vector3f.XN.rotationDegrees(180f));
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(entityIn.ticksExisted*10));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
        model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucent(getEntityTexture(entityIn))), packedLightIn, OverlayTexture.NO_OVERLAY,1.0F, 1.0F, 1.0F, 1.0F);
        model.setRotationAngles(entityIn,0,0,partialTicks,0,0);
        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityMiniTornadoProjectile entity) {
        return TEXTURE;
    }
}

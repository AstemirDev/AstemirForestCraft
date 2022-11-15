package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.projectiles.ModelAncientRune;
import org.astemir.forestcraft.common.entities.projectiles.other.DaybreakProjectile;

public class RendererAncientRune extends EntityRenderer<DaybreakProjectile> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/ancient_rune.png");

    private ModelAncientRune model = new ModelAncientRune();

    public RendererAncientRune(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(DaybreakProjectile entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0,2,0);
        matrixStackIn.scale(2,-2,2);
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(entityIn.ticksExisted*20));
        model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucent(getEntityTexture(entityIn))), packedLightIn, OverlayTexture.NO_OVERLAY,1.0F, 1.0F, 1.0F, 1.0F);
        model.setRotationAngles(entityIn,0,0,partialTicks,0,0);
        matrixStackIn.pop();
    }


    @Override
    public ResourceLocation getEntityTexture(DaybreakProjectile entity) {
        return TEXTURE;
    }
}

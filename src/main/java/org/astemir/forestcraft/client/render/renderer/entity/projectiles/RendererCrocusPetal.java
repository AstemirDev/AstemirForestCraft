package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.projectiles.ModelCrocusPetal;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCrocusPetal;

@OnlyIn(Dist.CLIENT)
public class RendererCrocusPetal extends EntityRenderer<EntityCrocusPetal> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/crocus_petal.png");
    private static final ModelCrocusPetal MODEL = new ModelCrocusPetal();

    public RendererCrocusPetal(EntityRendererManager renderManager) {
        super(renderManager);
    }


    @Override
    public void render(EntityCrocusPetal entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0,-1.15,0);
        MODEL.setRotationAngles(entityIn, 0,0,entityIn.ticksExisted,0,0);
        MODEL.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySolid(getEntityTexture(entityIn))), packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
    }



    @Override
    public ResourceLocation getEntityTexture(EntityCrocusPetal entity) {
        return TEXTURE;
    }
}

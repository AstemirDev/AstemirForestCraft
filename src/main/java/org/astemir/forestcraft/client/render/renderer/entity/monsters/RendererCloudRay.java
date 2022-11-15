package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelCloudRay;
import org.astemir.forestcraft.common.entities.monsters.EntityCloudRay;

@OnlyIn(Dist.CLIENT)
public class RendererCloudRay extends MobRenderer<EntityCloudRay, ModelCloudRay> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cloudray.png");


    public RendererCloudRay(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelCloudRay(), 0.25f);
    }



    @Override
    public void render(EntityCloudRay entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCloudRay entity) {
        return TEXTURE;
    }
}

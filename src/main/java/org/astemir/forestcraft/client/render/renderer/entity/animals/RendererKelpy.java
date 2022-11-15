package org.astemir.forestcraft.client.render.renderer.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelKelpy;
import org.astemir.forestcraft.common.entities.animals.EntityKelpy;

@OnlyIn(Dist.CLIENT)
public class RendererKelpy extends MobRenderer<EntityKelpy, ModelKelpy> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/kelpy.png");


    public RendererKelpy(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelKelpy(), 0.45f);
    }


    @Override
    protected void preRenderCallback(EntityKelpy entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        double i = 0.75f;
        float f = 1.0F + 0.15F * (float) i;
        matrixStackIn.scale(f, f, f);
    }

    @Override
    public void render(EntityKelpy entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityKelpy entity) {
        return TEXTURE;
    }
}

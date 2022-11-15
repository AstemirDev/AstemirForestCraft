package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelTerrorBringer;
import org.astemir.forestcraft.common.entities.monsters.EntityTerrorBringer;


@OnlyIn(Dist.CLIENT)
public class RendererTerrorBringer extends MobRenderer<EntityTerrorBringer, ModelTerrorBringer> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/terror_bringer/terror_bringer_0.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/terror_bringer/terror_bringer_1.png");
    protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/terror_bringer/terror_bringer_2.png");
    protected static final ResourceLocation TEXTURE_3 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/terror_bringer/terror_bringer_3.png");
    protected static final ResourceLocation GRAND_TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/terror_bringer/grand_terror_bringer.png");

    public RendererTerrorBringer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelTerrorBringer(), 0.5f);
    }

    @Override
    public void render(EntityTerrorBringer entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void preRenderCallback(EntityTerrorBringer entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        if (entitylivingbaseIn.getSkinType() == 4){
            double i = 1.75f;
            float f = 1.0F + 0.15F * (float) i;
            matrixStackIn.scale(f, f, f);
        }else {
            double i = 1.25f;
            float f = 1.0F + 0.15F * (float) i;
            matrixStackIn.scale(f, f, f);
        }
    }

    @Override
    protected void applyRotations(EntityTerrorBringer entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityTerrorBringer entity) {
        switch (entity.getSkinType()){
            case 0:
                return TEXTURE_0;
            case 1:
                return TEXTURE_1;
            case 2:
                return TEXTURE_2;
            case 3:
                return TEXTURE_3;
            case 4:
                return GRAND_TEXTURE;
        }
        return TEXTURE_0;
    }

}

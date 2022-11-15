package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerAlphaInsaneDogEyes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelAlphaInsaneDog;
import org.astemir.forestcraft.common.entities.monsters.EntityAlphaInsaneDog;


@OnlyIn(Dist.CLIENT)
public class RendererAlphaInsaneDog extends MobRenderer<EntityAlphaInsaneDog, ModelAlphaInsaneDog> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/alpha_insane_dog/alpha_insane_dog.png");

    public RendererAlphaInsaneDog(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelAlphaInsaneDog(), 0.8f);
        this.addLayer(new LayerAlphaInsaneDogEyes<>(this));
    }

    @Override
    public void render(EntityAlphaInsaneDog entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void applyRotations(EntityAlphaInsaneDog entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAlphaInsaneDog entity) {
        return TEXTURE;
    }
}

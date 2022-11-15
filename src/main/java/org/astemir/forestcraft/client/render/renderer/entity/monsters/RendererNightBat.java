package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerNightBat;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelNightBat;
import org.astemir.forestcraft.common.entities.monsters.EntityNightBat;

@OnlyIn(Dist.CLIENT)
public class RendererNightBat extends MobRenderer<EntityNightBat, ModelNightBat> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/night_bat/night_bat.png");

    public RendererNightBat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelNightBat(), 0.4F);
        this.addLayer(new LayerNightBat(this));
    }


    protected void preRenderCallback(EntityNightBat entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.8F, 0.8F, 0.8F);
    }

    protected void applyRotations(EntityNightBat entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        matrixStackIn.translate(0.0D, (double)(MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getEntityTexture(EntityNightBat entity) {
        return TEXTURE;
    }
}

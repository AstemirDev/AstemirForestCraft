package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelNetherBat;
import org.astemir.forestcraft.common.entities.monsters.EntityNetherBat;

@OnlyIn(Dist.CLIENT)
public class RendererNetherBat extends MobRenderer<EntityNetherBat, ModelNetherBat> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/nether_bat.png");

    public RendererNetherBat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelNetherBat(), 0.25F);
    }

    @Override
    protected int getBlockLight(EntityNetherBat entityIn, BlockPos partialTicks) {
        return 15;
    }


    @Override
    protected void preRenderCallback(EntityNetherBat entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.5F, 0.5F, 0.5F);
    }

    protected void applyRotations(EntityNetherBat entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        matrixStackIn.translate(0.0D, (double)(MathHelper.cos(ageInTicks * 0.3F) * 0.1F), 0.0D);
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityNetherBat entity) {
        return TEXTURE;
    }
}

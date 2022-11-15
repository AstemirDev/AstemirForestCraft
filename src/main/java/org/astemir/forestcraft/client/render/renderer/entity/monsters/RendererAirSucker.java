package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerAirSuckerEyes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelAirSucker;
import org.astemir.forestcraft.common.entities.monsters.EntityAirSucker;

@OnlyIn(Dist.CLIENT)
public class RendererAirSucker extends MobRenderer<EntityAirSucker, ModelAirSucker> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/air_sucker/air_sucker.png");

    public RendererAirSucker(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelAirSucker(), 0.75f);
        this.addLayer(new LayerAirSuckerEyes<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAirSucker entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(EntityAirSucker entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (!entityLiving.isSucked()) {
            float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
            float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);
            matrixStackIn.translate(0, 0.5D, 0);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
            matrixStackIn.translate(0.0D, (double) -1.2F, 0.0D);
        }
    }

    @Override
    protected float handleRotationFloat(EntityAirSucker livingBase, float partialTicks) {
        return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
    }
}

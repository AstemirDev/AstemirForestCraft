package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerEaterOfTheDepths;
import org.astemir.forestcraft.client.render.layer.LayerSeaDevil;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelEaterOfTheDepths;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelSeaDevil;
import org.astemir.forestcraft.common.entities.monsters.EntityEaterOfTheDepths;
import org.astemir.forestcraft.common.entities.monsters.EntitySeaDevil;

@OnlyIn(Dist.CLIENT)
public class RendererSeaDevil extends MobRenderer<EntitySeaDevil, ModelSeaDevil> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/sea_devil/sea_devil.png");

    public RendererSeaDevil(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelSeaDevil(), 0.5f);
        this.addLayer(new LayerSeaDevil(this));
    }

    @Override
    protected void applyRotations(EntitySeaDevil entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-entityLiving.rotationPitch));
    }


    @Override
    public ResourceLocation getEntityTexture(EntitySeaDevil entity) {
        return TEXTURE;
    }
}

package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerWoodAbominationEyes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelWaterBug;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelWoodAbomination;
import org.astemir.forestcraft.common.entities.monsters.EntitySeaDevil;
import org.astemir.forestcraft.common.entities.monsters.EntityWaterBug;
import org.astemir.forestcraft.common.entities.monsters.EntityWoodAbomination;

@OnlyIn(Dist.CLIENT)
public class RendererWaterBug extends MobRenderer<EntityWaterBug, ModelWaterBug> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/water_bug.png");

    public RendererWaterBug(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelWaterBug(), 0.7f);
    }


    @Override
    protected void applyRotations(EntityWaterBug entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-entityLiving.rotationPitch));
    }


    @Override
    public ResourceLocation getEntityTexture(EntityWaterBug entity) {
        return TEXTURE;
    }
}

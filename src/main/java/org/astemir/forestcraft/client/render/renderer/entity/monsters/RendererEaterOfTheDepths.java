package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.math.MathUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerEaterOfTheDepths;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelEaterOfTheDepths;
import org.astemir.forestcraft.common.entities.monsters.EntityEaterOfTheDepths;

@OnlyIn(Dist.CLIENT)
public class RendererEaterOfTheDepths extends MobRenderer<EntityEaterOfTheDepths, ModelEaterOfTheDepths> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/eater_of_the_depths/eater_of_the_depths.png");

    public RendererEaterOfTheDepths(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelEaterOfTheDepths(), 0.7f);
        this.addLayer(new LayerEaterOfTheDepths(this));
    }

    @Override
    protected void applyRotations(EntityEaterOfTheDepths entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-entityLiving.rotationPitch));
    }


    @Override
    public ResourceLocation getEntityTexture(EntityEaterOfTheDepths entity) {
        return TEXTURE;
    }
}

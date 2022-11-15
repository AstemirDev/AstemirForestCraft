package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerThunderScreamerCharge;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelThunderScreamer;
import org.astemir.forestcraft.common.entities.monsters.EntityThunderScreamer;



@OnlyIn(Dist.CLIENT)
public class RendererThunderScreamer extends MobRenderer<EntityThunderScreamer, ModelThunderScreamer> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/thunderscreamer.png");

    public RendererThunderScreamer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelThunderScreamer(), 0.7f);
        addLayer(new LayerThunderScreamerCharge(this));
    }

    @Override
    protected void applyRotations(EntityThunderScreamer entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityLiving.rotationPitch));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityThunderScreamer entity) {
        return TEXTURE;
    }
}

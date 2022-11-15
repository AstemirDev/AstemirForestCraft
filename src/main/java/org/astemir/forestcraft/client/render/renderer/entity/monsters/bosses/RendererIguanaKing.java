package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelIguanaKing;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityIguanaKing;

@OnlyIn(Dist.CLIENT)
public class RendererIguanaKing extends MobRenderer<EntityIguanaKing, ModelIguanaKing> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/iguana_king.png");

    public RendererIguanaKing(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelIguanaKing(), 1f);
    }

    @Override
    protected void applyRotations(EntityIguanaKing entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.isDeadAnimationPlaying()) {
            if (entityLiving.deathTime <= 20) {
                matrixStackIn.rotate(Vector3f.XP.rotation((float)Math.toRadians((-4.5 * entityLiving.deathTime))));
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
            }else{
                matrixStackIn.rotate(Vector3f.XP.rotation((float)Math.toRadians(-90)));
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
            }
            matrixStackIn.translate(0,-0.8,0);
        }else{
            super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(EntityIguanaKing entity) {
        return TEXTURE;
    }



    public static int getPackedOverlay(LivingEntity livingEntityIn, float uIn) {
        return OverlayTexture.getPackedUV(OverlayTexture.getU(uIn), OverlayTexture.getV(livingEntityIn.hurtTime > 0 || livingEntityIn.deathTime > 0));
    }
}

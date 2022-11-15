package org.astemir.forestcraft.client.render.renderer.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelCicada;
import org.astemir.forestcraft.common.entities.animals.EntityCicada;

@OnlyIn(Dist.CLIENT)
public class RendererCicada extends MobRenderer<EntityCicada, ModelCicada> {

    protected static final ResourceLocation TEXTURE_A = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cicada/cicada_0.png");
    protected static final ResourceLocation TEXTURE_B = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cicada/cicada_1.png");
    protected static final ResourceLocation TEXTURE_C = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cicada/cicada_2.png");

    public RendererCicada(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelCicada(), 0.25f);
    }

    @Override
    protected void applyRotations(EntityCicada entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (!entityLiving.isHangingOnTree()) {
            super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityLiving.rotationPitch));
        }else{
            switch (entityLiving.getHangingPose()){
                case 0:{
                    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90));
                    break;
                }
                case 1:{
                    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(270));
                    break;
                }
                case 2:{
                    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180));
                    break;
                }
                case 3:{
                    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(0));
                    break;
                }
            }
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90));
        }
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCicada entity) {
        return entity.getSkinType() == 0 ? TEXTURE_A : entity.getSkinType() == 1 ? TEXTURE_B : entity.getSkinType() == 2 ? TEXTURE_C : TEXTURE_A;
    }
}

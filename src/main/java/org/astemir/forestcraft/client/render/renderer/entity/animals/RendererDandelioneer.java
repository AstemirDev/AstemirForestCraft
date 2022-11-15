package org.astemir.forestcraft.client.render.renderer.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelBabyDandelioneer;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelDandelioneer;
import org.astemir.forestcraft.common.entities.animals.EntityDandelioneer;

@OnlyIn(Dist.CLIENT)
public class RendererDandelioneer<T extends EntityModel<EntityDandelioneer>> extends MobRenderer<EntityDandelioneer, T>{

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/dandelioneer/dandelioneer.png");
    protected static final ResourceLocation BABY_TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/dandelioneer/dandelioneer_baby.png");
    private static final ModelDandelioneer MODEL = new ModelDandelioneer();
    private static final ModelBabyDandelioneer BABY_MODEL = new ModelBabyDandelioneer();

    public RendererDandelioneer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,(T)MODEL ,0.5f);
    }

    @Override
    public void render(EntityDandelioneer entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isChild()){
            entityModel = (T)BABY_MODEL;
        }else{
            entityModel = (T)MODEL;
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }



    @Override
    public ResourceLocation getEntityTexture(EntityDandelioneer entity) {
        if (entity.isChild()){
            return BABY_TEXTURE;
        }else {
            return TEXTURE;
        }
    }
}

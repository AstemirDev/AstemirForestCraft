package org.astemir.forestcraft.client.render.renderer.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelWorm;
import org.astemir.forestcraft.common.entities.animals.EntityWorm;

@OnlyIn(Dist.CLIENT)
public class RendererWorm extends MobRenderer<EntityWorm, ModelWorm> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/worm/worm.png");
    protected static final ResourceLocation GOLDEN = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/worm/golden_worm.png");
    protected static final ResourceLocation DIAMOND = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/worm/diamond_worm.png");


    public RendererWorm(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelWorm(), 0.25f);
    }



    @Override
    public void render(EntityWorm entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityWorm entity) {
        switch (entity.getWormType()){
            case 0: return TEXTURE;
            case 1: return GOLDEN;
            case 2: return DIAMOND;
        }
        return TEXTURE;
    }
}

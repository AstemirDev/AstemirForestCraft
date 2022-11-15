package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerInsaneDogEyes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelInsaneDog;
import org.astemir.forestcraft.common.entities.monsters.EntityInsaneDog;
import org.astemir.api.math.RandomUtils;


@OnlyIn(Dist.CLIENT)
public class RendererInsaneDog extends MobRenderer<EntityInsaneDog, ModelInsaneDog> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/insane_dog/insane_dog.png");

    public RendererInsaneDog(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelInsaneDog(), 0.5f);
        this.addLayer(new LayerInsaneDogEyes<>(this));
    }

    @Override
    public void render(EntityInsaneDog entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void applyRotations(EntityInsaneDog entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.getLazerTicks() > 0){
            matrixStackIn.scale(1+ RandomUtils.randomFloat(0.05f,-0.05f),1+RandomUtils.randomFloat(0.05f,-0.05f),1+RandomUtils.randomFloat(0.05f,-0.05f));
        }
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityInsaneDog entity) {
        return TEXTURE;
    }
}

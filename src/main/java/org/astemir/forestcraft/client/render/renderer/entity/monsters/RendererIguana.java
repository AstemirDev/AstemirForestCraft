package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelIguana;
import org.astemir.forestcraft.common.entities.monsters.EntityIguana;

@OnlyIn(Dist.CLIENT)
public class RendererIguana extends MobRenderer<EntityIguana, ModelIguana> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/iguana.png");


    public RendererIguana(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelIguana(), 0.25f);
    }



    @Override
    public void render(EntityIguana entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityIguana entity) {
        return TEXTURE;
    }
}

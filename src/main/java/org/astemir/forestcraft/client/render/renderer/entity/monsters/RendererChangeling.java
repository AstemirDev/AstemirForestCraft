package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelChangeling;
import org.astemir.forestcraft.common.entities.monsters.EntityChangeling;


@OnlyIn(Dist.CLIENT)
public class RendererChangeling extends MobRenderer<EntityChangeling, ModelChangeling> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/changeling/changeling.png");
    protected static final ResourceLocation TEXTURE_PARASITE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/changeling/changeling_parasite.png");


    public RendererChangeling(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelChangeling(), 0.5f);
    }

    @Override
    public void render(EntityChangeling entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityChangeling entity) {
        if (entity.hasHarmedSkin()){
            return TEXTURE_PARASITE;
        }
        return TEXTURE;
    }
}

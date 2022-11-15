package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelCosmicFiend;
import org.astemir.forestcraft.client.render.layer.LayerCosmicFiend;
import org.astemir.forestcraft.client.render.renderer.FCGhostRenderer;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityCosmicFiend;

@OnlyIn(Dist.CLIENT)
public class RendererCosmicFiend extends FCGhostRenderer<EntityCosmicFiend, ModelCosmicFiend> {



    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/cosmic_fiend/cosmic_fiend.png");


    public RendererCosmicFiend(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelCosmicFiend(), 3f);
        this.addLayer(new LayerCosmicFiend<>(this));
        setGhostLayers(0.5f,10);
    }


    @Override
    protected void preRenderCallback(EntityCosmicFiend entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        double i = 8f;
        float f = 1.0F + 0.15F * (float)i;
        matrixStackIn.scale(f, f, f);
    }


    @Override
    public void render(EntityCosmicFiend entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        float offset = Math.min(2f,(1-(entityIn.getHealth()/entityIn.getMaxHealth()))*1.5f+0.5f);
        renderGhost(entityIn,matrixStackIn,bufferIn,partialTicks,packedLightIn,entityYaw,offset);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityCosmicFiend entity) {
        return TEXTURE;
    }
}

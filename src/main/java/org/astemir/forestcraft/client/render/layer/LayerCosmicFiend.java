package org.astemir.forestcraft.client.render.layer;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCRenderTypes;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelCosmicFiend;

public class LayerCosmicFiend<T extends Entity,M extends ModelCosmicFiend> extends AbstractEyesLayer {


    public static final RenderType RENDER_TYPE = FCRenderTypes.getEyesTransculent(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/cosmic_fiend/cosmic_fiend_overlay.png"));

    public LayerCosmicFiend(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }


    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (WorldUtils.isNight(entitylivingbaseIn.world)) {
            super.render(matrixStackIn,bufferIn,packedLightIn,entitylivingbaseIn,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw,headPitch);
        }
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch,float alpha) {
        if (WorldUtils.isNight(entitylivingbaseIn.world)) {
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);
        }
    }


    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}

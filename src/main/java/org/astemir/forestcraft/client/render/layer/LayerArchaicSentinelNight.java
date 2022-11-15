package org.astemir.forestcraft.client.render.layer;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelRunestoneLord;

public class LayerArchaicSentinelNight<T extends Entity,M extends ModelRunestoneLord> extends AbstractEyesLayer {

    private static final RenderType OVERLAY = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/archaic_sentinel/archaic_sentinel_night_overlay.png"));

    public LayerArchaicSentinelNight(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (WorldUtils.isNight(entitylivingbaseIn.world)) {
            super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        }
    }

    @Override
    public RenderType getRenderType() {
        return OVERLAY;
    }
}

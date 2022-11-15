package org.astemir.forestcraft.client.render.layer;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelAirSucker;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelAknayah;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityAknayah;

public class LayerAknayah<T extends Entity,M extends ModelAknayah> extends AbstractEyesLayer {

    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/hayanka_true_form_layer.png"));

    public LayerAknayah(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        EntityAknayah aknayah = (EntityAknayah) entitylivingbaseIn;
        if (aknayah.getHealth() / aknayah.getMaxHealth() <= 0.5f) {
            super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        }
    }

    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}

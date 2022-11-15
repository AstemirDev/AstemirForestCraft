package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelJungleWasp;
import org.astemir.forestcraft.common.entities.monsters.EntityJungleWasp;

@OnlyIn(Dist.CLIENT)
public class RendererJungleWasp extends MobRenderer<EntityJungleWasp, ModelJungleWasp> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/jungle_wasp.png");


    public RendererJungleWasp(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelJungleWasp(), 0.25f);
    }



    @Override
    public void render(EntityJungleWasp entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityJungleWasp entity) {
        return TEXTURE;
    }
}

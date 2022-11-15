package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelBakudan;
import org.astemir.forestcraft.common.entities.monsters.EntityBakudan;

@OnlyIn(Dist.CLIENT)
public class RendererBakudan extends MobRenderer<EntityBakudan, ModelBakudan> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/bakudan/bakudan.png");


    public RendererBakudan(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelBakudan(), 0.25f);
    }



    @Override
    public void render(EntityBakudan entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBakudan entity) {
        return TEXTURE;
    }
}

package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCRenderTypes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelDesecratedSoul;
import org.astemir.forestcraft.client.render.renderer.FCGhostRenderer;
import org.astemir.forestcraft.common.entities.monsters.EntityDesecratedSoul;

import javax.annotation.Nullable;


@OnlyIn(Dist.CLIENT)
public class RendererDesecratedSoul extends FCGhostRenderer<EntityDesecratedSoul, ModelDesecratedSoul> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_0.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_1.png");
    protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_2.png");
    protected static final ResourceLocation TEXTURE_3 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_3.png");
    protected static final ResourceLocation TEXTURE_4 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_4.png");
    protected static final ResourceLocation TEXTURE_5 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/desecrated_soul/desecrated_soul_5.png");

    public RendererDesecratedSoul(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelDesecratedSoul(), 0f);
        setGhostLayers(0.25f,2);
    }

    @Override
    public boolean shouldRender(EntityDesecratedSoul livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
    }

    @Override
    public void render(EntityDesecratedSoul entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        renderGhost(entityIn,matrixStackIn,bufferIn,partialTicks,packedLightIn,entityYaw,0.25f);
    }

    public RenderType getGhostRenderType(ResourceLocation loc){
        return FCRenderTypes.getEyesTransculent(loc);
    }


    @Nullable
    protected RenderType func_230496_a_(EntityDesecratedSoul p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        ResourceLocation resourcelocation = this.getEntityTexture(p_230496_1_);
        return FCRenderTypes.getEyesTransculent(resourcelocation);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityDesecratedSoul entity) {
        int skin = entity.getSkin();
        switch (skin){
            case 0: return TEXTURE_0;
            case 1: return TEXTURE_1;
            case 2: return TEXTURE_2;
            case 3: return TEXTURE_3;
            case 4: return TEXTURE_4;
            case 5: return TEXTURE_5;
        }
        return TEXTURE_0;
    }

}

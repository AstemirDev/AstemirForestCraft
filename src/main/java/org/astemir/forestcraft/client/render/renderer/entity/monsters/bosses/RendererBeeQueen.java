package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelBeeQueen;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityBeeQueen;

@OnlyIn(Dist.CLIENT)
public class RendererBeeQueen extends MobRenderer<EntityBeeQueen, ModelBeeQueen> {



    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/beequeen.png");

    public RendererBeeQueen(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelBeeQueen(), 1f);
    }


    @Override
    protected void applyRotations(EntityBeeQueen entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public void render(EntityBeeQueen entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBeeQueen entity) {
        return TEXTURE;
    }
}

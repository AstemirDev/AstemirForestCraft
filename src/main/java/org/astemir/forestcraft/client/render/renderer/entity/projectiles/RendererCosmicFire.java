package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCRenderTypes;
import org.astemir.forestcraft.client.render.model.entity.projectiles.ModelCosmicFire;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCosmicFire;

import static net.minecraft.client.renderer.RenderType.makeType;

public class RendererCosmicFire extends EntityRenderer<EntityCosmicFire> {

    public static final ResourceLocation T0 = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/cosmic_fire/cosmic_fire_0.png");
    public static final ResourceLocation T1 = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/cosmic_fire/cosmic_fire_1.png");
    public static final ResourceLocation T2 = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/cosmic_fire/cosmic_fire_2.png");
    public static final ResourceLocation T3 = new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/cosmic_fire/cosmic_fire_3.png");


    private ModelCosmicFire model = new ModelCosmicFire();

    public RendererCosmicFire(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityCosmicFire entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.push();
        matrixStackIn.translate(0,1.25f,0);
        matrixStackIn.rotate(Vector3f.XN.rotationDegrees(180f));
        IVertexBuilder vertexBuilder = bufferIn.getBuffer(FCRenderTypes.getEyesTransculent(getEntityTexture(entityIn)));
        model.render(matrixStackIn, vertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY,1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
        matrixStackIn.pop();
        model.setRotationAngles(entityIn,0,0,partialTicks,0,0);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityCosmicFire entity) {
        int index = (entity.ticksExisted/2) % 4;
        switch (index){
            case 0: return T0;
            case 1: return T1;
            case 2: return T2;
            case 3: return T3;
        }
        return null;
    }
}

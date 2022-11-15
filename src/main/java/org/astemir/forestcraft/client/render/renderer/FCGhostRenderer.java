package org.astemir.forestcraft.client.render.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.math.MathUtils;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.client.render.layer.LayerCosmicFiend;
import org.astemir.api.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class FCGhostRenderer<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T,M> {


    private List<GhostLayer> ghostLayers = new ArrayList<>();
    private int lastRenderedId = -1;
    private T lastRendered;


    public FCGhostRenderer(EntityRendererManager renderManagerIn, M entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }


    public void addGhostLayer(float offset){
        ghostLayers.add(new GhostLayer(offset));
    }


    public void setGhostLayers(float offset,int count){
        for (int i = 0;i<count;i++){
            addGhostLayer(offset);
        }
    }

    @Override
    public boolean shouldRender(T livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        boolean rendered = super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
        if (rendered){
            if (lastRenderedId == -1){
                lastRenderedId = livingEntityIn.getEntityId();
                lastRendered = livingEntityIn;
            }
        }else{
            if (livingEntityIn.getEntityId() == lastRenderedId) {
                lastRenderedId = -1;
                lastRendered = null;
            }
        }
        return rendered;
    }

    public void renderGhost(T entityIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, float partialTicks, int packedLightIn, float entityYaw, float offset){
        if (lastRendered == null){
            lastRenderedId = -1;
        }else
        if (lastRendered.removed){
            lastRenderedId = -1;
            lastRendered = null;
        }else{
            if (lastRendered.world.getDimensionKey() != entityIn.world.getDimensionKey()){
                lastRenderedId = -1;
                lastRendered = null;
            }
        }
        for (GhostLayer layer : ghostLayers) {
            if (entityIn.getEntityId() == lastRenderedId) {
                layer.update();
                layer.setOffset(offset);
            }
            matrixStackIn.push();
            applyRotations(entityIn,matrixStackIn,entityIn.ticksExisted,entityIn.rotationYaw,partialTicks);
            matrixStackIn.translate(layer.getPos().getX(),layer.getPos().getY(), layer.getPos().getZ());
            matrixStackIn.push();
            preRenderCallback(entityIn,matrixStackIn,partialTicks);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-180.0F));
            matrixStackIn.translate(0.0D, (double)-1.501F, 0.0D);
            entityModel.render(matrixStackIn,bufferIn.getBuffer(getGhostRenderType(getEntityTexture(entityIn))),packedLightIn,getPackedOverlay(entityIn,getOverlayProgress(entityIn,0)),1,1,1,layer.getAlpha());
            for(LayerRenderer layerrenderer : this.layerRenderers) {
                if (layerrenderer instanceof LayerCosmicFiend) {
                    ((LayerCosmicFiend)layerrenderer).render(matrixStackIn, bufferIn, packedLightIn, entityIn, entityIn.limbSwing, entityIn.limbSwingAmount, partialTicks, entityIn.ticksExisted, entityYaw, entityIn.rotationPitch, layer.getAlpha());
                }
            }
            matrixStackIn.pop();
            matrixStackIn.pop();
        }
    }

    public RenderType getGhostRenderType(ResourceLocation loc){
        return RenderType.getEntityTranslucent(loc);
    }



    public class GhostLayer{

        private float alpha = 1;

        private Vector3f pos = new Vector3f(0,0,0);

        private Vector3f newPos = new Vector3f(0,0,0);

        private float offset = 0.5f;

        public GhostLayer(float offset) {
            this.offset = offset;
            newPos = new Vector3f(pos.getX()+RandomUtils.randomFloat(-offset,offset),pos.getY()+RandomUtils.randomFloat(-offset,offset),pos.getZ()+RandomUtils.randomFloat(-offset,offset));
        }

        public void update(){
            if (Math.abs(alpha) > 0.01f){
                Vector3 p = MathUtils.interpolate(new Vector3(pos.getX(),pos.getY(),pos.getZ()),new Vector3(newPos.getX(),newPos.getY(),newPos.getZ()),0.1f);
                alpha = MathUtils.interpolate(alpha,0f,0.1f);
                pos = new Vector3f(p.x,p.y,p.z);
            }else{
                alpha = 1;
                pos = new Vector3f(0,0,0);
                newPos = new Vector3f(pos.getX()+RandomUtils.randomFloat(-offset,offset),pos.getY()+RandomUtils.randomFloat(-offset,offset),pos.getZ()+RandomUtils.randomFloat(-offset,offset));
            }
        }

        public void setOffset(float offset) {
            this.offset = offset;
        }

        public float getAlpha() {
            return alpha;
        }

        public Vector3f getPos() {
            return pos;
        }
    }

}

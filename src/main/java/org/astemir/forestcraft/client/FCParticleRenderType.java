package org.astemir.forestcraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class FCParticleRenderType {

    public static final IParticleRenderType COSMIC = new IParticleRenderType() {

        public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.disableBlend();
            RenderSystem.disableFog();
            RenderSystem.depthMask(true);
            textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
            bufferBuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        }


        public void finishRender(Tessellator tesselator) {
            tesselator.draw();
        }

        public String toString() {
            return "PARTICLE_SHEET_OPAQUE";
        }
    };
}

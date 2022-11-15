package org.astemir.forestcraft.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.Util;

import java.util.OptionalDouble;

public class FCRenderStates {
    
    public static final RenderState.TransparencyState NO_TRANSPARENCY = new RenderState.TransparencyState("no_transparency", () -> {
        RenderSystem.disableBlend();
    }, () -> {
    });
    public static final RenderState.TransparencyState ADDITIVE_TRANSPARENCY = new RenderState.TransparencyState("additive_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderState.TransparencyState LIGHTNING_TRANSPARENCY = new RenderState.TransparencyState("lightning_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderState.TransparencyState GLINT_TRANSPARENCY = new RenderState.TransparencyState("glint_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderState.TransparencyState CRUMBLING_TRANSPARENCY = new RenderState.TransparencyState("crumbling_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderState.TransparencyState TRANSLUCENT_TRANSPARENCY = new RenderState.TransparencyState("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderState.AlphaState ZERO_ALPHA = new RenderState.AlphaState(0.0F);
    public static final RenderState.AlphaState DEFAULT_ALPHA = new RenderState.AlphaState(0.003921569F);
    public static final RenderState.AlphaState HALF_ALPHA = new RenderState.AlphaState(0.5F);
    public static final RenderState.ShadeModelState SHADE_DISABLED = new RenderState.ShadeModelState(false);
    public static final RenderState.ShadeModelState SHADE_ENABLED = new RenderState.ShadeModelState(true);
    public static final RenderState.TextureState BLOCK_SHEET_MIPPED = new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS_TEXTURE, false, true);
    public static final RenderState.TextureState BLOCK_SHEET = new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS_TEXTURE, false, false);
    public static final RenderState.TextureState NO_TEXTURE = new RenderState.TextureState();
    public static final RenderState.TexturingState DEFAULT_TEXTURING = new RenderState.TexturingState("default_texturing", () -> {
    }, () -> {
    });
    public static final RenderState.TexturingState OUTLINE_TEXTURING = new RenderState.TexturingState("outline_texturing", () -> {
        RenderSystem.setupOutline();
    }, () -> {
        RenderSystem.teardownOutline();
    });
    public static final RenderState.TexturingState GLINT_TEXTURING = new RenderState.TexturingState("glint_texturing", () -> {
        setupGlintTexturing(8.0F);
    }, () -> {
        RenderSystem.matrixMode(5890);
        RenderSystem.popMatrix();
        RenderSystem.matrixMode(5888);
    });
    public static final RenderState.TexturingState ENTITY_GLINT_TEXTURING = new RenderState.TexturingState("entity_glint_texturing", () -> {
        setupGlintTexturing(0.16F);
    }, () -> {
        RenderSystem.matrixMode(5890);
        RenderSystem.popMatrix();
        RenderSystem.matrixMode(5888);
    });
    public static final RenderState.LightmapState LIGHTMAP_ENABLED = new RenderState.LightmapState(true);
    public static final RenderState.LightmapState LIGHTMAP_DISABLED = new RenderState.LightmapState(false);
    public static final RenderState.OverlayState OVERLAY_ENABLED = new RenderState.OverlayState(true);
    public static final RenderState.OverlayState OVERLAY_DISABLED = new RenderState.OverlayState(false);
    public static final RenderState.DiffuseLightingState DIFFUSE_LIGHTING_ENABLED = new RenderState.DiffuseLightingState(true);
    public static final RenderState.DiffuseLightingState DIFFUSE_LIGHTING_DISABLED = new RenderState.DiffuseLightingState(false);
    public static final RenderState.CullState CULL_ENABLED = new RenderState.CullState(true);
    public static final RenderState.CullState CULL_DISABLED = new RenderState.CullState(false);
    public static final RenderState.DepthTestState DEPTH_ALWAYS = new RenderState.DepthTestState("always", 519);
    public static final RenderState.DepthTestState DEPTH_EQUAL = new RenderState.DepthTestState("==", 514);
    public static final RenderState.DepthTestState DEPTH_LEQUAL = new RenderState.DepthTestState("<=", 515);
    public static final RenderState.WriteMaskState COLOR_DEPTH_WRITE = new RenderState.WriteMaskState(true, true);
    public static final RenderState.WriteMaskState COLOR_WRITE = new RenderState.WriteMaskState(true, false);
    public static final RenderState.WriteMaskState DEPTH_WRITE = new RenderState.WriteMaskState(false, true);
    public static final RenderState.LayerState NO_LAYERING = new RenderState.LayerState("no_layering", () -> {
    }, () -> {
    });
    public static final RenderState.LayerState POLYGON_OFFSET_LAYERING = new RenderState.LayerState("polygon_offset_layering", () -> {
        RenderSystem.polygonOffset(-1.0F, -10.0F);
        RenderSystem.enablePolygonOffset();
    }, () -> {
        RenderSystem.polygonOffset(0.0F, 0.0F);
        RenderSystem.disablePolygonOffset();
    });
    public static final RenderState.LayerState field_239235_M_ = new RenderState.LayerState("view_offset_z_layering", () -> {
        RenderSystem.pushMatrix();
        RenderSystem.scalef(0.99975586F, 0.99975586F, 0.99975586F);
    }, RenderSystem::popMatrix);
    public static final RenderState.FogState NO_FOG = new RenderState.FogState("no_fog", () -> {
    }, () -> {
    });
    public static final RenderState.FogState FOG = new RenderState.FogState("fog", () -> {
        FogRenderer.applyFog();
        RenderSystem.enableFog();
    }, () -> {
        RenderSystem.disableFog();
    });
    public static final RenderState.FogState BLACK_FOG = new RenderState.FogState("black_fog", () -> {
        RenderSystem.fog(2918, 0.0F, 0.0F, 0.0F, 1.0F);
        RenderSystem.enableFog();
    }, () -> {
        FogRenderer.applyFog();
        RenderSystem.disableFog();
    });
    public static final RenderState.TargetState MAIN_TARGET = new RenderState.TargetState("main_target", () -> {
    }, () -> {
    });
    public static final RenderState.TargetState OUTLINE_TARGET = new RenderState.TargetState("outline_target", () -> {
        Minecraft.getInstance().worldRenderer.getEntityOutlineFramebuffer().bindFramebuffer(false);
    }, () -> {
        Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
    });
    public static final RenderState.TargetState field_239236_S_ = new RenderState.TargetState("translucent_target", () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().worldRenderer.func_239228_q_().bindFramebuffer(false);
        }

    }, () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
        }

    });
    public static final RenderState.TargetState field_239237_T_ = new RenderState.TargetState("particles_target", () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().worldRenderer.func_239230_s_().bindFramebuffer(false);
        }

    }, () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
        }

    });
    public static final RenderState.TargetState field_239238_U_ = new RenderState.TargetState("weather_target", () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().worldRenderer.func_239231_t_().bindFramebuffer(false);
        }

    }, () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
        }

    });
    public static final RenderState.TargetState field_239239_V_ = new RenderState.TargetState("clouds_target", () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().worldRenderer.func_239232_u_().bindFramebuffer(false);
        }

    }, () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
        }

    });
    public static final RenderState.TargetState field_241712_U_ = new RenderState.TargetState("item_entity_target", () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().worldRenderer.func_239229_r_().bindFramebuffer(false);
        }

    }, () -> {
        if (Minecraft.isFabulousGraphicsEnabled()) {
            Minecraft.getInstance().getFramebuffer().bindFramebuffer(false);
        }

    });
    
    public static final RenderState.LineState DEFAULT_LINE = new RenderState.LineState(OptionalDouble.of(1.0D));

    private static void setupGlintTexturing(float scaleIn) {
        RenderSystem.matrixMode(5890);
        RenderSystem.pushMatrix();
        RenderSystem.loadIdentity();
        long i = Util.milliTime() * 8L;
        float f = (float)(i % 110000L) / 110000.0F;
        float f1 = (float)(i % 30000L) / 30000.0F;
        RenderSystem.translatef(-f, f1, 0.0F);
        RenderSystem.rotatef(10.0F, 0.0F, 0.0F, 1.0F);
        RenderSystem.scalef(scaleIn, scaleIn, scaleIn);
        RenderSystem.matrixMode(5888);
    }
}

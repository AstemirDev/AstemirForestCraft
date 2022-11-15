package org.astemir.forestcraft.client;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;


import static net.minecraft.client.renderer.RenderType.makeType;
import static org.astemir.forestcraft.client.FCRenderStates.*;

public class FCRenderTypes {


    public static final RenderType SOLID = makeType("forestcraft:solid", DefaultVertexFormats.BLOCK, 7, 2097152, true, false, RenderType.State.getBuilder().shadeModel(SHADE_ENABLED).lightmap(LIGHTMAP_ENABLED).texture(BLOCK_SHEET_MIPPED).build(true));
    public static final RenderType CUTOUT_MIPPED = makeType("forestcraft:cutout_mipped", DefaultVertexFormats.BLOCK, 7, 131072, true, false, RenderType.State.getBuilder().shadeModel(SHADE_ENABLED).lightmap(LIGHTMAP_ENABLED).texture(BLOCK_SHEET_MIPPED).alpha(HALF_ALPHA).build(true));
    public static final RenderType CUTOUT = makeType("forestcraft:cutout", DefaultVertexFormats.BLOCK, 7, 131072, true, false, RenderType.State.getBuilder().shadeModel(SHADE_ENABLED).lightmap(LIGHTMAP_ENABLED).texture(BLOCK_SHEET).alpha(HALF_ALPHA).build(true));
    public static final RenderType TRANSLUCENT = makeType("forestcraft:translucent", DefaultVertexFormats.BLOCK, 7, 262144, true, true, getTranslucentState());

    public static RenderType getEntityCutoutFrame(ResourceLocation locationIn,float i,float j) {
        RenderType.State rendertype$state = RenderType.State.getBuilder().texture(new RenderState.TextureState(locationIn, false, false)).transparency(NO_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).texturing(new RenderState.OffsetTexturingState(i,j)).build(true);
        return makeType("forestcraft:entity_cutout", DefaultVertexFormats.ENTITY, 7, 256, true, false, rendertype$state);
    }

    public static RenderType.State getTranslucentState() {
        return RenderType.State.getBuilder().shadeModel(SHADE_ENABLED).lightmap(LIGHTMAP_ENABLED).texture(BLOCK_SHEET_MIPPED).transparency(TRANSLUCENT_TRANSPARENCY).target(field_239236_S_).build(true);
    }


    public static RenderType getEyesTransculent(ResourceLocation locationIn) {
        RenderState.TextureState renderstate$texturestate = new RenderState.TextureState(locationIn, false, false);
        return RenderType.makeType("forestcraft:eyes_transculent", DefaultVertexFormats.ENTITY, 7, 256, false, true, RenderType.State.getBuilder().texture(renderstate$texturestate).transparency(TRANSLUCENT_TRANSPARENCY).writeMask(COLOR_WRITE).fog(NO_FOG).build(false));
    }

    private static RenderType.State getItemEntityState() {
        return RenderType.State.getBuilder().shadeModel(SHADE_ENABLED).lightmap(LIGHTMAP_ENABLED).texture(BLOCK_SHEET_MIPPED).transparency(TRANSLUCENT_TRANSPARENCY).target(field_241712_U_).build(true);
    }
}

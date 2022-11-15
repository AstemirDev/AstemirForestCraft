package org.astemir.forestcraft.client.render.renderer.entity.projectiles;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;

@OnlyIn(Dist.CLIENT)
public class RendererElectriteArrow extends ArrowRenderer {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/electrite_arrow.png");

    public RendererElectriteArrow(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }


    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}

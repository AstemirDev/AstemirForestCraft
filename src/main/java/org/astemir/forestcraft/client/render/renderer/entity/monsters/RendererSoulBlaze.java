package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;


@OnlyIn(Dist.CLIENT)
public class RendererSoulBlaze extends BlazeRenderer {

    protected static final ResourceLocation texture = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/soul_blaze.png");


    public RendererSoulBlaze(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(BlazeEntity entity) {
        return texture;
    }
}

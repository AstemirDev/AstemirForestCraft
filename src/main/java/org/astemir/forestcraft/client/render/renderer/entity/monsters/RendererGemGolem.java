package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelGemGolem;
import org.astemir.forestcraft.common.entities.animals.EntityGemGolem;

@OnlyIn(Dist.CLIENT)
public class RendererGemGolem extends MobRenderer<EntityGemGolem, ModelGemGolem> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/gem_golem.png");

    public RendererGemGolem(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelGemGolem(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityGemGolem entity) {
        return TEXTURE;
    }
}

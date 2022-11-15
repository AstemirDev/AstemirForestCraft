package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerWoodAbominationEyes;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelWoodAbomination;
import org.astemir.forestcraft.common.entities.monsters.EntityWoodAbomination;

@OnlyIn(Dist.CLIENT)
public class RendererWoodAbomination extends MobRenderer<EntityWoodAbomination, ModelWoodAbomination> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/wood_abomination/wood_abomination.png");

    public RendererWoodAbomination(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelWoodAbomination(), 0.7f);
        this.addLayer(new LayerWoodAbominationEyes<>(this));
    }


    @Override
    public ResourceLocation getEntityTexture(EntityWoodAbomination entity) {
        return TEXTURE;
    }
}

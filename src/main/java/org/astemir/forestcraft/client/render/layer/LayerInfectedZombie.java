package org.astemir.forestcraft.client.render.layer;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelInfectedZombie;

public class LayerInfectedZombie<T extends Entity,M extends ModelInfectedZombie> extends AbstractEyesLayer {

    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/infected_zombie/infected_zombie_glow.png"));

    public LayerInfectedZombie(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }


    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}

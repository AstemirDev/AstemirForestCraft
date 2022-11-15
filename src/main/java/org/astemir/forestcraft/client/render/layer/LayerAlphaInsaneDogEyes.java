package org.astemir.forestcraft.client.render.layer;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelAlphaInsaneDog;

public class LayerAlphaInsaneDogEyes<T extends Entity,M extends ModelAlphaInsaneDog> extends AbstractEyesLayer {

    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/alpha_insane_dog/alpha_insane_dog_eyes.png"));

    public LayerAlphaInsaneDogEyes(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}

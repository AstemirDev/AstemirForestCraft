package org.astemir.forestcraft.client.render.layer;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelAirSucker;

public class LayerNightBat<T extends Entity,M extends ModelAirSucker> extends AbstractEyesLayer {

    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/night_bat/night_bat_layer.png"));

    public LayerNightBat(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}

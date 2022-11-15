package org.astemir.forestcraft.client.render.layer;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelNightWatcher;

public class LayerNightWatcher<T extends Entity,M extends ModelNightWatcher> extends AbstractEyesLayer {

    private static final RenderType RENDER_TYPE_NORMAL = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/night_watcher_eye.png"));
    private static final RenderType RENDER_TYPE_ANGRY = RenderType.getEyes(new ResourceLocation(ForestCraft.MOD_ID,"textures/entity/night_watcher_eye_angry.png"));

    public LayerNightWatcher(IEntityRenderer p_i226039_1_) {
        super(p_i226039_1_);
    }


    @Override
    public RenderType getRenderType() {
        return RENDER_TYPE_NORMAL;
    }
}

package org.astemir.forestcraft.client.render.renderer.entity.animals;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.animals.ModelDeer;
import org.astemir.forestcraft.common.entities.animals.EntityDeer;

@OnlyIn(Dist.CLIENT)
public class RendererDeer extends MobRenderer<EntityDeer, ModelDeer> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/deer/deer.png");
    protected static final ResourceLocation SNOWY_TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/deer/reindeer.png");

    public RendererDeer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelDeer(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityDeer entity) {
        if (entity.getDeerType() == 1) {
            return SNOWY_TEXTURE;
        }
        return TEXTURE;
    }

}

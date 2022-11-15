package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerAknayah;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelAknayah;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityAknayah;

@OnlyIn(Dist.CLIENT)
public class RendererAknayah extends MobRenderer<EntityAknayah, ModelAknayah> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/hayanka.png");
    protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/hayanka_true_form.png");

    public RendererAknayah(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelAknayah(), 0.5f);
        addLayer(new LayerAknayah(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAknayah entity) {
        if (entity.getHealth() / entity.getMaxHealth() <= 0.5f) {
            return TEXTURE_2;
        }
        return TEXTURE;
    }

}

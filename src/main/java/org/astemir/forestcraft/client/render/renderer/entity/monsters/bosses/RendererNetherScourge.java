package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelNetherScourge;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityNetherScourge;

@OnlyIn(Dist.CLIENT)
public class RendererNetherScourge extends MobRenderer<EntityNetherScourge, ModelNetherScourge> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/nether_scourge/nether_scourge.png");
    protected static final ResourceLocation ENRAGED = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/nether_scourge/nether_scourge_enraged.png");

    public RendererNetherScourge(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelNetherScourge(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityNetherScourge entity) {
        if (entity.isEnraged()){
            return ENRAGED;
        }
        return TEXTURE;
    }

}

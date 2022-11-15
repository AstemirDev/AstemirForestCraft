package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.entities.monsters.EntityPetrifiedRunestoneZombie;


@OnlyIn(Dist.CLIENT)
public class RendererPetrifiedZombie extends AbstractZombieRenderer<EntityPetrifiedRunestoneZombie,ZombieModel<EntityPetrifiedRunestoneZombie>> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/petrified_runestone_zombie/petrified_runestone_zombie_0.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/petrified_runestone_zombie/petrified_runestone_zombie_1.png");
    protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/petrified_runestone_zombie/petrified_runestone_zombie_2.png");
    protected static final ResourceLocation TEXTURE_3 = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/petrified_runestone_zombie/petrified_runestone_zombie_3.png");

    public RendererPetrifiedZombie(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPetrifiedRunestoneZombie entity) {
        int skin = entity.getSkin();
        switch (skin){
            case 0: return TEXTURE_0;
            case 1: return TEXTURE_1;
            case 2: return TEXTURE_2;
            case 3: return TEXTURE_3;
        }
        return TEXTURE_0;
    }
}

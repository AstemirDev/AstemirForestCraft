package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelCrocusFlower;
import org.astemir.forestcraft.common.entities.monsters.EntityCrocusFlower;

@OnlyIn(Dist.CLIENT)
public class RendererCrocusFlower extends MobRenderer<EntityCrocusFlower, ModelCrocusFlower> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/crocus_flower.png");

    public RendererCrocusFlower(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelCrocusFlower(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCrocusFlower entity) {
        return TEXTURE;
    }
}

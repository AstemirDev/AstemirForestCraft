package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelKrock;
import org.astemir.forestcraft.common.entities.monsters.EntityKrock;


@OnlyIn(Dist.CLIENT)
public class RendererKrock extends MobRenderer<EntityKrock, ModelKrock> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/krock.png");

    public RendererKrock(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelKrock(), 0.7f);
    }

    @Override
    public boolean shouldRender(EntityKrock livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityKrock entity) {
        return TEXTURE;
    }
}

package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.layer.LayerInfectedZombie;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelInfectedZombie;
import org.astemir.forestcraft.common.entities.monsters.EntityInfectedZombie;

@OnlyIn(Dist.CLIENT)
public class RendererInfectedZombie extends MobRenderer<EntityInfectedZombie, ModelInfectedZombie> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/infected_zombie/infected_zombie.png");

    public RendererInfectedZombie(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelInfectedZombie(), 0.7f);
        this.addLayer(new LayerInfectedZombie<>(this));
    }

    @Override
    protected void preRenderCallback(EntityInfectedZombie entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        if (entitylivingbaseIn.isChild()) {
            float f = 0.5f;
            matrixStackIn.scale(f, f, f);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(EntityInfectedZombie entity) {
        return TEXTURE;
    }
}

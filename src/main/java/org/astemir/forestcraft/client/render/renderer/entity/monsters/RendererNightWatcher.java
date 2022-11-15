package org.astemir.forestcraft.client.render.renderer.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelNightWatcher;
import org.astemir.forestcraft.common.entities.monsters.EntityNightWatcher;


@OnlyIn(Dist.CLIENT)
public class RendererNightWatcher extends MobRenderer<EntityNightWatcher, ModelNightWatcher> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/night_watcher/night_watcher.png");
    protected static final ResourceLocation ANGRY = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/night_watcher/night_watcher_angry.png");

    public RendererNightWatcher(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelNightWatcher(), 0.7f);
    }

    @Override
    protected void preRenderCallback(EntityNightWatcher entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        matrixStackIn.translate(0,-2,0);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityNightWatcher entity) {
        if (entity.getAttackTicks() > 0){
            return ANGRY;
        }else{
            return TEXTURE;
        }
    }

}

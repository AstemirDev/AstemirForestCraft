package org.astemir.forestcraft.client.render.renderer.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.entity.monsters.bosses.ModelRunestoneLord;
import org.astemir.forestcraft.client.render.layer.LayerArchaicSentinel;
import org.astemir.forestcraft.client.render.layer.LayerArchaicSentinelNight;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityArchaicSentinel;

@OnlyIn(Dist.CLIENT)
public class RendererArchaicSentinel extends MobRenderer<EntityArchaicSentinel, ModelRunestoneLord> {


    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/archaic_sentinel/archaic_sentinel.png");
    protected static final ResourceLocation TEXTURE_NIGHT = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/archaic_sentinel/archaic_sentinel_night.png");


    public RendererArchaicSentinel(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new ModelRunestoneLord(), 0.5f);
        addLayer(new LayerArchaicSentinel<>(this));
        addLayer(new LayerArchaicSentinelNight<>(this));
    }



    @Override
    public void render(EntityArchaicSentinel entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityArchaicSentinel entity) {
        if (WorldUtils.isNight(entity.world)){
            return TEXTURE_NIGHT;
        }else{
            return TEXTURE;
        }
    }
}

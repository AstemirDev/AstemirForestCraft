package org.astemir.forestcraft.client.render.renderer.block;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.item.renderer.AnimatedTileEntityRenderer;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.render.model.block.ModelGiantHive;
import org.astemir.forestcraft.common.tileentity.TileEntityGiantHive;

@OnlyIn(Dist.CLIENT)
public class RendererGiantHive extends AnimatedTileEntityRenderer<TileEntityGiantHive, ModelGiantHive> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ForestCraft.MOD_ID, "textures/entity/giant_hive.png");

    public RendererGiantHive() {
        super(new ModelGiantHive());
    }

    @Override
    public ResourceLocation getTexture(TileEntityGiantHive tileEntity) {
        return TEXTURE;
    }
}

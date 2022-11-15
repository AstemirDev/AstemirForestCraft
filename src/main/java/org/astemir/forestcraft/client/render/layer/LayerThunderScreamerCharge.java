package org.astemir.forestcraft.client.render.layer;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.client.render.model.entity.monsters.ModelThunderScreamer;
import org.astemir.forestcraft.common.entities.monsters.EntityThunderScreamer;


@OnlyIn(Dist.CLIENT)
public class LayerThunderScreamerCharge extends EnergyLayer<EntityThunderScreamer, ModelThunderScreamer> {

    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final ModelThunderScreamer birdModel = new ModelThunderScreamer();

    public LayerThunderScreamerCharge(IEntityRenderer<EntityThunderScreamer, ModelThunderScreamer> p_i50947_1_) {
        super(p_i50947_1_);
    }

    @Override
    protected float func_225634_a_(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    @Override
    protected ResourceLocation func_225633_a_() {
        return LIGHTNING_TEXTURE;
    }

    @Override
    protected EntityModel<EntityThunderScreamer> func_225635_b_() {
        return this.birdModel;
    }
}

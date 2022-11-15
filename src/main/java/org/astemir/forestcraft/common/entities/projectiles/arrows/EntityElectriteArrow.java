package org.astemir.forestcraft.common.entities.projectiles.arrows;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.api.math.RandomUtils;

public class EntityElectriteArrow extends AbstractArrowEntity {

    public EntityElectriteArrow(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityElectriteArrow(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public EntityElectriteArrow(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
    }

    public EntityElectriteArrow(World worldIn, PlayerEntity playerentity) {
        super(FCEntities.ELECTRITE_ARROW, playerentity, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(FCParticles.ELECTRO.get(), this.getPosX()+ RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0,0,0);
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        if (p_213868_1_.getEntity() instanceof LivingEntity){
            if (!((LivingEntity)p_213868_1_.getEntity()).isPotionActive(FCEffects.ELECTROCUT.get())) {
                ((LivingEntity) p_213868_1_.getEntity()).addPotionEffect(new EffectInstance(FCEffects.ELECTROCUT.get(), 100, 0, false, false));
            }
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }

}

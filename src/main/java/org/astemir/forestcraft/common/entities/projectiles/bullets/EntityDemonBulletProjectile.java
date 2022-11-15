package org.astemir.forestcraft.common.entities.projectiles.bullets;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.projectiles.ITimeLiving;
import org.astemir.forestcraft.registries.FCItems;


public class EntityDemonBulletProjectile extends FCBulletEntity implements ITimeLiving {

    public EntityDemonBulletProjectile(EntityType<? extends EntityDemonBulletProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
        setNoGravity(true);
    }

    public EntityDemonBulletProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.DEMON_BULLET_PROJECTILE, throwerIn, worldIn);
        setNoGravity(true);
    }

    public EntityDemonBulletProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.DEMON_BULLET_PROJECTILE, x, y, z, worldIn);
        setNoGravity(true);
    }

    @Override
    public void breakAndDie() {
        super.breakAndDie();
        Particle3D particle = new Particle3D(ParticleTypes.BLOCK,FCBlocks.ROTTEN_FLESH_BLOCK.getDefaultState()).size(0.1,0.1,0.1).renderTimes(16).speed(0.25f,0.25f,0.25f).randomSpeed();
        particle.play(world,getPosX(),getPosY(),getPosZ());
    }

    @Override
    protected Item getDefaultItem() {
        return FCItems.DEMON_BULLET;
    }

    @Override
    public void tick() {
        super.tick();
        Particle3D particle = new Particle3D(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()).size(0.1f,0.1f,0.1f).renderTimes(2).speed(0.1f,0.1f,0.1f).randomSpeed().renderTimes(2);
        particle.play(world,getPosX(),getPosY(),getPosZ());
    }


    @Override
    public void onDamageEntity(LivingEntity entity) {
        if (getShooter() != null) {
            if (entity.getEntityId() != getShooter().getEntityId()) {
                entity.addPotionEffect(new EffectInstance(Effects.REGENERATION,40,0));
                entity.addPotionEffect(new EffectInstance(Effects.SPEED,40,1));
            }
        }
    }

    @Override
    public int maxLivingTime() {
        return 30;
    }
}
package org.astemir.forestcraft.common.entities.projectiles.bullets;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.projectiles.ITimeLiving;
import org.astemir.forestcraft.registries.FCItems;


public class EntityBlackholeBullet extends FCBulletEntity implements ITimeLiving {


    public EntityBlackholeBullet(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityBlackholeBullet(double x, double y, double z, World worldIn) {
        super(FCEntities.BLACKHOLE_BULLET_PROJECTILE, x, y, z, worldIn);
    }

    public EntityBlackholeBullet(LivingEntity livingEntityIn, World worldIn) {
        super(FCEntities.BLACKHOLE_BULLET_PROJECTILE, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return FCItems.BLACKHOLE_BULLET;
    }


    @Override
    public void tick() {
        super.tick();
        Particle3D particle = new Particle3D(ParticleTypes.WITCH).size(0.1f,0.1f,0.1f).speed(0.1f,0.1f,0.1f).randomSpeed();
        particle.play(world,getPosX(),getPosY(),getPosZ());
    }



    @Override
    public void onDamageEntity(LivingEntity entity) {
        if (getShooter() != null) {
            if (entity.getEntityId() != getShooter().getEntityId()) {
                entity.setFire(10);
                entity.hurtResistantTime = 10;
            }
        }
    }

    @Override
    public int maxLivingTime() {
        return 30;
    }
}
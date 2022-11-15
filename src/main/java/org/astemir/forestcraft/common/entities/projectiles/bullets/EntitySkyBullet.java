package org.astemir.forestcraft.common.entities.projectiles.bullets;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.projectiles.ITimeLiving;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItems;


public class EntitySkyBullet extends FCBulletEntity implements ITimeLiving {

    public EntitySkyBullet(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntitySkyBullet(double x, double y, double z, World worldIn) {
        super(FCEntities.SKY_BULLET_PROJECTILE, x, y, z, worldIn);
    }

    public EntitySkyBullet(LivingEntity livingEntityIn, World worldIn) {
        super(FCEntities.SKY_BULLET_PROJECTILE, livingEntityIn, worldIn);
    }


    @Override
    public void breakAndDie() {
        super.breakAndDie();
        world.playSound(getPosX(),getPosY(),getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT,0.25f,2,false);
    }

    @Override
    public void playBreakingParticle() {
        super.playBreakingParticle();
        for (int i = 0; i < 4; ++i) {
            this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return FCItems.SKY_BULLET;
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(ParticleTypes.END_ROD, this.getPosX()+ RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0, 0, 0);
    }


    @Override
    public int maxLivingTime() {
        return 30;
    }
}
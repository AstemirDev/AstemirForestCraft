package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.client.AColor;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.Sound3D;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.projectiles.ITimeLiving;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;


public class DaybreakProjectile extends FCBulletEntity implements IAnimated, ITimeLiving {


    private Animation DEFAULT = new Animation(0,"default").time(0.52f).loop();

    private AnimationFactory factory = new AnimationFactory(this,DEFAULT);

    public DaybreakProjectile(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DaybreakProjectile(double x, double y, double z, World worldIn) {
        super(FCEntities.DAYBREAK_PROJECTILE, x, y, z, worldIn);
    }

    public DaybreakProjectile(LivingEntity livingEntityIn, World worldIn) {
        super(FCEntities.DAYBREAK_PROJECTILE, livingEntityIn, worldIn);
    }

    @Override
    public void breakAndDie() {
        super.breakAndDie();
        Particle3D particle = new Particle3D(ParticleTypes.BLOCK,FCBlocks.ARCHAIC_STONE.getDefaultState()).size(1,1,1).renderTimes(16).speed(0.25f,0.25f,0.25f).randomSpeed();
        particle.play(world,getPosX(),getPosY(),getPosZ());
        Sound3D sound = new Sound3D(()-> SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.AMBIENT,1,0.5f);
        sound.play(world,getPosX(),getPosY(),getPosZ());
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }


    @Override
    public void tick() {
        super.tick();
        factory.tick();
        factory.playAnimation(DEFAULT);
        Particle3D a = new Particle3D(AColor.GOLD).size(0.1f,0.1f,0.1f).speed(0.5f,0.5f,0.5f).randomSpeed();
        Particle3D b = new Particle3D(AColor.PURPLE).size(0.1f,0.1f,0.1f).speed(0.5f,0.5f,0.5f).randomSpeed();
        a.play(world,getPosX(),getPosY(),getPosZ());
        b.play(world,getPosX(),getPosY(),getPosZ());
    }


    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    @Override
    public int maxLivingTime() {
        return 80;
    }
}
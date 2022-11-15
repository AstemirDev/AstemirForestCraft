package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.api.math.RandomUtils;



public class EntityBubbleProjectile extends ThrowableEntity implements IAnimated {

    public int damage = 3;

    private Animation DEFAULT = new Animation(0,"default").time(1.04f).loop();

    private AnimationFactory factory = new AnimationFactory(this,DEFAULT);

    public EntityBubbleProjectile(EntityType<? extends EntityBubbleProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public EntityBubbleProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.BUBBLE_PROJECTILE, throwerIn, worldIn);
    }

    public EntityBubbleProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.BUBBLE_PROJECTILE, x, y, z, worldIn);
    }




    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vector3d vector3d = (new Vector3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale(velocity);
        this.setMotion(vector3d);
        float f = MathHelper.sqrt(horizontalMag(vector3d));
        this.rotationYaw = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(vector3d.y, f) * (double)(180F / (float)Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    protected void registerData() {
    }

    @Override
    public void tick() {
        super.tick();
        factory.tick();
        factory.playAnimation(DEFAULT);
        if (ticksExisted > 60){
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 3);
                this.remove();
            }
        }
        setMotion(getMotion().mul(0.95f,0.95f,0.95f));
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected float getGravityVelocity() {
        return 0.005f;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3){
            for (int i = 0;i<10;i++) {
                this.world.addParticle(ParticleTypes.BUBBLE_POP, this.getPosX() + RandomUtils.randomFloat(-0.4f, 0.4f), this.getPosY() + RandomUtils.randomFloat(-0.4f, 0.4f), this.getPosZ() + RandomUtils.randomFloat(-0.4f, 0.4f), 0, 0, 0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        int i = RandomUtils.randomInt(damage,damage+2);
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) i);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
            for (int i = 0;i<10;i++) {
                playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 2, 0.5f);
                playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 2, 2);
            }
        }
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
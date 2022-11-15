package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCSounds;


public class EntityMiniTornadoProjectile extends ThrowableEntity implements IAnimated {

    public int damage = 3;

    private Animation DEFAULT = new Animation(0,"default").speed(0.5f).time(1.04f).loop();

    private int lifeTicks = 9999999;

    private AnimationFactory factory = new AnimationFactory(this,DEFAULT);

    public EntityMiniTornadoProjectile(EntityType<? extends EntityMiniTornadoProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public EntityMiniTornadoProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.MINI_TORNADO_PROJECTILE, throwerIn, worldIn);
    }

    public EntityMiniTornadoProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.MINI_TORNADO_PROJECTILE, x, y, z, worldIn);
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
        playSound(FCSounds.CLOUD_RAY_ATTACK.get(), 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    protected void registerData() {
    }

    private Particle3D particle3D = new Particle3D(ParticleTypes.CLOUD).size(0.25f,0.65f,0.25f).speed(0.25f,0.25f,0.25f).deltaSpeed(0.1f).randomSpeed();

    @Override
    public void tick() {
        super.tick();
        factory.tick();
        factory.playAnimation(DEFAULT);
        if (lifeTicks <= 0){
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 3);
                this.remove();
            }
        }
        if (ticksExisted > 120){
            lifeTicks = 0;
        }
        particle3D.play(world,getPosX(),getPosY(),getPosZ());
        setMotion(getMotion().mul(0.975f,0.975f,0.975f));
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
                this.world.addParticle(ParticleTypes.CLOUD, this.getPosX() + RandomUtils.randomFloat(-0.4f, 0.4f), this.getPosY() + RandomUtils.randomFloat(-0.4f, 0.4f), this.getPosZ() + RandomUtils.randomFloat(-0.4f, 0.4f), 0, 0, 0);
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
        lifeTicks = 60;
        if (result.getType() == RayTraceResult.Type.ENTITY){
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (entity.isNonBoss()) {
                if (func_234616_v_() != null){
                    if (entity.getUniqueID() != func_234616_v_().getUniqueID()){
                        EntityUtils.addMotion(entity,RandomUtils.randomFloat(-1,1),0.5f,RandomUtils.randomFloat(-1,1));
                    }
                }
            }
        }else{
            remove();
        }
        this.doBlockCollisions();
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityCosmicFiend;
import org.astemir.forestcraft.registries.FCEntities;


public class EntityCosmicFire extends AbstractFireballEntity implements IAnimated {

    public int damage = 5;

    private Animation DEFAULT = new Animation(0,"default").time(0.52f).loop();

    private AnimationFactory factory = new AnimationFactory(this,DEFAULT);

    public EntityCosmicFire(EntityType<? extends EntityCosmicFire> p_i50160_1_, World p_i50160_2_) {
        super(p_i50160_1_, p_i50160_2_);
    }

    public EntityCosmicFire(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(FCEntities.COSMIC_FIRE, shooter, accelX, accelY, accelZ, worldIn);
    }

    public EntityCosmicFire(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(FCEntities.COSMIC_FIRE, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        if (!this.world.isRemote) {
            Entity entity = p_213868_1_.getEntity();
            Entity entity1 = this.func_234616_v_();
            if (!(entity instanceof EntityCosmicFiend)) {
                if (!entity1.getUniqueID().equals(entity.getUniqueID())) {
                    int i = entity.getFireTimer();
                    entity.setFire(5);
                    boolean flag = entity.attackEntityFrom(DamageSource.causeThrownDamage(this, entity1), damage);
                    if (!flag) {
                        entity.forceFireTicks(i);
                    } else if (entity1 instanceof LivingEntity) {
                        this.applyEnchantments((LivingEntity) entity1, entity);
                    }
                }
            }
        }
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        RayTraceResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onEntityHit((EntityRayTraceResult)result);
            if (!this.world.isRemote) {
                this.remove();
            }
        }
    }

    public boolean canBeCollidedWith() {
        return false;
    }


    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        factory.tick();
        factory.playAnimation(DEFAULT);
        if (ticksExisted > 100){
            remove();
        }
        super.tick();
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
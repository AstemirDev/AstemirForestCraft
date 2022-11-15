package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.api.math.RandomUtils;


public class EntityNoteProjectile extends ProjectileItemEntity {

    public int damage = 3;

    public EntityNoteProjectile(EntityType<? extends EntityNoteProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public EntityNoteProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.NOTE_PROJECTILE, throwerIn, worldIn);
    }

    public EntityNoteProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.NOTE_PROJECTILE, x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
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
    public void tick() {
        super.tick();
        if (ticksExisted > 20){
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 3);
                this.remove();
            }
        }
        int note = RandomUtils.randomInt(1,12);
        this.world.addParticle(ParticleTypes.NOTE, this.getPosX()+ RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), (double)note / 24.0D, 0, 0);
    }



    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        int i = RandomUtils.randomInt(damage,7);
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) i);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }

}
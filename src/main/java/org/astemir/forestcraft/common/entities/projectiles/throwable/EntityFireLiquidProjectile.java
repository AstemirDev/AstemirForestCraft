package org.astemir.forestcraft.common.entities.projectiles.throwable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItems;


public class EntityFireLiquidProjectile extends ProjectileItemEntity {

    public int damage = 5;

    public EntityFireLiquidProjectile(EntityType<? extends EntityFireLiquidProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public EntityFireLiquidProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.FIRE_LIQUID_PROJECTILE, throwerIn, worldIn);
    }

    public EntityFireLiquidProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.FIRE_LIQUID_PROJECTILE, x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return FCItems.FIRE_LIQUID;
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
    protected float getGravityVelocity() {
        return 0.04F;
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(ParticleTypes.SMOKE, this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0;i<8;i++) {
                this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.GLASS.getDefaultState()), this.getPosX() + RandomUtils.randomFloat(-0.15f, 0.15f), this.getPosY() + RandomUtils.randomFloat(-0.15f, 0.15f), this.getPosZ() + RandomUtils.randomFloat(-0.15f, 0.15f), 0.1D, 0.1D, 0.1D);
            }
            for (int i = 0; i < 32; ++i) {
                this.world.addParticle(ParticleTypes.FLAME, this.getPosX()+RandomUtils.randomFloat(-1f,1f), this.getPosY()+RandomUtils.randomFloat(-1f,1f), this.getPosZ()+RandomUtils.randomFloat(-1f,1f), 0D, 0D, 0D);
            }
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(ParticleTypes.LAVA, this.getPosX()+RandomUtils.randomFloat(-1f,1f), this.getPosY()+RandomUtils.randomFloat(-1f,1f), this.getPosZ()+RandomUtils.randomFloat(-1f,1f), 0D, 0D, 0D);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        damageAll();
        playSound(SoundEvents.BLOCK_GLASS_BREAK,1.5f,1.5f);
        playSound(SoundEvents.ITEM_FIRECHARGE_USE,1.5f,0.5f);
    }

    private void damageAll(){
        world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(2, 2, 2)).forEach((e) -> {
            if (!e.getUniqueID().equals(this.getUniqueID())) {
                e.setFire(10);
                e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) damage);
            }
        });
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            damageAll();
            playSound(SoundEvents.BLOCK_GLASS_BREAK,1.5f,1.5f);
            playSound(SoundEvents.ITEM_FIRECHARGE_USE,1.5f,0.5f);
            this.remove();
        }
    }
}
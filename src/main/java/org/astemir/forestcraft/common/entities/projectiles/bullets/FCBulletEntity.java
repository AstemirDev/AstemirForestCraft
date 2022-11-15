package org.astemir.forestcraft.common.entities.projectiles.bullets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.IFCProjectile;
import org.astemir.forestcraft.common.entities.projectiles.ITimeLiving;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class FCBulletEntity extends ProjectileItemEntity implements IFCProjectile {

    private static final DataParameter<ItemStack> SHOOTING_ITEM = EntityDataManager.createKey(FCBulletEntity.class, DataSerializers.ITEMSTACK);
    private int piercingCount = 0;
    private float damage = 1;
    private float knockback = 0;
    private int piercied = 0;
    private UUID shooter;

    public FCBulletEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public FCBulletEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public FCBulletEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public FCBulletEntity initialize(LivingEntity livingEntityIn, Item texture){
        this.shooter = livingEntityIn.getUniqueID();
        if (fallSpeed() == 0){
            setNoGravity(true);
        }
        setItem(texture.getDefaultInstance());
        return this;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(SHOOTING_ITEM, ItemStack.EMPTY);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("ShootingItem",getItemShooting().write(new CompoundNBT()));
        compound.putInt("PiercingCount",piercingCount);
        compound.putInt("Pierced",piercied);
        compound.putFloat("Damage",damage);
        compound.putFloat("Knockback",knockback);
        compound.putUniqueId("Shooter",shooter);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        dataManager.set(SHOOTING_ITEM,ItemStack.read((CompoundNBT) compound.get("ShootingItem")));
        piercingCount = compound.getInt("PiercingCount");
        piercied = compound.getInt("Pierced");
        damage = compound.getFloat("Damage");
        knockback = compound.getFloat("Knockback");
        shooter = compound.getUniqueId("Shooter");
    }

    @Override
    public void shoot(LivingEntity shooter,ItemStack shootingItem,float pitch, float yaw, float newPitch, float speed, float spread) {
        this.dataManager.set(SHOOTING_ITEM, shootingItem);
        EntityUtils.shootProjectileIgnoreMotion(this,shooter,pitch,yaw,newPitch,speed,spread);
    }


    @Override
    public void tick() {
        super.tick();
        if (this instanceof ITimeLiving){
            if (ticksExisted >= ((ITimeLiving)this).maxLivingTime()){
                breakAndDie();
            }
        }
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected void func_230299_a_(BlockRayTraceResult p_230299_1_) {
        super.func_230299_a_(p_230299_1_);
        onHitBlock(p_230299_1_.getPos(),p_230299_1_.getFace(),p_230299_1_.isInside());
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        onHitEntity(p_213868_1_.getEntity());
    }

    @Override
    public void onHitEntity(Entity entity) {
        if (entity instanceof LivingEntity){
            if (getShooter() != null) {
                if (getShooter().getUniqueID() != entity.getUniqueID()) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    onDamageEntity(livingEntity);
                    livingEntity.attackEntityFrom(DamageSource.causeThrownDamage(livingEntity, getShooter()), calculateDamage(livingEntity));
                    if (knockback > 0) {
                        Vector3 dir = EntityUtils.motion(this).normalize();
                        livingEntity.applyKnockback(knockback,-dir.x,-dir.z);
                    }
                    piercied++;
                    if (piercied >= getPiercingCount()) {
                        breakAndDie();
                    }
                }
            }else{
                breakAndDie();
            }
        }
    }

    @Override
    public void onHitBlock(BlockPos pos, Direction face,boolean inside) {
        if (canCollideWithBlock()){
            breakAndDie();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            playBreakingParticle();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void playBreakingParticle(){
        spawnParticle();
    }

    public void breakAndDie(){
        this.world.setEntityState(this, (byte) 3);
        spawnParticle();
        this.remove();
        onDie();
    }

    private void spawnParticle(){
        if (getDefaultItem() != null) {
            if (getDefaultItem() != Items.AIR) {
                Particle3D particle = new Particle3D(ParticleTypes.ITEM, getDefaultItem().getDefaultInstance()).size(0.1f, 0.1f, 0.1f).count(4).renderTimes(4).speed(0.25f, 0.25f, 0.25f).randomSpeed();
                particle.play(world, getPosX(), getPosY(), getPosZ());
            }
        }
    }

    @Override
    public Supplier<SoundEvent> getBreakSound() {
        return FCSounds.BULLET_IMPACT;
    }

    @Override
    public boolean canCollideWithBlock() {
        return true;
    }

    @Override
    public int getPiercingCount() {
        return piercingCount;
    }

    @Override
    public float getDefaultDamage() {
        return damage;
    }

    @Override
    public void setDefaultDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public float fallSpeed() {
        return 0;
    }

    @Override
    public LivingEntity getShooter() {
        if (world != null && shooter != null) {
            return world.getPlayerByUuid(shooter);
        }
        return null;
    }

    @Override
    protected float getGravityVelocity() {
        return fallSpeed();
    }

    @Override
    public void onDie() {
    }

    @Override
    public void onDamageEntity(LivingEntity entity) {
    }

    @Override
    public void setPiercingCount(int count) {
        this.piercingCount = count;
    }

    @Override
    public float getKnockback() {
        return knockback;
    }

    @Override
    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    @Override
    public float calculateDamage(LivingEntity entity) {
        return damage;
    }

    @Override
    public ItemStack getItemShooting() {
        return dataManager.get(SHOOTING_ITEM);
    }
}

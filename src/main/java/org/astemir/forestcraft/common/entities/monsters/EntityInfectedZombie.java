package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Random;

public class EntityInfectedZombie extends ZombieEntity{

    private static final DataParameter<Integer> ATTACK_TICKS = EntityDataManager.createKey(EntityInfectedZombie.class, DataSerializers.VARINT);

    public EntityInfectedZombie(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ATTACK_TICKS,0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }


    @Override
    public void setChild(boolean childZombie) {

    }



    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if (getPosition().getY() > worldIn.getSeaLevel()-2) {
            return super.canSpawn(worldIn, spawnReasonIn);
        }
        return false;
    }

    @Override
    protected void onDeathUpdate() {
        super.onDeathUpdate();
        if (this.deathTime == 1) {
            createSporeCloud(world, getPosition());
            world.playSound(getPosition().getX(), getPosition().getY(), getPosition().getZ(), SoundEvents.ENTITY_SPIDER_DEATH, SoundCategory.BLOCKS, 2, 0.5f, false);
        }
    }


    public void createSporeCloud(World worldIn,BlockPos pos){
        AreaEffectCloudEntity cloud = EntityType.AREA_EFFECT_CLOUD.create(worldIn);
        cloud.setRadius(1.5f);
        cloud.setParticleData(FCParticles.SPORE.get());
        cloud.setDuration(400);
        cloud.setWaitTime(0);
        cloud.setRadiusOnUse(-0.005f);
        cloud.setRadiusPerTick(-0.005f);
        cloud.addEffect(new EffectInstance(Effects.NAUSEA, 200, 0, false, false));
        cloud.addEffect(new EffectInstance(Effects.POISON, 200, 3, false, false));
        cloud.moveToBlockPosAndAngles(pos.add(0, 0.1, 0), 0, 0);
        worldIn.addEntity(cloud);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.INFECTED_ZOMBIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.INFECTED_ZOMBIE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.INFECTED_ZOMBIE_DEATH.get();
    }


    public int getAttackTicks() {
        return dataManager.get(ATTACK_TICKS);
    }

    public void setAttackTicks(int i){
        this.dataManager.set(ATTACK_TICKS,i);
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,18).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,2).createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        setAttackTicks(10);
        if (entityIn instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity)entityIn;
            livingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA,200,1,false,false));
        }
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (getAttackTicks() > 0) {
            setAttackTicks(getAttackTicks() - 1);
        }
    }

}


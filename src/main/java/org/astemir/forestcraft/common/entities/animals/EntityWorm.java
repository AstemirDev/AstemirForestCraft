package org.astemir.forestcraft.common.entities.animals;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.loot.WeightedRandom;
import java.util.Random;

public class EntityWorm extends CreatureEntity implements IAnimated, IDamageResistable {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityWorm.class, DataSerializers.VARINT);
    private Animation IDLE = new Animation(0,"idle").time(1.04f).loop();

    private AnimationFactory factory = new AnimationFactory(this,IDLE);

    private static WeightedRandom<Integer> RANDOM_WORM_TYPE = new WeightedRandom<Integer>().
            add(80,0).
            add(5,1).
            add(0.1f,2).build();

    public EntityWorm(EntityType type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        if (worldIn.getWorldInfo().isRaining()) {
            boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
            return res;
        }else{
            return false;
        }
    }


    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return 0;
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return 0;
    }


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (limbSwingAmount > 0.01 && limbSwing > 0.01) {
            factory.playAnimation(IDLE);
        }else{
            factory.stopAnimation(IDLE);
        }
    }


    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Type", getWormType());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(TYPE,compound.getInt("Type"));
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(TYPE,0);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        dataManager.set(TYPE, RANDOM_WORM_TYPE.random());
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }



    public int getWormType(){
        return dataManager.get(TYPE);
    }

    public void setWormType(int type){
        dataManager.set(TYPE,type);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if (world.isRaining()) {
            return world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
        }else{
            return false;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,1).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.2D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    private static DamageResistanceMap DRM = new DamageResistanceMap().put(DamageSource.IN_WALL,1);

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return DRM;
    }
}

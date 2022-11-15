package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.ai.AlphaInsaneDogLaserGoal;
import org.astemir.forestcraft.common.entities.ai.AttackPlayerWithoutInsaneHelmetGoal;
import org.astemir.forestcraft.common.entities.ai.AttackSurfaceAnimalsGoal;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.Random;
import java.util.function.Predicate;

public class EntityAlphaInsaneDog extends MonsterEntity implements IAnimated {

    private static final DataParameter<Integer> LAZER_TICKS = EntityDataManager.createKey(EntityAlphaInsaneDog.class, DataSerializers.VARINT);


    public static final Animation IDLE = new Animation(0,"idle").
            loop().speed(2).
            time(0.52f).conflict(4);

    public static final Animation HOWL = new Animation(1,"howl").
            speed(3f).
            time(0.52f);

    public static final Animation ATTACK = new Animation(2,"attack").
            time(0.52f);

    public static final Animation LASER = new Animation(3,"laser").
            loop().
            time(0.52f).conflict(2);

    public static final Animation RUN = new Animation(4,"run").
            loop().
            speed(0.5f).
            time(1.04f).conflict(0);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,HOWL,ATTACK,LASER,RUN){

    };


    public EntityAlphaInsaneDog(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 15;
    }


    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        spawnDog();
        spawnDog();
        spawnDog();
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.ALPHA_INSANE_DOG_AMBIENT.get();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(EntityInsaneDog.class));
        targetSelector.addGoal(1, new AttackPlayerWithoutInsaneHelmetGoal(this,10,false,false));
        targetSelector.addGoal(2,new NearestAttackableTargetGoal(this, VillagerEntity.class,false,false));
        targetSelector.addGoal(3, new AttackSurfaceAnimalsGoal(this,10,false,false));
        goalSelector.addGoal(1,new SwimGoal(this));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.7f,true));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this,0.5f));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        goalSelector.addGoal(6,new LookAtGoal(this, LivingEntity.class,20));
        goalSelector.addGoal(4,new AlphaInsaneDogLaserGoal(this));
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    public static boolean canMonsterSpawnInLight(EntityType<? extends MonsterEntity> type, IServerWorld world, SpawnReason reason, BlockPos blockpos, Random rand) {
        if (world.getLightFor(LightType.SKY, blockpos) > rand.nextInt(32)) {
            return false;
        } else {
            int i = world.getLight(blockpos);
            if (world.getWorld().isThundering()) {
                int j = world.getSkylightSubtracted();
                world.getLightManager().onBlockEmissionIncrease(blockpos, 10);
                i = world.getLight(blockpos);
                world.getLightManager().onBlockEmissionIncrease(blockpos, j);
            }
            return i <= rand.nextInt(8);
        }
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
    }


    public int getLazerTicks() {
        return dataManager.get(LAZER_TICKS);
    }

    public void setLazerTicks(int i){
        this.dataManager.set(LAZER_TICKS,i);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(ATTACK);
        if (entityIn instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity)entityIn;
            if (!livingEntity.isEntityUndead()) {
                livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE));
            }else{
                livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH));
            }
        }
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(LAZER_TICKS,0);
    }

    public void spawnDog(){
        EntityInsaneDog dog = FCEntities.INSANE_DOG.create(world);
        dog.moveToBlockPosAndAngles(new BlockPos(getPosXRandom(1),getPosY(),getPosZRandom(1)),0,0);
        dog.setAlpha(this);
        world.addEntity(dog);
    }


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if ((limbSwingAmount > 0.01 && limbSwing > 0.01) && getActivePotionEffect(Effects.SPEED) != null) {
            factory.playAnimation(RUN);
        }else{
            factory.playAnimation(IDLE);
        }

        if (rand.nextInt(300) == 0){
            factory.playAnimation(HOWL);
            playSound(FCSounds.ALPHA_INSANE_DOG_HOWL.get(), 2, RandomUtils.randomFloat(0.9f, 1.1f));
        }

        if (ticksExisted != 0 && (ticksExisted % 300 == 0) && getAttackTarget() != null){
            addPotionEffect(new EffectInstance(Effects.SPEED,200,2,false,false));
        }

        if (getLazerTicks() > 0) {
            for (int i = 0;i<6;i++) {
                spawnParticles();
            }
            if (getLazerTicks() % 15 == 0){
                playSound(FCSounds.LAZERBEAM.get(), 0.3f,RandomUtils.randomFloat(1.2f,1.4f));
            }
            setLazerTicks(getLazerTicks() - 1);
        }else{
            factory.stopAnimation(LASER);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.ALPHA_INSANE_DOG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    public void spawnParticles(){
        double dirX = Math.cos(Math.toRadians(getRotationYawHead()+90))+ RandomUtils.randomFloat(-0.1f,0.1f);
        double dirY = -Math.sin(Math.toRadians(rotationPitch))+ RandomUtils.randomFloat(-0.1f,0.1f);
        double dirZ = Math.sin(Math.toRadians(getRotationYawHead()+90))+ RandomUtils.randomFloat(-0.1f,0.1f);
        int distance = 9;
        for (double i = 0.75D;i<distance+0.75D*0.5D;i+=0.5D){
            Vector3d pos = new Vector3d(getPosX()+dirX*i,getPosYEye()+dirY*i-0.25,getPosZ()+dirZ*i);
            world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 0.1, pos.getY() - 0.1, pos.getZ() - 0.1, pos.getX() + 0.1, pos.getY() + 0.1, pos.getZ() + 0.1), new Predicate<LivingEntity>() {
                @Override
                public boolean test(LivingEntity livingEntity) {
                    return !(livingEntity instanceof EntityInsaneDog) && !(livingEntity instanceof EntityAlphaInsaneDog);
                }
            }).forEach((e)->{
                e.addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,0));
                e.attackEntityFrom(DamageSource.causeMobDamage(this),3);
            });
            this.world.addParticle(new RedstoneParticleData(1, 0, 0, 1), pos.getX(),pos.getY(),pos.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,100).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D).createMutableAttribute(Attributes.ARMOR,1).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1);
    }

    @Override
    public void playAmbientSound() {
        playSound(SoundEvents.ENTITY_WOLF_GROWL,0.75f,0.5f);
        super.playAmbientSound();
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

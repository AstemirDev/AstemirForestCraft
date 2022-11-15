package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.ai.AttackLivingEntitiesSkipRelative;
import org.astemir.forestcraft.common.entities.ai.AvoidTargetGoal;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityBakudanBall;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.EnumSet;
import java.util.Random;


public class EntityBakudan extends MonsterEntity implements IAnimated {


    private static final DataParameter<Boolean> HAS_BALL = EntityDataManager.createKey(EntityBakudan.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> THROW_TICKS = EntityDataManager.createKey(EntityBakudan.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> RECHARGE_TICKS = EntityDataManager.createKey(EntityBakudan.class, DataSerializers.VARINT);


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(22).
            speed(2).conflict(1,2);

    public static final Animation IDLE2 = new Animation(1,"idle2").
            time(31).
            speed(2).conflict(0,2);

    public static final Animation IDLE3 = new Animation(2,"idle3").
            time(13).
            speed(2).conflict(0,1);

     public static final Animation THROW = new Animation(3,"throw").
            time(5).
            speed(2).conflict(0,1,2,4,5);

     public static final Animation THROW2 = new Animation(4,"throw2").
            time(5).
            speed(2).conflict(0,1,2,3,5);

     public static final Animation THROW3 = new Animation(5,"throw3").
            time(5).
            speed(2).conflict(0,1,2,3,4);

     private AnimationFactory factory = new AnimationFactory(this,IDLE,IDLE2,IDLE3,THROW,THROW2,THROW3){
         @Override
         public void onAnimationEnd(Animation animation) {
             if (THROW.equals(animation) || THROW2.equals(animation) || THROW3.equals(animation)){
                 if (getAttackTarget() != null) {
                     EntityBakudanBall bakudanBall = new EntityBakudanBall(world, EntityBakudan.this);
                     Vector3d vector3d = getAttackTarget().getMotion();
                     double d0 = getAttackTarget().getPosX() + vector3d.x - getPosX();
                     double d1 = getAttackTarget().getPosYEye() - getPosY();
                     double d2 = getAttackTarget().getPosZ() + vector3d.z - getPosZ();
                     bakudanBall.shoot(d0, d1, d2, 0.5f, 0);
                     world.addEntity(bakudanBall);
                     playSound(FCSounds.BAKUDAN_THROW.get(), 1, RandomUtils.randomFloat(0.9f, 1.1f));
                     setHasBall(false);
                 }
             }
         }

     };


    public EntityBakudan(EntityType type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        playSound(FCSounds.BAKUDAN_RECHARGE.get(),1,1);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(HAS_BALL,true);
        dataManager.register(THROW_TICKS,0);
        dataManager.register(RECHARGE_TICKS,20);
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() != null){
            if (hasBall()){
                loseBall();
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(10,new ThrowBallGoal());
        goalSelector.addGoal(2,new AvoidTargetGoal(this, LivingEntity.class, 4, 0.5, 0.65));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(3, new AttackLivingEntitiesSkipRelative(this, LivingEntity.class, 10, false, false));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public void throwBall(){
        setRechargeTicks(80);
        setThrowTicks(10);
        if (RandomUtils.doWithChance(25)){
            factory.playAnimation(THROW);
        }else
        if (RandomUtils.doWithChance(25)){
            factory.playAnimation(THROW2);
        }else{
            factory.playAnimation(THROW3);
        }
    }


    public void loseBall(){
        setRechargeTicks(60);
        EntityBakudanBall bakudanBall = new EntityBakudanBall(world, this);
        bakudanBall.shoot(0,0.5,0,0.3f,0);
        world.addEntity(bakudanBall);
        setHasBall(false);
    }


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (rand.nextInt(600) == 0 && hasBall()){
            if (RandomUtils.doWithChance(45)){
                factory.playAnimation(IDLE2);
            }else{
                factory.playAnimation(IDLE3);
            }
        }
        if (!factory.isPlaying(IDLE2) && !factory.isPlaying(IDLE3) && !factory.isPlaying(THROW) && !factory.isPlaying(THROW2) && !factory.isPlaying(THROW3)){
            factory.playAnimation(IDLE);
        }
        if (getThrowTicks() > 0){
            setThrowTicks(getThrowTicks()-1);
        }
        if (getRechargeTicks() <= 0 && !hasBall()){
            setHasBall(true);
            playSound(FCSounds.BAKUDAN_RECHARGE.get(),1,1);
        }
        if (getRechargeTicks() > 0){
            setRechargeTicks(getRechargeTicks()-1);;
        }
    }


    public void setRechargeTicks(int i){
        dataManager.set(RECHARGE_TICKS,i);
    }

    public int getRechargeTicks(){
        return dataManager.get(RECHARGE_TICKS);
    }


    public void setThrowTicks(int i){
        dataManager.set(THROW_TICKS,i);
    }

    public int getThrowTicks(){
        return dataManager.get(THROW_TICKS);
    }

    public void setHasBall(boolean b){
        dataManager.set(HAS_BALL,b);
    }

    public boolean hasBall(){
        return dataManager.get(HAS_BALL);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.BAKUDAN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.BAKUDAN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.BAKUDAN_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }


    public class ThrowBallGoal extends Goal{

        private int delay = 0;

        public ThrowBallGoal() {
            setMutexFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return getAttackTarget() != null && hasBall() && canAttack(getAttackTarget());
        }

        @Override
        public boolean shouldContinueExecuting() {
            return getAttackTarget() != null && hasBall() && canAttack(getAttackTarget());
        }

        @Override
        public void resetTask() {
            delay = 0;
        }

        @Override
        public void startExecuting() {
            this.delay = 20;
        }


        @Override
        public void tick() {
            if (getAttackTarget() != null) {
                if (delay < 20) {
                    getLookController().setLookPositionWithEntity(getAttackTarget(), 30.0F, 30.0F);
                }
            }
            if (delay <= 0) {
                delay = 100;
                throwBall();
            }
            if (delay > 0){
                delay--;
            }
        }
    }

}

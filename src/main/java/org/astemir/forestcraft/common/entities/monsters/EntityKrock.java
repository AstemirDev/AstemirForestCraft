package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.ai.AttackLivingEntitiesSkipRelative;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;


public class EntityKrock extends MonsterEntity implements IAnimated {


    private static final DataParameter<Boolean> ROLLING = EntityDataManager.createKey(EntityKrock.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SPIKES = EntityDataManager.createKey(EntityKrock.class, DataSerializers.BOOLEAN);

    private Vector3d direction = new Vector3d(0,0,0);

    private int rollingTimer = 0;

    private int rollingTicks = 0;

    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(21).
            speed(2);

    public static final Animation SPIKE_DISABLING = new Animation(1,"spike_disabling").
            time(21).speed(1);

    public static final Animation SPIKE_ENABLING = new Animation(2,"spike_enabling").
            time(21).speed(1);

    public static final Animation START_ROLL = new Animation(3,"start_rolling").
            time(21).conflict(0);

    public static final Animation ROLL = new Animation(4,"rolling").
            time(5).loop().conflict(0);


    private AnimationFactory factory = new AnimationFactory(this,IDLE,SPIKE_DISABLING,SPIKE_ENABLING,START_ROLL,ROLL){

        @Override
        public void onAnimationTick(Animation animation, float tick) {
            if (START_ROLL.equals(animation)){
                if (tick == 1){
                    factory.playAnimation(SPIKE_ENABLING);
                }
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (START_ROLL.equals(animation)){
                if (getAttackTarget() != null) {
                    playAnimation(ROLL);
                    rollingTicks = 80;
                    direction = new Vector3d(EntityKrock.this.getAttackTarget().getPosX() - EntityKrock.this.getPosX(), 0.0D, EntityKrock.this.getAttackTarget().getPosZ() - EntityKrock.this.getPosZ()).normalize();
                    setRolling(true);
                }
            }
            if (SPIKE_ENABLING.equals(animation)){
                setSpikes(true);
            }
            if (SPIKE_DISABLING.equals(animation)){
                setSpikes(false);
            }
        }
    };


    public EntityKrock(EntityType type, World worldIn) {
        super(type, worldIn);
        stepHeight = 1;
    }

    @Override
    public void setHeadRotation(float yaw, int pitch) {
        if (!isRolling()) {
            super.setHeadRotation(yaw, pitch);
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ROLLING,false);
        dataManager.register(SPIKES,false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(1,new RollToTarget());
        goalSelector.addGoal(3,new LookRandomlyGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        goalSelector.addGoal(4,new RandomWalkingGoal(this,0.5D));
        targetSelector.addGoal(5, new AttackLivingEntitiesSkipRelative(this, PlayerEntity.class,10,false,false ));
    }


    public void setRolling(boolean b){
        dataManager.set(ROLLING,b);
    }

    public boolean isRolling(){
        return dataManager.get(ROLLING);
    }

    public void setSpikes(boolean b){
        dataManager.set(SPIKES,b);
    }

    public boolean isSpiked(){
        return dataManager.get(SPIKES);
    }


    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return 0f;
    }


    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (rollingTimer > 0){
            rollingTimer--;
        }
        if (isRolling()){
            if (ticksExisted % 8 == 0) {
                playSound(FCSounds.KROCK_STEP.get(),0.25f, RandomUtils.randomFloat(0.9f,1.1f));
                BlockState blockUnder = world.getBlockState(new BlockPos(getPosX(),getPosY()-1,getPosZ()));
                if (!blockUnder.isAir()) {
                    for (int i = 0;i<10;i++) {
                        world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockUnder), getPosX()+RandomUtils.randomFloat(-0.5f,0.5f), getPosY()+RandomUtils.randomFloat(0,0.5f), getPosZ()+RandomUtils.randomFloat(-0.5f,0.5f), 0.1f, 0.1f, 0.1f);
                    }
                }
            }
            world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox(), new Predicate<LivingEntity>() {
                @Override
                public boolean test(LivingEntity entity) {
                    return !(entity instanceof EntityKrock);
                }
            }).forEach((e)->{
                e.attackEntityFrom(DamageSource.causeMobDamage(this),4);
                e.setMotion(e.getMotion().add(getMotion()));
            });
        }else{
            factory.playAnimation(IDLE);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!isRolling() || source.isFireDamage() || source.isMagicDamage() || source.isExplosion()) {
            return super.attackEntityFrom(source, amount);
        }else{
            playSound(SoundEvents.ENTITY_IRON_GOLEM_HURT,1,RandomUtils.randomFloat(0.5f,0.75f));
        }
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.KROCK_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.KROCK_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.KROCK_DEATH.get();
    }


    public void setRollingTimer(int rollingTimer) {
        this.rollingTimer = rollingTimer;
    }



    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,20).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,2).createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    public class RollToTarget extends Goal {

        public RollToTarget() {
            setMutexFlags(EnumSet.of(Flag.LOOK,Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            return getAttackTarget() != null && rollingTimer <=0;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return rollingTimer <=0;
        }


        @Override
        public void startExecuting() {
            factory.playAnimation(START_ROLL);
        }

        @Override
        public void tick() {
            if (rollingTicks > 0) {
                Vector3 dir = new Vector3(direction.getX(),direction.getY(),direction.getZ());
                Vector2 rot = dir.yawPitchDegrees();
                rotationYaw = -rot.getX();
                renderYawOffset = rotationYaw;
                move(MoverType.SELF,direction.mul(0.4,0.4,0.4));
                rollingTicks--;
            }else{
                if (isRolling()) {
                    setRollingTimer(60);
                    setRolling(false);
                    factory.playAnimation(SPIKE_DISABLING);
                    factory.stopAnimation(ROLL);
                    resetTask();
                }
            }
        }
    }


}

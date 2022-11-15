package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import org.astemir.forestcraft.common.entities.ai.AttackPlayerWithoutInsaneHelmetGoal;
import org.astemir.forestcraft.common.entities.ai.AttackSurfaceAnimalsGoal;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

public class EntityInsaneDog extends MonsterEntity {

    private static final DataParameter<Integer> ATTACK_TICKS = EntityDataManager.createKey(EntityInsaneDog.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> LAZER_TICKS = EntityDataManager.createKey(EntityInsaneDog.class, DataSerializers.VARINT);
    private float lastYaw = 0;
    private float lastPitch = 0;
    private LivingEntity alpha;

    public EntityInsaneDog(EntityType type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.INSANE_DOG_AMBIENT.get();
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
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1,new SwimGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(EntityInsaneDog.class));
        targetSelector.addGoal(1, new AttackPlayerWithoutInsaneHelmetGoal(this,10,false,false));
        targetSelector.addGoal(2,new NearestAttackableTargetGoal(this, VillagerEntity.class,false,false));
        targetSelector.addGoal(3, new AttackSurfaceAnimalsGoal(this,10,false,false));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.7f,true));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this,0.5f));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        goalSelector.addGoal(6,new LookAtGoal(this, LivingEntity.class,20));
        goalSelector.addGoal(5,new InsaneDogLazerGoal(this));
        goalSelector.addGoal(10,new FollowAlphaGoal(this,0.7));
    }



    public int getAttackTicks() {
        return dataManager.get(ATTACK_TICKS);
    }

    public void setAttackTicks(int i){
        this.dataManager.set(ATTACK_TICKS,i);
    }

    public int getLazerTicks() {
        return dataManager.get(LAZER_TICKS);
    }

    public void setLazerTicks(int i){
        this.dataManager.set(LAZER_TICKS,i);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        setAttackTicks(10);
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
        dataManager.register(ATTACK_TICKS,0);
        dataManager.register(LAZER_TICKS,0);
    }

    public LivingEntity getAlpha() {
        return alpha;
    }

    public void setAlpha(LivingEntity alpha) {
        this.alpha = alpha;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (alpha != null) {
            if (alpha instanceof EntityAlphaInsaneDog) {
                if (((EntityAlphaInsaneDog) alpha).getFactory().isPlaying(EntityAlphaInsaneDog.HOWL)){
                    addPotionEffect(new EffectInstance(Effects.SPEED,200,1,false,true));
                }
            }
        }
        if (ticksExisted % 20 == 0) {
            if (getAlpha() == null) {
                world.getTargettableEntitiesWithinAABB(EntityAlphaInsaneDog.class, EntityPredicate.DEFAULT, this, this.getBoundingBox().grow(40.0D, 20.0D, 40.0D)).forEach((alpha) -> {
                    setAlpha(alpha);
                });
            }else{
                if (getAlpha().getLastAttackedEntity() != null){
                    if (getAlpha().getLastAttackedEntity().isAlive() && !getAlpha().getLastAttackedEntity().getUniqueID().equals(getUniqueID())){
                        setAttackTarget(getAlpha().getLastAttackedEntity());
                    }
                }
                if (!getAlpha().isAlive()){
                    setAlpha(null);
                }
            }
        }
        if (getAttackTicks() > 0) {
            setAttackTicks(getAttackTicks() - 1);
        }
        if (getLazerTicks() > 0) {
            for (int i = 0;i<4;i++) {
                spawnParticles();
            }
            if (getLazerTicks() % 15 == 0){
                playSound(FCSounds.LAZERBEAM.get(), 0.3f,RandomUtils.randomFloat(1.5f,1.6f));
            }
            setLazerTicks(getLazerTicks() - 1);
        }else{
            lastYaw = getRotationYawHead();
            lastPitch = rotationPitch;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.INSANE_DOG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    public void spawnParticles(){
        double dirX = Math.cos(Math.toRadians(getRotationYawHead()+90))+ RandomUtils.randomFloat(-0.1f,0.1f);
        double dirY = -Math.sin(Math.toRadians(rotationPitch))+ RandomUtils.randomFloat(-0.1f,0.1f);
        double dirZ = Math.sin(Math.toRadians(getRotationYawHead()+90))+ RandomUtils.randomFloat(-0.1f,0.1f);
        int distance = 4;
        for (double i = 0.75D;i<distance+0.75D*0.5D;i+=0.5D){
            Vector3d pos = new Vector3d(getPosX()+dirX*i,getPosYEye()+dirY*i-0.25,getPosZ()+dirZ*i);
            world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 0.1, pos.getY() - 0.1, pos.getZ() - 0.1, pos.getX() + 0.1, pos.getY() + 0.1, pos.getZ() + 0.1), new Predicate<LivingEntity>() {
                @Override
                public boolean test(LivingEntity livingEntity) {
                    return !(livingEntity instanceof EntityInsaneDog) && !(livingEntity instanceof EntityAlphaInsaneDog);
                }
            }).forEach((e)->{
                e.addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,0));
                e.attackEntityFrom(DamageSource.causeMobDamage(this),1);
            });
            this.world.addParticle(new RedstoneParticleData(1, 0, 0, 1), pos.getX(),pos.getY(),pos.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,25).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D);
    }

    public class InsaneDogLazerGoal extends Goal{

        private int timer = 0;
        private final EntityInsaneDog dog;

        public InsaneDogLazerGoal(EntityInsaneDog dog){
            this.dog = dog;
        }


        @Override
        public boolean shouldExecute() {
            return getAttackTarget() != null;
        }

        @Override
        public void tick() {
            if (timer <= 0){
                if (getDistanceSq(getAttackTarget()) < 4) {
                    timer = 250+RandomUtils.randomInt(100);
                    dog.setLazerTicks(60);
                }
            }else{
                timer--;
            }
        }

        @Override
        public void startExecuting() {
            timer = 250+RandomUtils.randomInt(100);
        }


        @Override
        public boolean shouldContinueExecuting() {
            return getAttackTarget() != null;
        }
    }

    public class FollowAlphaGoal extends Goal {

        private final EntityInsaneDog dog;
        private LivingEntity targetEntity;
        private double movePosX;
        private double movePosY;
        private double movePosZ;
        private final double speed;
        private final float maxTargetDistance;

        public FollowAlphaGoal(EntityInsaneDog entity, double speedIn) {
            this.dog = entity;
            this.speed = speedIn;
            this.maxTargetDistance = 60;
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            this.targetEntity = this.dog.getAlpha();
            if (this.targetEntity == null) {
                return false;
            } else if (this.targetEntity.getDistanceSq(this.dog) > (double)(this.maxTargetDistance * this.maxTargetDistance)) {
                return false;
            } else {
                Vector3d vector3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.dog, 4, 4, this.targetEntity.getPositionVec());
                if (vector3d == null) {
                    return false;
                } else {
                    this.movePosX = vector3d.x;
                    this.movePosY = vector3d.y;
                    this.movePosZ = vector3d.z;
                    return true;
                }
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return !this.dog.getNavigator().noPath() && this.targetEntity.isAlive() && this.targetEntity.getDistanceSq(this.dog) < (double)(this.maxTargetDistance * this.maxTargetDistance);
        }

        @Override
        public void resetTask() {
            this.targetEntity = null;
        }


        @Override
        public void startExecuting() {
            this.dog.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
        }
    }
}

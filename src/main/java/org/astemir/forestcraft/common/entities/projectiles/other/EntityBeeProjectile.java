package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.entities.animals.EntityKillerBee;

import java.util.EnumSet;
import java.util.Set;

public class EntityBeeProjectile extends BeeEntity {

    private PlayerEntity player;

    private int ticks = 0;


    public EntityBeeProjectile(EntityType<? extends BeeEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public void func_241356_K__() {

    }



    @Override
    public boolean hasStung() {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (ticks > 200){
            remove();
            spawnExplosionParticle();
        }else{
            ticks++;
        }
        if (player != null){
            if (getAttackTarget() == null) {
                if (player.getLastAttackedEntity() != null) {
                    if (!player.getLastAttackedEntity().getUniqueID().equals(getUniqueID())) {
                        setAttackTarget(player.getLastAttackedEntity());
                    }
                } else if (player.getLastDamageSource() != null) {
                    if (player.getLastDamageSource().getTrueSource() != null) {
                        if (player.getLastDamageSource().getTrueSource() instanceof LivingEntity) {
                            if (!player.getLastDamageSource().getTrueSource().getUniqueID().equals(getUniqueID())) {
                                setAttackTarget((LivingEntity) player.getLastDamageSource().getTrueSource());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 0;
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (player != null){
            if (source.getTrueSource() != null) {
                if (source.getTrueSource().getUniqueID().equals(player.getUniqueID())) {
                    return false;
                }
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    public PlayerEntity getPlayer() {
        return player;
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.goals.clear();
        targetSelector.goals.clear();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.4F, true));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2,new FollowQueenGoal(this,1.25));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10,true,false,(e)->true));
    }



    public class FollowQueenGoal extends Goal {

        private final EntityBeeProjectile bee;
        private LivingEntity targetEntity;
        private double movePosX;
        private double movePosY;
        private double movePosZ;
        private final double speed;
        private final float maxTargetDistance;

        public FollowQueenGoal(EntityBeeProjectile bee, double speedIn) {
            this.bee = bee;
            this.speed = speedIn;
            this.maxTargetDistance = 60;
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            this.targetEntity = this.bee.getPlayer();
            if (this.targetEntity == null) {
                return false;
            } else if (this.targetEntity.getDistanceSq(this.bee) > (double)(this.maxTargetDistance * this.maxTargetDistance)) {
                return false;
            } else {
                Vector3d vector3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.bee, 3, 4, this.targetEntity.getPositionVec());
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
            return !this.bee.getNavigator().noPath() && this.targetEntity.isAlive() && this.targetEntity.getDistanceSq(this.bee) < (double)(this.maxTargetDistance * this.maxTargetDistance);
        }

        @Override
        public void resetTask() {
            this.targetEntity = null;
        }


        @Override
        public void startExecuting() {
            this.bee.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
        }
    }
}

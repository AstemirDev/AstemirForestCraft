package org.astemir.forestcraft.common.entities.animals;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityBeeQueen;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;

public class EntityKillerBee extends BeeEntity {

    private EntityBeeQueen beeQueen;



    public EntityKillerBee(EntityType<? extends BeeEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void setBeeQueen(EntityBeeQueen beeQueen) {
        this.beeQueen = beeQueen;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        setAngerTime(99999);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
        if (beeQueen != null) {
            if (beeQueen.getAttackTarget() != null) {
                setAttackTarget(beeQueen.getAttackTarget());
            }
        }
    }

    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
            public boolean canEntityStandOnPos(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }
            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(false);
        flyingpathnavigator.setCanEnterDoors(true);
        return flyingpathnavigator;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 0;
    }

    public EntityBeeQueen getBeeQueen() {
        return beeQueen;
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

        private final EntityKillerBee bee;
        private LivingEntity targetEntity;
        private double movePosX;
        private double movePosY;
        private double movePosZ;
        private final double speed;
        private final float maxTargetDistance;

        public FollowQueenGoal(EntityKillerBee bee, double speedIn) {
            this.bee = bee;
            this.speed = speedIn;
            this.maxTargetDistance = 60;
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            this.targetEntity = this.bee.getBeeQueen();
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

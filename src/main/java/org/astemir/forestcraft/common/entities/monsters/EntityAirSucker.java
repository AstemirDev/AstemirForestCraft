package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.entities.ai.AttackPlayerWithoutScaleArmorGoal;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

public class EntityAirSucker extends SquidEntity {

    public EntityAirSucker(EntityType<? extends SquidEntity> type, World worldIn) {
        super(type, worldIn);
    }

    private LivingEntity suckedOn;

    private static final DataParameter<Boolean> SUCKED = EntityDataManager.createKey(EntityAirSucker.class, DataSerializers.BOOLEAN);


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.FOLLOW_RANGE,24).createMutableAttribute(Attributes.ATTACK_DAMAGE,4);
    }


    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(SUCKED,false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        targetSelector.addGoal(3, new AttackPlayerWithoutScaleArmorGoal(this,10,false,false));
        goalSelector.addGoal(3,new SweelGoal(this));
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if (suckedOn == null) {
            super.applyEntityCollision(entityIn);
        }
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (suckedOn == null) {
            entityIn.applyEntityCollision(this);
        }
    }

    public boolean isSucked(){
        return dataManager.get(SUCKED);
    }


    @Override
    public void livingTick() {
        super.livingTick();
        world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox(), new Predicate<LivingEntity>() {
            @Override
            public boolean test(LivingEntity entity) {
                return (entity instanceof PlayerEntity) && (suckedOn == null);
            }
        }).forEach((e)->{
            if (!((PlayerEntity)e).isCreative() && !e.isSpectator()) {
                suckedOn = e;
                dataManager.set(SUCKED, true);
            }
        });
        if (suckedOn != null){
            if (suckedOn.isAlive()){
                setPosition(suckedOn.getPosX(),suckedOn.getPosY()+suckedOn.getEyeHeight(),suckedOn.getPosZ());
                suckedOn.setAir(suckedOn.getAir()-5);
                if (suckedOn.getAir() <= -20) {
                    suckedOn.setAir(0);
                    suckedOn.attackEntityFrom(DamageSource.DROWN, 2.0F);
                }
                setAir(300);
                fallDistance = 0;
                rotationPitch = 0;
            }else{
                dataManager.set(SUCKED,false);
                suckedOn = null;
            }
        }
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    public static boolean spawnSucker(EntityType<EntityAirSucker> p_223365_0_, IWorld p_223365_1_, SpawnReason reason, BlockPos p_223365_3_, Random p_223365_4_) {
        return p_223365_4_.nextInt(8) == 0 && p_223365_3_.getY() > 5 && p_223365_3_.getY() < p_223365_1_.getSeaLevel()-15;
    }



    public class SweelGoal extends Goal {
        private final EntityAirSucker swellingCreeper;
        private LivingEntity attackTarget;

        public SweelGoal(EntityAirSucker entitycreeperIn) {
            this.swellingCreeper = entitycreeperIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            LivingEntity livingentity = this.swellingCreeper.getAttackTarget();

            return swellingCreeper.suckedOn == null || livingentity != null && this.swellingCreeper.getDistanceSq(livingentity) < 9.0D;
        }

        @Override
        public void startExecuting() {
            this.swellingCreeper.getNavigator().clearPath();
            this.attackTarget = this.swellingCreeper.getAttackTarget();
        }

        @Override
        public void resetTask() {
            this.attackTarget = null;
        }

        @Override
        public void tick() {
            super.tick();
            if (swellingCreeper.getAttackTarget() != null && swellingCreeper.suckedOn == null) {
                if (swellingCreeper.getAttackTarget().isInWater() && swellingCreeper.canEntityBeSeen(swellingCreeper.getAttackTarget())) {
                    Vector3d direction = new Vector3d(getAttackTarget().getPosX() - getPosX(), getAttackTarget().getPosY() - getPosY(), getAttackTarget().getPosZ() - getPosZ()).normalize();
                    swellingCreeper.setMovementVector((float) direction.x / 2, (float) direction.y / 2, (float) direction.z / 2);
                }
            }
        }
    }

}

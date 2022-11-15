package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.EnumSet;
import java.util.Random;

public class EntityWoodAbomination extends MonsterEntity {



    public EntityWoodAbomination(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WoodAbominationJumpAttackGoal(this,0.4f));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(EntityWoodAbomination.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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
    protected SoundEvent getAmbientSound() {
        return FCSounds.WOOD_ABOMINATION_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.WOOD_ABOMINATION_SCREAM.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.WOOD_ABOMINATION_AMBIENT.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.35D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,2);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),100,2));
        }
        return super.attackEntityAsMob(entityIn);
    }

    public class WoodAbominationJumpAttackGoal extends Goal{

        private final MobEntity leaper;
        private LivingEntity leapTarget;
        private final float leapMotionY;


        public WoodAbominationJumpAttackGoal(MobEntity leapingEntity, float leapMotionYIn) {
            this.leaper = leapingEntity;
            this.leapMotionY = leapMotionYIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            if (this.leaper.isBeingRidden()) {
                return false;
            } else {
                this.leapTarget = this.leaper.getAttackTarget();
                if (this.leapTarget == null) {
                    return false;
                } else {
                    double d0 = this.leaper.getDistanceSq(this.leapTarget);
                    if ((d0 < 128D)) {
                        if (!this.leaper.isOnGround()) {
                            return false;
                        } else {
                            return this.leaper.getRNG().nextInt(3) == 0;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return !this.leaper.isOnGround();
        }

        @Override
        public void tick() {
            super.tick();
            EntityUtils.lookAt(EntityWoodAbomination.this,Vector3.from(leapTarget.getPositionVec()).normalize());
        }

        @Override
        public void startExecuting() {
            Vector3d vector3d = this.leaper.getMotion();
            Vector3d vector3d1 = new Vector3d(this.leapTarget.getPosX() - this.leaper.getPosX(), 0.0D, this.leapTarget.getPosZ() - this.leaper.getPosZ());
            if (vector3d1.lengthSquared() > 1.0E-7D) {
                vector3d1 = vector3d1.normalize().scale(1.25D).add(vector3d.scale(0.2D));
            }
            this.leaper.setMotion(vector3d1.x, (double)this.leapMotionY, vector3d1.z);
        }
    }


}

package org.astemir.forestcraft.common.entities.animals;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;

import java.util.Random;

public class EntityKelpy extends WaterMobEntity implements IAnimated {



    public static final Animation IDLE = new Animation(0,"idle").
            loop().time(1.04f).conflict(1);

    public static final Animation SURFACE = new Animation(1,"surface").
            loop().time(0.52f).conflict(0);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,SURFACE);

    public EntityKelpy(EntityType type, World worldIn) {
        super(type, worldIn);
        this.moveController = new EntityKelpy.MoveHelperController(this);
        this.lookController = new DolphinLookController(this, 10);
    }


    @Override
    public void livingTick() {
        factory.tick();
        if (isInWaterOrBubbleColumn()){
            factory.playAnimation(IDLE);
        }else{
            factory.playAnimation(SURFACE);
        }
        super.livingTick();
    }

    public static boolean spawnKelpy(EntityType<EntityKelpy> p_223365_0_, IWorld p_223365_1_, SpawnReason reason, BlockPos p_223365_3_, Random p_223365_4_) {
        return p_223365_3_.getY() > 5 && p_223365_3_.getY() < p_223365_1_.getSeaLevel()-15;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }


    static class MoveHelperController extends MovementController {
        private final EntityKelpy dolphin;

        public MoveHelperController(EntityKelpy dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        @Override
        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setMotion(this.dolphin.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
                double d0 = this.posX - this.dolphin.getPosX();
                double d1 = this.posY - this.dolphin.getPosY();
                double d2 = this.posZ - this.dolphin.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setMoveForward(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.dolphin.rotationYaw = this.limitAngle(this.dolphin.rotationYaw, f, 10.0F);
                    this.dolphin.renderYawOffset = this.dolphin.rotationYaw;
                    this.dolphin.rotationYawHead = this.dolphin.rotationYaw;
                    float f1 = (float)(this.speed * this.dolphin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setAIMoveSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.rotationPitch = this.limitAngle(this.dolphin.rotationPitch, f2, 5.0F);
                        float f3 = MathHelper.cos(this.dolphin.rotationPitch * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.dolphin.rotationPitch * ((float)Math.PI / 180F));
                        this.dolphin.moveForward = f3 * f1;
                        this.dolphin.moveVertical = -f4 * f1;
                    } else {
                        this.dolphin.setAIMoveSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.dolphin.setAIMoveSpeed(0.0F);
                this.dolphin.setMoveStrafing(0.0F);
                this.dolphin.setMoveVertical(0.0F);
                this.dolphin.setMoveForward(0.0F);
            }
        }
    }


    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new PanicGoal(this, 0.75D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PlayerEntity.class,10,0.5D,0.75D));
        this.goalSelector.addGoal(0, new BreatheAirGoal(this));
        this.goalSelector.addGoal(1, new FindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    @Override
    public int getMaxAir() {
        return 4800;
    }

    @Override
    protected int determineNextAir(int currentAir) {
        return this.getMaxAir();
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_DOLPHIN_SWIM;
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,25).createMutableAttribute(Attributes.MOVEMENT_SPEED,1.75D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }


    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.3F;
    }


    @Override
    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(this.getAIMoveSpeed(), travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    @Override
    public int getVerticalFaceSpeed() {
        return 1;
    }

    @Override
    public int getHorizontalFaceSpeed() {
        return 1;
    }
}

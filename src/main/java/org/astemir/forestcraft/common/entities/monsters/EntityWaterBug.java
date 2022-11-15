package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.entity.ai.AIUtils;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.ai.FishMeleeAttackGoal;
import org.astemir.forestcraft.common.entities.ai.TargetGoal3D;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.EnumSet;
import java.util.Random;

public class EntityWaterBug extends WaterMobEntity implements IAnimated {

    public static final Animation IDLE = new Animation(0,"idle").time(1.04f).loop().conflict(1);
    public static final Animation IDLE_WATER = new Animation(1,"idle_water").time(1.04f).loop().conflict(0);
    public static final Animation MOVE = new Animation(2,"move").time(0.76f).loop();
    public static final Animation BITE = new Animation(3,"bite").time(0.52f);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,BITE,MOVE,IDLE_WATER);

    public EntityWaterBug(EntityType type, World worldIn) {
        super(type, worldIn);
        this.moveController = new EntityWaterBug.MoveHelperController(this);
        this.lookController = new DolphinLookController(this, 10);
    }

    public static boolean canSpawn(EntityType<EntityWaterBug> p_223365_0_, IWorld p_223365_1_, SpawnReason reason, BlockPos p_223365_3_, Random p_223365_4_) {
        return true;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(BITE);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public int getTalkInterval() {
        return 250;
    }

    static class MoveHelperController extends MovementController {
        private final EntityWaterBug dolphin;

        public MoveHelperController(EntityWaterBug dolphinIn) {
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
            }else {
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
        this.goalSelector.addGoal(0, new BreatheAirGoal(this));
        this.goalSelector.addGoal(0, new MoveToWaterGoal());
        this.goalSelector.addGoal(2, new FishMeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1D, 10));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new TargetGoal3D(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, AbstractFishEntity.class, 20, false, true, (e)->true));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, SquidEntity.class, 20, false, true, (e)->true));
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new EntityWaterBug.Navigator(this, worldIn);
    }

    static class Navigator extends SwimmerPathNavigator {
        Navigator(MobEntity turtle, World worldIn) {
            super(turtle, worldIn);
        }

        protected boolean canNavigate() {
            return true;
        }

        protected PathFinder getPathFinder(int p_179679_1_) {
            this.nodeProcessor = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeProcessor, p_179679_1_);
        }

        public boolean canEntityStandOnPos(BlockPos pos) {
            return !this.world.getBlockState(pos).isOpaqueCube(this.world, pos);
        }
    }

    @Override
    public int getMaxAir() {
        return 5000;
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
        return FCSounds.WATER_BUG_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.WATER_BUG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.WATER_BUG_DEATH.get();
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_HOSTILE_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (isInWater()){
            factory.playAnimation(IDLE_WATER);
        }else {
            factory.playAnimation(IDLE);
        }
        factory.playAnimation(MOVE);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,15).createMutableAttribute(Attributes.MOVEMENT_SPEED,1.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 16);
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
            this.setMotion(this.getMotion().scale(0.8D));
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


    public class MoveToWaterGoal extends Goal{

        private Vector3 waterPos;

        public MoveToWaterGoal() {
            setMutexFlags(EnumSet.of(Flag.MOVE,Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return !isInWater();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return !isInWater();
        }

        private void selectWaterPos(){
            BlockPos blockPos = AIUtils.nearestBlockPos(getPosition(),10,2,(pos)->{
                if (world.getFluidState(pos).isTagged(FluidTags.WATER)){
                    return true;
                }
                return false;
            });
            if (blockPos != null) {
                waterPos = Vector3.from(blockPos);
            }
        }

        @Override
        public void tick() {
            if (waterPos == null){
                selectWaterPos();
            }else
            if (waterPos.distanceTo(Vector3.from(getPositionVec())) > 200){
                selectWaterPos();
            }
            if (waterPos != null) {
                getNavigator().tryMoveToXYZ(waterPos.getX()+0.5f,waterPos.getY(),waterPos.getZ()+0.5f,1.5f);
            }
        }

    }
}

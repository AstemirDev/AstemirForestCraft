package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.ai.FishMeleeAttackGoal;
import org.astemir.forestcraft.common.entities.animals.EntityKelpy;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.entities.ai.TargetGoal3D;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.common.world.FCWorldData;

import java.util.EnumSet;
import java.util.Random;

public class EntityEaterOfTheDepths extends WaterMobEntity implements IAnimated {

    public static final Animation IDLE = new Animation(0,"idle").time(1.04f).loop();
    private AnimationFactory factory = new AnimationFactory(this,IDLE);
    private int magnetTicks = 0;

    public EntityEaterOfTheDepths(EntityType type, World worldIn) {
        super(type, worldIn);
        this.moveController = new EntityEaterOfTheDepths.MoveHelperController(this);
        this.lookController = new DolphinLookController(this, 10);
    }


    public static boolean spawnEater(EntityType<EntityEaterOfTheDepths> p_223365_0_, IWorld p_223365_1_, SpawnReason reason, BlockPos p_223365_3_, Random p_223365_4_) {
        return p_223365_4_.nextInt(FCConfigurationValues.EATER_OF_THE_DEPTHS_RARITY.getValue()) == 0 && p_223365_3_.getY() > 5 && p_223365_3_.getY() < p_223365_1_.getSeaLevel()-15;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }


    static class MoveHelperController extends MovementController {
        private final EntityEaterOfTheDepths dolphin;

        public MoveHelperController(EntityEaterOfTheDepths dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        @Override
        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setMotion(this.dolphin.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
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
        this.goalSelector.addGoal(0, new BreatheAirGoal(this));
        this.goalSelector.addGoal(1, new EOTDFindWaterGoal(this));
        this.goalSelector.addGoal(2, new FishMeleeAttackGoal(this, 1.2000000476837158D, true));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 20));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 12.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new TargetGoal3D(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, SquidEntity.class, 20, false, true, (e)->true));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, AbstractFishEntity.class, 20, false, true, (e)->true));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, DolphinEntity.class, 20, false, true, (e)->true));
        this.targetSelector.addGoal(3, new TargetGoal3D(this, EntityKelpy.class, 20, false, true, (e)->true));
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
        return FCSounds.EATER_OF_THE_DEPTHS_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.EATER_OF_THE_DEPTHS_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.EATER_OF_THE_DEPTHS_DEATH.get();
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_DOLPHIN_SWIM;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        factory.playAnimation(IDLE);
        if (getAttackTarget() != null) {
            if (getAttackTarget().getDistanceSq(this) < 32) {
                if (magnetTicks >= 160 && magnetTicks <= 190) {
                    getAttackTarget().addPotionEffect(new EffectInstance(Effects.BLINDNESS,120,0,false,false));
                    getAttackTarget().addPotionEffect(new EffectInstance(Effects.WEAKNESS,300,0,false,false));
                    EntityUtils.setMotion(getAttackTarget(), Vector3.from(getAttackTarget().getPositionVec()).direction(Vector3.from(getPositionVec())).normalize().mul(0.25f));
                }
                if (magnetTicks > 190){
                    magnetTicks = 0;
                }else{
                    magnetTicks++;
                }
            }
        }
    }


    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        FCWorldData data = FCWorldData.getData(world);
        if (data != null){
            if (!data.isSpawnAquamarine()){
                world.getPlayers().forEach((player)->{
                    player.sendStatusMessage(new TranslationTextComponent("message.forestcraft.aquamarine"),false);
                });
                data.setSpawnAquamarine(true);
            }
        }
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,100).createMutableAttribute(Attributes.MOVEMENT_SPEED,1.9D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D);
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

    private class EOTDFindWaterGoal extends Goal{

        private final CreatureEntity creature;

        public EOTDFindWaterGoal(CreatureEntity creature) {
            this.creature = creature;
        }

        @Override
        public boolean shouldExecute() {
            return this.creature.isOnGround() && !this.creature.world.getFluidState(this.creature.getPosition()).isTagged(FluidTags.WATER);
        }

        @Override
        public void startExecuting() {
            BlockPos blockpos = null;

            for(BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(this.creature.getPosX() - 5.0D), MathHelper.floor(this.creature.getPosY() - 2.0D), MathHelper.floor(this.creature.getPosZ() - 5.0D), MathHelper.floor(this.creature.getPosX() + 5.0D), MathHelper.floor(this.creature.getPosY()), MathHelper.floor(this.creature.getPosZ() + 5.0D))) {
                if (this.creature.world.getFluidState(blockpos1).isTagged(FluidTags.WATER)) {
                    blockpos = blockpos1;
                    break;
                }
            }

            if (blockpos != null) {
                this.creature.getMoveHelper().setMoveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 3.0D);
            }

        }
    }

}

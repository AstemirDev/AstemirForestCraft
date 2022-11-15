package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ITag;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.animals.EntityKillerBee;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityBeeQueen;


public class EntityJungleWasp extends MonsterEntity implements IFlyingAnimal, IAnimated {



    public static final Animation WINGS = new Animation(0,"wings").
            loop().
            time(1.12f);

    public static final Animation IDLE = new Animation(1,"idle").
            loop().
            time(1.04f);

    public static final Animation ATTACK = new Animation(2,"attack").
            time(0.52f);

    private AnimationFactory factory = new AnimationFactory(this,WINGS,IDLE,ATTACK);


    public EntityJungleWasp(EntityType<? extends EntityJungleWasp> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new FlyingMovementController(this, 80, true);
        this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathPriority(PathNodeType.COCOA, -1.0F);
        this.setPathPriority(PathNodeType.FENCE, -1.0F);
    }


    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return worldIn.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.75D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this,0.7f,0.01F));
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityKillerBee.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, BeeEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityBeeQueen.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeBeeStingDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.applyEnchantments(this, entityIn);
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).setBeeStingCount(((LivingEntity)entityIn).getBeeStingCount() + 1);
                int i = 0;
                if (this.world.getDifficulty() == Difficulty.NORMAL) {
                    i = 10;
                } else if (this.world.getDifficulty() == Difficulty.HARD) {
                    i = 20;
                }
                if (i > 0) {
                    ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, i * 20, 1));
                }
            }
            factory.playAnimation(ATTACK);
            this.playSound(SoundEvents.ENTITY_BEE_STING, 1.0F, 0.75F);
        }
        return flag;
    }

    public void livingTick() {
        super.livingTick();
        factory.tick();
        factory.playAnimation(WINGS);
        factory.playAnimation(IDLE);
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 15).createMutableAttribute(Attributes.FLYING_SPEED, 0.8F).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.7f).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }




    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
            public boolean canEntityStandOnPos(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(false);
        flyingpathnavigator.setCanEnterDoors(true);
        return flyingpathnavigator;
    }



    protected void playStepSound(BlockPos pos, BlockState blockIn) {
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_BEE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BEE_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }


    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.5F : sizeIn.height * 0.5F;
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected boolean makeFlySound() {
        return true;
    }


    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }

    protected void handleFluidJump(ITag<Fluid> fluidTag) {
        this.setMotion(this.getMotion().add(0.0D, 0.01D, 0.0D));
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d func_241205_ce_() {
        return new Vector3d(0.0D, (double)(0.5F * this.getEyeHeight()), (double)(this.getWidth() * 0.2F));
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}
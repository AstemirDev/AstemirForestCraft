package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Random;

public class EntityIguana extends MonsterEntity implements IAnimated {


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(1.04f).
            speed(2).conflict(2);

    public static final Animation SWITCH = new Animation(1,"switch").
            time(1.04f).
            speed(0.5f).conflict(0);

    public static final Animation RUN = new Animation(2,"run").
            loop().
            time(1.04f).speed(0.3f).conflict(0,1);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,SWITCH,RUN){
        @Override
        public void onAnimationEnd(Animation animation) {
            if (SWITCH.equals(animation)){
                factory.playAnimation(RUN);
            }
        }
    };

    public EntityIguana(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }



    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if ((limbSwingAmount > 0.01 && limbSwing > 0.01) && hurtTime <= 0) {
            if (getAttackTarget() != null) {
                if (!factory.isPlaying(RUN) && !factory.isPlaying(SWITCH)) {
                    factory.playAnimation(SWITCH);
                }
            }
        }else{
            if (!factory.isPlaying(SWITCH)){
                factory.playAnimation(IDLE);
            }
        }
    }



    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).getBlock() == Blocks.JUNGLE_LEAVES || worldIn.getBlockState(blockpos).getBlock() == Blocks.GRASS_BLOCK;
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
        return FCSounds.IGUANA_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.IGUANA_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.IGUANA_DEATH.get();
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,2);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON,40,0));
        }
        return super.attackEntityAsMob(entityIn);
    }


    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

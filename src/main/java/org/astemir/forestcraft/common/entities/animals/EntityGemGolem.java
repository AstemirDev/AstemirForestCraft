package org.astemir.forestcraft.common.entities.animals;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Random;

public class EntityGemGolem extends CreatureEntity implements IAnimated {


    public static final Animation IDLE = new Animation(0,"idle").time(1.04f).loop();
    public static final Animation ATTACK = new Animation(1,"attack").time(1.04f);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,ATTACK);

    public EntityGemGolem(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 5;
    }

    @Override
    protected void registerData() {
        super.registerData();

    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        goalSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(10, new MeleeAttackGoal(this,0.75D,true));
    }



    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        BlockState under = worldIn.getBlockState(getPosition().add(0,-1,0));
        if (under.equals(FCBlocks.PEARLSTONE.getDefaultState()) || under.equals(FCBlocks.MOSSY_PEARLSTONE.getDefaultState()) || under.equals(FCBlocks.GEM_ORE.getDefaultState()) || under.equals(FCBlocks.SHINY_PEARLSTONE.getDefaultState())){
            if (getPosition().getY() < 50){
                return world.getDimensionKey() == World.OVERWORLD;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.GEM_GOLEM_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.GEM_GOLEM_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.GEM_GOLEM_DEATH.get();
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,1);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(ATTACK);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        factory.playAnimation(IDLE);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

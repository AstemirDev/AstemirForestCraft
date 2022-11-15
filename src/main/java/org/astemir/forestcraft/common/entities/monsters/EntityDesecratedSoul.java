package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Random;

public class EntityDesecratedSoul extends MonsterEntity implements IAnimated {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityDesecratedSoul.class, DataSerializers.VARINT);


    public static final Animation IDLE = new Animation(0,"idle").time(1.04f).loop();
    public static final Animation IDLE1 = new Animation(1,"idle1").time(1.04f);
    public static final Animation ATTACK = new Animation(2,"attack").time(32);
    public static final Animation DEATH = new Animation(3,"death").time(1.1f).conflict(0,1,2);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,IDLE1,ATTACK,DEATH);


    public EntityDesecratedSoul(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }



    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        dataManager.set(TYPE, RandomUtils.randomInt(0,5));
        int health = RandomUtils.randomInt(10,25);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        this.setHealth(health);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Skin", dataManager.get(TYPE));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(TYPE,compound.getInt("Skin"));
    }


    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(TYPE,0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }



    public int getSkin(){
        return dataManager.get(TYPE);
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

    private int deathTicks = 0;

    @Override
    protected void onDeathUpdate() {
        factory.playAnimation(DEATH);
        ++this.deathTicks;
        if (this.deathTicks == 18) {
            remove();
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.SOUL, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(ATTACK);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public int getTalkInterval() {
        return 240;
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.GHOST_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.GHOST_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.GHOST_DEATH.get();
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,10).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D);
    }


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (rand.nextInt() == 10) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.addParticle(ParticleTypes.SOUL, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
        }
        if (!factory.isPlaying(IDLE1) && !factory.isPlaying(DEATH)) {
            factory.playAnimation(IDLE);
        }
        if (rand.nextInt(800) == 0){
            if (getAttackTarget() == null) {
                if (!factory.isPlaying(ATTACK) && !factory.isPlaying(DEATH)) {
                    factory.playAnimation(IDLE1);
                }
            }
        }
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class EntityNightWatcher extends MonsterEntity {


    private static final DataParameter<Integer> ATTACK_TICKS = EntityDataManager.createKey(EntityNightWatcher.class, DataSerializers.VARINT);

    public EntityNightWatcher(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 5;
    }


    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ATTACK_TICKS,0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 64.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void setChild(boolean childZombie) {

    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    @Override
    public void setAttackTarget(LivingEntity entitylivingbaseIn) {
        if (getAttackTarget() == null) {
            entitylivingbaseIn.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 60, 10, false, false));
            playSound(FCSounds.NIGHT_WATCHER_SCREAM.get(), 2, RandomUtils.randomFloat(0.9f, 1.1f));
        }
        super.setAttackTarget(entitylivingbaseIn);
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
        return SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }


    public int getAttackTicks() {
        return dataManager.get(ATTACK_TICKS);
    }

    public void setAttackTicks(int i){
        this.dataManager.set(ATTACK_TICKS,i);
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,2).createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }


    @Override
    public void livingTick() {
        super.livingTick();
        if (this.world.isRemote) {
            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.PORTAL, this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }
        if (ticksExisted % 60 == 0) {
            if (getAttackTarget() != null) {
                setAttackTicks(20);
                getAttackTarget().addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 20, 15, false, false));
                playSound(FCSounds.NIGHT_WATCHER_SCREAM.get(), 2, RandomUtils.randomFloat(0.9f, 1.1f));
                teleportRandomly(getAttackTarget());
            }
        }
        if (getAttackTicks() > 0) {
            setAttackTicks(getAttackTicks() - 1);
        }
    }

    private void teleportRandomly(LivingEntity entity) {
        if (!world.isRemote) {
            for (int i = 0; i < 32; ++i) {
                entity.world.addParticle(ParticleTypes.PORTAL, entity.getPosition().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), entity.getPosition().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), entity.getPosition().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
            }
            entity.attackEntityFrom(DamageSource.MAGIC, 2);
            entity.world.playSound(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1, 1, false);
            double d0 = entity.getPosX() + (RandomUtils.randomFloat(-8, 8) - 0.5D);
            double d1 = entity.getPosY() + (RandomUtils.randomInt(10));
            double d2 = entity.getPosZ() + (RandomUtils.randomFloat(-8, 8) - 0.5D);
            if (entity instanceof ServerPlayerEntity){
                ((ServerPlayerEntity)entity).teleport((ServerWorld) world,d0,d1,d2,0,0);
            }else {
                entity.setLocationAndAngles(d0, d1, d2,0,0);
            }
            for (int i = 0; i < 32; ++i) {
                entity.world.addParticle(ParticleTypes.PORTAL, entity.getPosition().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), entity.getPosition().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), entity.getPosition().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
            }
            entity.world.playSound(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1, 1, false);
        }
    }

}

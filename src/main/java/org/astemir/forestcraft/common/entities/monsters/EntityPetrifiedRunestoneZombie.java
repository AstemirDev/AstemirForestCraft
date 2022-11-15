package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

public class EntityPetrifiedRunestoneZombie extends ZombieEntity{


    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityPetrifiedRunestoneZombie.class, DataSerializers.VARINT);


    public EntityPetrifiedRunestoneZombie(EntityType type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(TYPE,0);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        dataManager.set(TYPE, RandomUtils.randomInt(0,3));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Skin", dataManager.get(TYPE));
    }

    public int getSkin(){
        return dataManager.get(TYPE);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(TYPE,compound.getInt("Skin"));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.PETRIFIED_ZOMBIE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.PETRIFIED_ZOMBIE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.PETRIFIED_ZOMBIE_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,35).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.18D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,4).createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

}

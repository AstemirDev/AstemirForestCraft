package org.astemir.forestcraft.common.entities.animals;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityDeer extends AnimalEntity{


    private static final DataParameter<Integer> DEER_TYPE = EntityDataManager.createKey(EntityDeer.class, DataSerializers.VARINT);


    public EntityDeer(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DEER_TYPE,0);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (WorldUtils.isBiomes(worldIn.getBiome(getPosition()),Biomes.SNOWY_TUNDRA,Biomes.SNOWY_TAIGA,Biomes.SNOWY_TAIGA_HILLS,Biomes.ICE_SPIKES,Biomes.SNOWY_TAIGA_MOUNTAINS)){
            setDeerType(1);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setDeerType(compound.getInt("Type"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Type",getDeerType());
    }

    public int getDeerType(){
        return dataManager.get(DEER_TYPE);
    }

    public void setDeerType(int i){
        dataManager.set(DEER_TYPE,i);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(3, new TemptGoal(this, 0.6D, Ingredient.fromItems(FCItems.BLUEBERRY), false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 0.6D));
        goalSelector.addGoal(2, new BreedGoal(this, 0.6D));
        goalSelector.addGoal(2, new DeerPanicGoal(this, 0.75D));
        goalSelector.addGoal(4, new DeerAvoidGoal(this, PlayerEntity.class, 16.0F, 0.5D, 0.75D));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        goalSelector.addGoal(3, new DeerAttackGoal(this,0.6D,true));
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    public static boolean canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).isIn(Blocks.GRASS_BLOCK) && worldIn.getLightSubtracted(pos, 0) > 8;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        playSound(FCSounds.DEER_HORNS.get(),1, RandomUtils.randomFloat(0.9f,1.1f));
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.DEER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.DEER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.DEER_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,20).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == FCItems.BLUEBERRY;
    }


    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        EntityDeer deerEntity = FCEntities.DEER.create(p_241840_1_);
        deerEntity.setDeerType(this.rand.nextBoolean() ? this.getDeerType() : ((EntityDeer)p_241840_2_).getDeerType());
        return deerEntity;
    }


    public class DeerPanicGoal extends PanicGoal{

        public DeerPanicGoal(CreatureEntity creature, double speedIn) {
            super(creature, speedIn);
        }


        @Override
        public boolean shouldContinueExecuting() {
            return !(EntityDeer.this.getHealth() > EntityDeer.this.getMaxHealth()/2);
        }

        @Override
        public boolean shouldExecute() {
            return !(EntityDeer.this.getHealth() > EntityDeer.this.getMaxHealth()/2);
        }
    }

    public class DeerAvoidGoal extends AvoidEntityGoal{

        public DeerAvoidGoal(CreatureEntity entityIn, Class classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(entityIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        }


        @Override
        public boolean shouldContinueExecuting() {
            return !(EntityDeer.this.getHealth() > EntityDeer.this.getMaxHealth()/2);
        }

        @Override
        public boolean shouldExecute() {
            return !(EntityDeer.this.getHealth() > EntityDeer.this.getMaxHealth()/2);
        }
    }

    public class DeerAttackGoal extends MeleeAttackGoal{
        public DeerAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
            super(creature, speedIn, useLongMemory);
        }

        @Override
        public boolean shouldContinueExecuting() {
            return EntityDeer.this.getHealth() < EntityDeer.this.getMaxHealth()/2;
        }

        @Override
        public boolean shouldExecute() {
            return EntityDeer.this.getHealth() < EntityDeer.this.getMaxHealth()/2;
        }
    }


}

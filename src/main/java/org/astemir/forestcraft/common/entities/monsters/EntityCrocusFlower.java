package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCrocusPetal;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import javax.annotation.Nullable;
import java.util.Random;


public class EntityCrocusFlower extends MonsterEntity implements IRangedAttackMob, IAnimated {

    public static final Animation IDLE = new Animation(0,"idle").time(1.04f).loop();
    public static final Animation SHOOT = new Animation(1,"shoot").time(1.04f).conflict(0);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,SHOOT);

    public EntityCrocusFlower(EntityType type, World worldIn) {
        super(type, worldIn);
        this.renderYawOffset=0;
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }


    public static boolean canMonsterSpawnInLight(EntityType<? extends MonsterEntity> type, IServerWorld world, SpawnReason reason, BlockPos blockpos, Random rand) {
        if (world.getLightFor(LightType.SKY, blockpos) > rand.nextInt(32)) {
            return false;
        } else {
            int i = world.getLight(blockpos);

            if (world.getWorld().isThundering()) {
                int j = world.getSkylightSubtracted();
                world.getLightManager().onBlockEmissionIncrease(blockpos, 10);
                i = world.getLight(blockpos);
                world.getLightManager().onBlockEmissionIncrease(blockpos, j);
            }

            return i <= rand.nextInt(8);
        }
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return worldIn.getBlockState(getPosition().down()).isIn(Blocks.GRASS_BLOCK) && world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        double x = getPosX();
        double y = getPosY();
        double z = getPosZ();
        spawnCrocusBlock(worldIn,new BlockPos(x,y,z));
        spawnCrocusBlock(worldIn,new BlockPos(x+1,y,z));
        spawnCrocusBlock(worldIn,new BlockPos(x+-1,y,z));
        spawnCrocusBlock(worldIn,new BlockPos(x,y,z+1));
        spawnCrocusBlock(worldIn,new BlockPos(x,y,z-1));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void spawnCrocusBlock(IServerWorld world,BlockPos pos){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.MOB_GRIEFING)) {
            //world.setBlockState(pos, FCBlocks.CROCUS_FLOWER.getDefaultState(), 11);
        }
    }

    @Override
    public void setMotion(Vector3d motionIn) {

    }

    @Override
    public void setMotion(double x, double y, double z) {

    }

    @Override
    protected void registerData() {
        super.registerData();
        lookController = new LookController(this){
            @Override
            public void setLookPosition(Vector3d lookVector) {

            }

            @Override
            public void setLookPositionWithEntity(Entity entityIn, float deltaYaw, float deltaPitch) {

            }

            @Override
            public void setLookPosition(double x, double y, double z) {

            }

            @Override
            public void setLookPosition(double x, double y, double z, float deltaYaw, float deltaPitch) {

            }
        };
        moveController = new MovementController(this){
            @Override
            public void setMoveTo(double x, double y, double z, double speedIn) {

            }

            @Override
            public void tick() {

            }
        };
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 20, 10.0F));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.8f,true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class,10,false,false,(entity)-> true));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public void onDeath(DamageSource cause) {
        BlockPos.getAllInBox(new AxisAlignedBB(getPosition()).grow(2.5,2.5,2.5)).forEach((block)->{
            if (world.getBlockState(block).equals(FCBlocks.CROCUS_FLOWER.getDefaultState())) {
                world.setBlockState(block, Blocks.AIR.getDefaultState());
            }
        });
        super.onDeath(cause);
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(SHOOT);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (!factory.isPlaying(SHOOT)) {
            factory.playAnimation(IDLE);
        }
        setPosition(getPosX(),getPosY(),getPosZ());
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,30).createMutableAttribute(Attributes.MOVEMENT_SPEED,0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1);
    }

    @Override
    public int getVerticalFaceSpeed() {
        return 0;
    }

    @Override
    public int getHorizontalFaceSpeed() {
        return 0;
    }

    @Override
    public int getFaceRotSpeed() {
        return 0;
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_WET_GRASS_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GRASS_BREAK;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        factory.playAnimation(SHOOT);
        for (int i = 0;i<10;i++) {
            EntityCrocusPetal petal = new EntityCrocusPetal(this.world, getPosX()-0.25,getPosY(),getPosZ()-0.25);
            petal.setShooter(this);
            petal.setItem(FCItems.CROCUS_PETALS.getDefaultInstance());
            petal.shoot(0,1,0,1.75f,16);
            this.world.addEntity(petal);
        }
        playSound(FCSounds.CROCUS_FLOWER_SHOOT.get(),1,RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

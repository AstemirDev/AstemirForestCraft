package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.loot.WeightedRandom;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.client.animator.Animation;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class EntityTerrorBringer extends EntityTamableMonster implements IRideable,IJumpingMount, IAnimated {

    private static final DataParameter<Integer> ATTACK_TICKS = EntityDataManager.createKey(EntityTerrorBringer.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.createKey(EntityTerrorBringer.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> MOVING_RIDER = EntityDataManager.createKey(EntityTerrorBringer.class, DataSerializers.BOOLEAN);
    protected float jumpPower;

    private static WeightedRandom<Integer> RANDOM_BIRD_TYPE = new WeightedRandom<Integer>().
            add(80,0).
            add(70,1).
            add(60,2).
            add(40,3).
            add(1,4).build();

    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(21).
            speed(2).conflict(1);
    public static final Animation RUN = new Animation(1,"run").
            time(14).conflict(0).loop();
    public static final Animation ATTACK = new Animation(2,"head_attack").
            time(14);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,RUN,ATTACK);


    public EntityTerrorBringer(EntityType type, World worldIn) {
        super(type, worldIn);
    }



    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ATTACK_TICKS,0);
        dataManager.register(SKIN_TYPE,0);
    }


    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return 0f;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        setSkinType(RANDOM_BIRD_TYPE.random());
        if (getSkinType() == 4){
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(35);
            setHealth(35.0F);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5);
            setCustomAttributes();
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setSkinType(compound.getInt("Type"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Type",getSkinType());
    }


    public int getSkinType(){
        return dataManager.get(SKIN_TYPE);
    }

    public void setSkinType(int i){
        dataManager.set(SKIN_TYPE,i);
    }


    @Override
    public void updatePassenger(Entity passenger) {
        positionRider(passenger, Entity::setPosition);
    }

    private void positionRider(Entity entity, Entity.IMoveCallback callback) {
        if (this.isPassenger(entity)) {
            double d0 = this.getPosY() + this.getMountedYOffset() + entity.getYOffset();
            Vector3d dir = EntityUtils.direction(entity).mul(-0.5f,0,-0.5f);
            callback.accept(entity, this.getPosX()+dir.x, d0, this.getPosZ()+dir.z);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.8f,true));
        goalSelector.addGoal(10,new EatNearbyFoodGoal());
        goalSelector.addGoal(3, new TerrorBringerTempGoal(this, 0.6D, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class,10,false,false,(entity)-> true));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(ATTACK);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() instanceof LivingEntity){
            world.getEntitiesWithinAABB(FCEntities.TERROR_BRINGER,getBoundingBox().grow(30,10,30),(entity)->{return (entity.getRevengeTarget() == null);}).forEach((entity)->{
                entity.setRevengeTarget(getRevengeTarget());
            });
        }
        return super.attackEntityFrom(source, amount);
    }


    @Override
    protected float getSoundPitch() {
        if (getSkinType() == 4){
           return 0.8f;
        }
        return super.getSoundPitch();
    }


    @Override
    public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        if (!this.isBeingRidden() && getOwner() != null) {
            if (getOwnerId().equals(p_230254_1_.getUniqueID())) {
                if (!this.world.isRemote) {
                    p_230254_1_.startRiding(this);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        if (isTamed()) {
            return super.canBeLeashedTo(player);
        }
        return false;
    }

    @Override
    public boolean canBeSteered() {
        if (getOwnerId() != null && getControllingPassenger() != null) {
            return this.getControllingPassenger().getUniqueID().equals(getOwnerId());
        }else{
            return false;
        }
    }

    private boolean ride(MobEntity mount, Vector3d p_233622_3_) {
        if (!mount.isAlive() && !isTamed()) {
            return false;
        } else {
            LivingEntity entity = (LivingEntity) getControllingPassenger();
            if (mount.isBeingRidden() && mount.canBeSteered() && entity instanceof PlayerEntity) {
                this.rotationYaw = entity.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = entity.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.renderYawOffset = this.rotationYaw;
                this.rotationYawHead = this.renderYawOffset;

                if (this.jumpPower > 0.0F && this.onGround) {
                    double d0 = 0.5 * (double)this.jumpPower * (double)this.getJumpFactor();
                    double d1;
                    if (this.isPotionActive(Effects.JUMP_BOOST)) {
                        d1 = d0 + (double)((float)(this.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
                    } else {
                        d1 = d0;
                    }

                    Vector3d vector3d = this.getMotion();
                    this.setMotion(vector3d.x, d1, vector3d.z);
                    net.minecraftforge.common.ForgeHooks.onLivingJump(this);
                    if (entity.moveForward > 0.0F) {
                        float f2 = MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F));
                        float f3 = MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F));
                        this.setMotion(this.getMotion().add((double)(-0.4F * f2 * this.jumpPower), 0.0D, (double)(0.4F * f3 * this.jumpPower)));
                    }

                    this.jumpPower = 0.0F;
                }
                this.jumpMovementFactor = this.getAIMoveSpeed() * 0.05F;
                if (mount.canPassengerSteer()) {
                    mount.setAIMoveSpeed(this.getMountedSpeed()*0.5f);
                    this.travelTowards(new Vector3d(entity.moveStrafing,jumpPower,entity.moveForward));
                } else {
                    mount.func_233629_a_(mount, false);
                    mount.setMotion(Vector3d.ZERO);
                }
                if (this.onGround) {
                    this.jumpPower = 0.0F;
                }
                return true;
            } else {
                mount.stepHeight = 0.5F;
                this.travelTowards(p_233622_3_);
                return false;
            }
        }
    }

    @Override
    public void travel(Vector3d travelVector) {
        this.ride(this, travelVector);
    }

    public int getAttackTicks() {
        return dataManager.get(ATTACK_TICKS);
    }

    public void setAttackTicks(int i){
        this.dataManager.set(ATTACK_TICKS,i);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        LivingEntity player = (LivingEntity) getControllingPassenger();
        boolean isMovingByPlayer = false;
        if (player != null){
            isMovingByPlayer = player.moveForward > 0 || player.moveStrafing > 0 || player.moveForward < 0 || player.moveStrafing < 0;
        }
        if ((limbSwingAmount > 0.01 && limbSwing > 0.01) || isMovingByPlayer) {
            factory.playAnimation(RUN);
        } else {
            factory.playAnimation(IDLE);
        }
        if (getAttackTicks() > 0) {
            setAttackTicks(getAttackTicks() - 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.TERROR_BRINGER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.TERROR_BRINGER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.TERROR_BRINGER_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,25).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.6D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D);
    }


    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
    }

    @Override
    public boolean boost() {
        return false;
    }

    @Override
    public void travelTowards(Vector3d travelVec) {
        super.travel(travelVec);
    }

    @Override
    public float getMountedSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    public void setJumpPower(int jumpPowerIn) {
        if (this.isTamed()) {
            if (jumpPowerIn < 0) {
                jumpPowerIn = 0;
            }

            if (jumpPowerIn >= 90) {
                this.jumpPower = 1.0F;
            } else {
                this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
            }

        }
    }

    @Override
    public boolean canJump() {
        return isTamed();
    }

    @Override
    public void handleStartJump(int jumpPower) {

    }

    @Override
    public void handleStopJump() {

    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    class TerrorBringerTempGoal extends TemptGoal{

        public TerrorBringerTempGoal(CreatureEntity creatureIn, double speedIn, boolean scaredByPlayerMovementIn) {
            super(creatureIn, speedIn, Ingredient.EMPTY, scaredByPlayerMovementIn);
        }

        @Override
        protected boolean isTempting(ItemStack stack) {
            if (stack.isFood()){
                return (stack.getItem()).getFood().isMeat();
            }
            return false;
        }
    }

    public void tame(PlayerEntity playerEntity){
        if (playerEntity != null) {
            if (RandomUtils.doWithChance(5)) {
                world.setEntityState(EntityTerrorBringer.this, (byte) 7);
                setTamedBy(playerEntity);
            }
        }
    }

    class EatNearbyFoodGoal extends Goal{
        public EatNearbyFoodGoal() {
        }

        @Override
        public boolean shouldExecute() {
            return EntityTerrorBringer.this.getAttackTicks() == 0;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return EntityTerrorBringer.this.getRevengeTarget() == null;
        }

        @Override
        public void tick() {
            List<ItemEntity> list = EntityTerrorBringer.this.world.getEntitiesWithinAABB(EntityType.ITEM, EntityTerrorBringer.this.getBoundingBox().grow(1, 1, 1), new Predicate<ItemEntity>() {
                @Override
                public boolean test(ItemEntity itemEntity) {
                    if (itemEntity.getItem().isFood()){
                        return itemEntity.getItem().getItem().getFood().isMeat();
                    }
                    return false;
                }
            });
            if (!list.isEmpty() && getAttackTicks() <= 0){
                ItemEntity itemEntity = list.get(0);
                factory.playAnimation(ATTACK);
                if (itemEntity.getThrowerId() != null && !isTamed()){
                    tame(world.getPlayerByUuid(itemEntity.getThrowerId()));
                }
                if (!world.isRemote) {
                    ((ServerWorld)world).getServer().getPlayerList().sendPacketToAllPlayers(new SPlaySoundEffectPacket( SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, EntityTerrorBringer.this.getPosX(), EntityTerrorBringer.this.getPosY(), EntityTerrorBringer.this.getPosZ(),2, 0.5f));
                    for (int i = 0; i < 10; i++) {
                        ((ServerWorld)world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, itemEntity.getItem()), itemEntity.getPosX() + RandomUtils.randomFloat(-0.25f, 0.25f), itemEntity.getPosY(), itemEntity.getPosZ() + RandomUtils.randomFloat(-0.25f, 0.25f), 0, 0, 0,0,0.1);
                    }
                }
                EntityTerrorBringer.this.addPotionEffect(new EffectInstance(Effects.SLOWNESS,10,10,false,false));
                EntityTerrorBringer.this.addPotionEffect(new EffectInstance(Effects.REGENERATION,100,1,false,false));
                itemEntity.remove();
            }
        }
    }

    public class OwnerHurtByTargetGoal extends TargetGoal {

        private final EntityTamableMonster tameable;
        private LivingEntity attacker;
        private int timestamp;

        public OwnerHurtByTargetGoal(EntityTamableMonster theDefendingTameableIn) {
            super(theDefendingTameableIn, false);
            this.tameable = theDefendingTameableIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean shouldExecute() {
            if (this.tameable.isTamed() && !this.tameable.isSitting()) {
                LivingEntity livingentity = this.tameable.getOwner();
                if (livingentity == null) {
                    return false;
                } else {
                    this.attacker = livingentity.getRevengeTarget();
                    int i = livingentity.getRevengeTimer();
                    return i != this.timestamp && this.isSuitableTarget(this.attacker, EntityPredicate.DEFAULT) && this.tameable.shouldAttackEntity(this.attacker, livingentity);
                }
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            this.goalOwner.setAttackTarget(this.attacker);
            LivingEntity livingentity = this.tameable.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getRevengeTimer();
            }

            super.startExecuting();
        }
    }

    public class OwnerHurtTargetGoal extends TargetGoal {
        private final EntityTamableMonster tameable;
        private LivingEntity attacker;
        private int timestamp;

        public OwnerHurtTargetGoal(EntityTamableMonster theEntityTameableIn) {
            super(theEntityTameableIn, false);
            this.tameable = theEntityTameableIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean shouldExecute() {
            if (this.tameable.isTamed() && !this.tameable.isSitting()) {
                LivingEntity livingentity = this.tameable.getOwner();
                if (livingentity == null) {
                    return false;
                } else {
                    this.attacker = livingentity.getLastAttackedEntity();
                    int i = livingentity.getLastAttackedEntityTime();
                    return i != this.timestamp && this.isSuitableTarget(this.attacker, EntityPredicate.DEFAULT) && this.tameable.shouldAttackEntity(this.attacker, livingentity);
                }
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            this.goalOwner.setAttackTarget(this.attacker);
            LivingEntity livingentity = this.tameable.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastAttackedEntityTime();
            }

            super.startExecuting();
        }
    }

}

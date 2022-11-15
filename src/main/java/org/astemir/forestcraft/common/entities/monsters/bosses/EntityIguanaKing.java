package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.client.AColor;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.network.BossMusicMessage;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.entities.animals.EntityKelpy;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.monsters.EntityIguana;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCEntityStats;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;


import javax.annotation.Nullable;
import java.util.function.Predicate;

public class EntityIguanaKing extends MonsterEntity implements IDamageResistable, IAnimated {

    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.GREEN).style(ABossbar.Style.SEGMENTED);

    private static final DamageResistanceMap resistanceMap = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.75f);

    private static final DataParameter<Integer> EARTH_ATTACK_TICKS = EntityDataManager.createKey(EntityIguanaKing.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> EARTH_ATTACK_RECHARGE = EntityDataManager.createKey(EntityIguanaKing.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> DEAD_ANIMATION = EntityDataManager.createKey(EntityIguanaKing.class, DataSerializers.BOOLEAN);
    private int attacksThrowed = 0;

    private AnimationFactory factory = new AnimationFactory(this,IDLE,RUN,HIT,HIT2,HIT_GROUND,ROAR){
        @Override
        public void onAnimationEnd(Animation animation) {
            if (HIT_GROUND.equals(animation)){
                hitGround();
            }
        }
    };

    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(21).conflict(1);

    public static final Animation RUN = new Animation(1,"run").
            loop().
            time(21).conflict(0);

    public static final Animation HIT = new Animation(2,"hit").
            time(21).conflict(4);


    public static final Animation HIT2 = new Animation(3,"hit2").
            time(21).conflict(4);

    public static final Animation HIT_GROUND = new Animation(4,"hit_ground").
            time(18).conflict(5);

    public static final Animation ROAR = new Animation(5,"roar").
            time(18).conflict(2,3,4).speed(2);


    public EntityIguanaKing(EntityType type, World worldIn) {
        super(type, worldIn);
        enablePersistence();
        experienceValue = 30;
        bossbar.boss(this);
        stepHeight = 1;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        FCEntityStats.IGUANA_KING.getStats(difficultyIn.getDifficulty()).apply(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(EARTH_ATTACK_TICKS,0);
        dataManager.register(EARTH_ATTACK_RECHARGE,190);
        dataManager.register(DEAD_ANIMATION,false);
    }

    public boolean isDeadAnimationPlaying(){
        return dataManager.get(DEAD_ANIMATION);
    }


    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, LivingEntity.class, 6.0F));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.7f,true));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, LivingEntity.class, 10, false, false, new Predicate<LivingEntity>() {
            @Override
            public boolean test(LivingEntity entity) {
                if (!(entity instanceof ArmorStandEntity) && !(entity instanceof EntityIguana)) {
                    return true;
                }else{
                    return false;
                }
            }
        }));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, false));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }


    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.show(player);
        }
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> player), new BossMusicMessage(getEntityId(),1));
    }

    @Override
    protected void onDeathUpdate() {
        ++this.deathTime;
        if (!isDeadAnimationPlaying()){
            dataManager.set(DEAD_ANIMATION,true);
        }
        if (deathTime == 20){
            playSound(FCSounds.IGUANA_KING_STEP.get(), 3f, RandomUtils.randomFloat(0.9f,1.1f));
            world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(8, 0, 8)).forEach((e) -> {
                if (!e.getUniqueID().equals(this.getUniqueID())) {
                    e.setMotion(0,0.5,0);
                    e.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 15, 10, false, false));
                }
            });
        }
        if (deathTime > 100){
            remove();
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), 1, 1, 1);
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(FCSounds.IGUANA_KING_STEP.get(), 0.75F, RandomUtils.randomFloat(0.9f, 1f));
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.hide(player);
        }
    }


    @Override
    public void tick() {
        super.tick();
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.update();
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (RandomUtils.doWithChance(25)){
            playSound(FCSounds.IGUANA_KING_HURT.get(),1,RandomUtils.randomFloat(0.9f,1.1f));
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.IGUANA_KING_HURT.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,275).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D).createMutableAttribute(Attributes.ARMOR,8).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,2).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1.5f);
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!factory.isPlaying(ROAR)) {
            if (RandomUtils.doWithChance(50)) {
                factory.playAnimation(HIT);
            } else {
                factory.playAnimation(HIT2);
            }
        }
        if (!isDeadAnimationPlaying()) {
            if (RandomUtils.doWithChance(15)) {
                playSound(FCSounds.IGUANA_KING_LAUGH.get(), 1, RandomUtils.randomFloat(0.9f, 1.1f));
            }
            return super.attackEntityAsMob(entityIn);
        }
        return false;
    }



    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if ((limbSwingAmount > 0.01 && limbSwing > 0.01)) {
            factory.playAnimation(RUN);
        } else {
            factory.playAnimation(IDLE);
        }
        if (!isDeadAnimationPlaying()) {
            if (getEarthAttackTicks() > 0) {
                setEarthAttackTicks(getEarthAttackTicks() - 1);
            }
            if (getEarthAttackRecharge() > 0) {
                if (getEarthAttackRecharge() == 200) {
                    world.getEntitiesWithinAABB(EntityType.PLAYER, getBoundingBox().grow(20, 10, 20), (e) -> true).forEach((entity) -> {
                        entity.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 15, 5, false, false));
                        entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 300, 0, true, true));
                    });
                    factory.playAnimation(ROAR);
                    for (int i = 0;i<2;i++){
                        BlockPos pos = WorldUtils.randomEmptyBlock(world,getPosition(),5);
                        EntityIguana iguana = FCEntities.IGUANA.create(world);
                        iguana.moveToBlockPosAndAngles(pos,rotationYaw,0);
                        if (getAttackTarget() != null) {
                            iguana.setAttackTarget(getAttackTarget());
                        }
                        world.addEntity(iguana);
                    }
                    playSound(FCSounds.IGUANA_KING_SCREAM.get(), 2, RandomUtils.randomFloat(0.9f, 1.1f));
                }
                setEarthAttackRecharge(getEarthAttackRecharge() - 1);
            } else {
                if (getAttackTarget() != null) {
                    if (getAttackTarget().getDistanceSq(this) < 20) {
                        factory.playAnimation(HIT_GROUND);
                    }
                }
            }
        }
    }

    private void hitGround(){
        attacksThrowed++;
        setEarthAttackTicks(20);
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.MOB_GRIEFING)) {
            BlockPos.getAllInBox(getBoundingBox().grow(2, 2, 2).offset(new Vector3d(0, -2, 0))).forEach((block) -> {
                if (!world.isAirBlock(block)) {
                    if (RandomUtils.doWithChance(30)) {
                        if (!world.isRemote) {
                            if (world.getBlockState(block).canEntityDestroy(world, block, this) && !world.getBlockState(block).getBlock().equals(Blocks.BEDROCK) && world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) == true && world.getFluidState(block).equals(Fluids.EMPTY.getDefaultState())) {
                                FallingBlockEntity blockEntity = new FallingBlockEntity(world, block.getX(), block.getY() + 1f, block.getZ(), world.getBlockState(block));
                                blockEntity.fallTime = 1;
                                blockEntity.shouldDropItem = false;
                                blockEntity.setMotion(RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(0.5f, 0.75f), RandomUtils.randomFloat(-0.5f, 0.5f));
                                world.addEntity(blockEntity);
                                world.destroyBlock(block, false);
                            }
                        }
                    }
                }
            });
        }
        world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(2, 0, 2)).forEach((e) -> {
            if (!e.getUniqueID().equals(this.getUniqueID()) && !(e instanceof EntityIguana)) {
                e.attackEntityFrom(DamageSource.causeMobDamage(this), 15);
            }
        });
        world.getEntitiesWithinAABB(EntityType.PLAYER, getBoundingBox().grow(20, 10, 20), (e) -> true).forEach((entity) -> {
            entity.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 30, 8, false, false));
        });
        playSound(FCSounds.IGUANA_KING_ATTACK.get(), 2, RandomUtils.randomFloat(0.9f, 1.1f));
        if (attacksThrowed < 4) {
            setEarthAttackRecharge(60);
        } else {
            attacksThrowed = 0;
            setEarthAttackRecharge(250);
        }
    }



    public void setEarthAttackTicks(int i){
        this.dataManager.set(EARTH_ATTACK_TICKS,i);
    }

    public int getEarthAttackTicks(){
        return dataManager.get(EARTH_ATTACK_TICKS);
    }

    public void setEarthAttackRecharge(int i){
        this.dataManager.set(EARTH_ATTACK_RECHARGE,i);
    }

    public int getEarthAttackRecharge(){
        return dataManager.get(EARTH_ATTACK_RECHARGE);
    }


    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return resistanceMap;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

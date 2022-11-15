package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.command.impl.TimeCommand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.client.AColor;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.common.entities.monsters.EntityIguana;
import org.astemir.forestcraft.common.entities.monsters.EntityPetrifiedRunestoneZombie;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCEntityStats;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.common.world.FCWorldData;

import javax.annotation.Nullable;

import static org.astemir.forestcraft.registries.FCItems.*;


public class EntityArchaicSentinel extends MonsterEntity implements IDamageResistable, IAnimated {



    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(21).conflict(2,5);

    public static final Animation HEAD_IDLE = new Animation(1,"head_idle").
            loop().
            time(42);

    public static final Animation RUN = new Animation(2,"run").
            loop().
            time(21).conflict(0,5);

    public static final Animation HIT = new Animation(3,"hit").
            time(27);

    public static final Animation HIT2 = new Animation(4,"hit2").
            time(27);

    public static final Animation TWIRLING = new Animation(5,"twirling").
            time(5).conflict(0,2,3,4).speed(2);

    private static final DamageResistanceMap RESISTANCE_MAP = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.5f).
            put(DamageResistanceMap.EXPLOSION,0.5f);


    public static final ItemDropTable ANCIENT_RUNESTONE_HERO_LOOT = new ItemDropTable().
            item(new ItemDropPool().
                    add(50.0, ItemDrop.fromItem(()-> PREHISTORIC_SHIELD.asItem())).
                    add(40.0, ItemDrop.fromItem(()->DAYBREAK.asItem())).
                    add(20.0, ItemDrop.fromItem(()->ANCESTOR.asItem())).
                    add(20.0, ItemDrop.fromItem(()->ARCHAXE.asItem())).
                    build());


    private static final DataParameter<Integer> SPIN_ATTACK_TICKS = EntityDataManager.createKey(EntityArchaicSentinel.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> EQUINOX_TICKS = EntityDataManager.createKey(EntityArchaicSentinel.class, DataSerializers.VARINT);

    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.GOLD).style(ABossbar.Style.SEGMENTED);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,HEAD_IDLE,RUN,HIT,HIT2,TWIRLING);

    private int ticksToSpin = 0;
    private int ticksToEquinox = 0;
    private int attackTimer;


    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        ANCIENT_RUNESTONE_HERO_LOOT.dropItems(world,getPosition(),attackingPlayer);
        super.dropLoot(damageSourceIn, attackedRecently);
    }



    public EntityArchaicSentinel(EntityType type, World worldIn) {
        super(type, worldIn);
        enablePersistence();
        experienceValue = 30;
        bossbar.boss(this);
        stepHeight = 1;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        FCEntityStats.ARCHAIC_SENTINEL.getStats(difficultyIn.getDifficulty()).apply(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(SPIN_ATTACK_TICKS,0);
        dataManager.register(EQUINOX_TICKS,0);
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }


    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, LivingEntity.class, 6.0F));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.7f,true));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class,false,false));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }


    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.show(player);
        }
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
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        FCWorldData data = FCWorldData.getData(world);
        if (data != null){
            if (!data.isRunestoneLordKilled()){
                world.getPlayers().forEach((player)->{
                    player.sendStatusMessage(new TranslationTextComponent("message.forestcraft.runestone_lord_killed"),false);
                });
                data.setRunestoneLordKilled(true);
            }
        }
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
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,300).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.45D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 60).createMutableAttribute(Attributes.ARMOR,8).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,1).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1f);
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (RandomUtils.doWithChance(50)) {
            factory.playAnimation(HIT);
        } else {
            factory.playAnimation(HIT2);
        }
        if (entityIn instanceof LivingEntity) {
            if (!WorldUtils.isNight(world)){
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS,60,1));
            }else{
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS,120,1));
            }
        }
        return super.attackEntityAsMob(entityIn);
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.85F;
    }


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        factory.playAnimation(HEAD_IDLE);
        if (getSpinTicks() <= 0) {
            if (limbSwing > 0.01 && limbSwingAmount > 0.01) {
                factory.playAnimation(RUN);
            } else {
                factory.playAnimation(IDLE);
            }
        }
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (!WorldUtils.isNight(world)){
            addPotionEffect(new EffectInstance(Effects.RESISTANCE,20,1));
        }else{
            addPotionEffect(new EffectInstance(Effects.SPEED,20,1));
        }
        if (getSpinTicks() > 0){
            factory.playAnimation(TWIRLING);
            setSpinTicks(getSpinTicks()-1);
            if (getAttackTarget() != null){
                setMotion(getMotion().add(new Vector3d(getPosX()-getAttackTarget().getPosX(),getPosY()-getAttackTarget().getPosY(),getPosZ()-getAttackTarget().getPosZ())).mul(-0.1,-0.1,-0.1).normalize());
            }
            if (getSpinTicks() % 2 == 0){
                playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,1,1.5f);
            }
            world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(1.5f,1.5f,1.5f), entity -> !(entity instanceof EntityArchaicSentinel) && !(entity instanceof EntityPetrifiedRunestoneZombie)).forEach((e)->{
                e.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                e.setMotion(e.getMotion().add(getMotion()));
            });
        }else{
            if (getAttackTarget() != null) {
                if (ticksToEquinox < 500) {
                    if (ticksToSpin < 200) {
                        ticksToSpin++;
                    } else {
                        setSpinTicks(64);
                        ticksToSpin = 0;
                    }
                    ticksToEquinox++;
                }else{
                    setEquinoxTicks(12);
                    if (!world.isRemote) {
                        ServerWorld serverWorld = (ServerWorld)world;
                        if (!WorldUtils.isNight(world)){
                            WorldUtils.setNight(serverWorld);
                        }else{
                            WorldUtils.setDay(serverWorld);
                        }
                        for (int i = 0;i<RandomUtils.randomInt(2,3);i++){
                            BlockPos pos = WorldUtils.randomEmptyBlock(world,getPosition(),4);
                            EntityPetrifiedRunestoneZombie zombie = FCEntities.PETRIFIED_ZOMBIE.create(world);
                            zombie.moveToBlockPosAndAngles(pos,rotationYaw,0);
                            if (getAttackTarget() != null) {
                                zombie.setAttackTarget(getAttackTarget());
                            }
                            world.addEntity(zombie);
                        }
                        playSound(FCSounds.ANCIENT_RUNESTONE_HERO_EQUINOX.get(),1,1);
                    }
                    ticksToEquinox = 0;
                }
            }
        }
    }

    public int getSpinTicks(){
        return dataManager.get(SPIN_ATTACK_TICKS);
    }

    public void setSpinTicks(int ticks){
        dataManager.set(SPIN_ATTACK_TICKS,ticks);
    }

    public int getEquinoxTicks(){
        return dataManager.get(EQUINOX_TICKS);
    }

    public void setEquinoxTicks(int ticks){
        dataManager.set(EQUINOX_TICKS,ticks);
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return RESISTANCE_MAP;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

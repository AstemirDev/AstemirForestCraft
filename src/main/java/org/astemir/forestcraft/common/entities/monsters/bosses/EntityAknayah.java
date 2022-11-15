package org.astemir.forestcraft.common.entities.monsters.bosses;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.api.client.AColor;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.loot.WeightedRandom;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.entities.ai.aknayah.AknayahMeleeAttackGoal;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCEntityStats;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;

import javax.annotation.Nullable;

import static org.astemir.forestcraft.common.entities.ai.aknayah.AknayahMessages.*;

public class EntityAknayah extends CreatureEntity implements IAnimated, IDamageResistable {

    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.AQUA).style(ABossbar.Style.FLAT);


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(1.04f);

    public static final Animation FACE_IDLE = new Animation(1,"face_idle_default").
            loop().
            time(1.04f);

    public static final Animation BLINK = new Animation(2,"blink").
            time(0.44f).conflict(3,4);

    public static final Animation HURT = new Animation(3,"hurt").
            time(0.52f).conflict(2,4,5);

    public static final Animation ANGRY_BROWS = new Animation(4,"angry_brows").
            time(1.04f).conflict(2,3,5);

    public static final Animation BROWS_IDLE = new Animation(5,"brows_idle_default").
            loop().
            time(1.04f).conflict(3,4);

    public static final Animation HAIR_IDLE = new Animation(6,"hair_idle").
            loop().
            time(1.04f);

    public static final Animation ATTACK_LEG = new Animation(7,"attack_leg").
            time(1.04f).conflict(8,9,13).speed(0.5f);

    public static final Animation ATTACK_HANDS = new Animation(8,"attack_hands").
            time(0.88f).conflict(7,9,13).speed(0.5f);;

    public static final Animation ATTACK_WEAPON = new Animation(9,"attack_weapon").
            time(0.84f).conflict(7,8,13).speed(0.5f);;

    public static final Animation SUMMON = new Animation(13,"summon").
            time(1.56f).conflict(7,8,9);

    public static final Animation DEATH = new Animation(10,"death").
            time(1.56f).conflict(0,7,8,9);

    public static final Animation JUMP = new Animation(11,"jump").
            time(1.04f).speed(0.5f);

    public static final Animation LAUGH = new Animation(12,"laugh").
            time(1.56f);

    public static final Animation INSANE_EYES = new Animation(13,"insane_eyes").
            loop().
            speed(0.1f).
            time(0.52f);

    public static final Animation WINGS = new Animation(14,"flap").
            loop().
            speed(2f).
            time(0.4f);

    private static WeightedRandom<Animation> RANDOM_ATTACK_ANIMATION = new WeightedRandom().
            add(60,ATTACK_LEG).
            add(40,ATTACK_HANDS).build();


    private final DamageResistanceMap resistanceMap = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.5f).
            put(DamageResistanceMap.EXPLOSION,1).
            put(DamageSource.FALL,1).
            put(DamageSource.DROWN,1).
            put(DamageSource.CRAMMING,1).
            put(DamageSource.LAVA,1).
            put(DamageSource.IN_FIRE,1).
            put(DamageSource.ON_FIRE,1);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,FACE_IDLE,BLINK,HURT,ANGRY_BROWS,BROWS_IDLE,HAIR_IDLE,ATTACK_LEG,ATTACK_HANDS,ATTACK_WEAPON,DEATH,JUMP,SUMMON,LAUGH,INSANE_EYES,WINGS){
    };

    private int projectileShieldTicks = 0;
    private int projectileShieldCooldown = 0;
    private float deathTicks = 0;




    public EntityAknayah(EntityType type, World worldIn) {
        super(type, worldIn);
        enablePersistence();
        bossbar.boss(this);
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
        bossbar.hide(player);
    }

    @Override
    public void tick() {
        super.tick();
        projectileShieldCooldown++;
        if (projectileShieldCooldown > 600){
            projectileShieldTicks = 200;
            projectileShieldCooldown = 0;
        }
        if (projectileShieldTicks > 0){
            projectileShieldTicks--;
            world.getEntitiesWithinAABB(ProjectileEntity.class, getBoundingBox().grow(4, 4, 4)).forEach((e) -> {
                e.setMotion(Vector3d.ZERO);
            });
        }
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.update();
        }
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        FCEntityStats.AKNAYAH.getStats(difficultyIn.getDifficulty()).apply(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new AknayahMeleeAttackGoal(this, 0.75D, true));
        this.goalSelector.addGoal(6, new SwimGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (!getShouldBeDead()) {
            if (ticksExisted % 10 == 0) {
                if (deathTicks <= 0) {
                    setHealth(getHealth() + 1);
                }
            }
            float chance = 0.05f;
            if (getHealth() / getMaxHealth() <= 0.5f) {
                chance = 0.5f;
                factory.playAnimation(INSANE_EYES);
                factory.playAnimation(WINGS);
                addPotionEffect(new EffectInstance(Effects.SPEED,80,2,false,false));
                if (this.world.isRemote) {
                    for(int i = 0; i < 10; ++i) {
                        this.world.addParticle(FCParticles.STARDUST.get(), true,this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.25D), (this.rand.nextDouble() - 0.25D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.25D) * 2.0D);
                    }
                }
            }
            if (getHealth() / getMaxHealth() <= 0.25f) {
                chance = 1;
                addPotionEffect(new EffectInstance(Effects.SPEED,80,4,false,false));
            }
            if (RandomUtils.doWithChance(chance) && (getHealth() / getMaxHealth()) <= 0.75f) {
                say(REGENERATION.random());
                addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, RandomUtils.randomInt(4, 10), false, false));
            }
        }
        if (RandomUtils.doWithChance(1) && !factory.isPlaying(BLINK)) {
            factory.playAnimation(BLINK);
        }
        if (!factory.isPlaying(DEATH)) {
            factory.playAnimation(IDLE);
        }
        if (!factory.isPlaying(ANGRY_BROWS) && !factory.isPlaying(HURT)) {
            factory.playAnimation(FACE_IDLE);
        }
        factory.playAnimation(HAIR_IDLE);
        if (!factory.isPlaying(ANGRY_BROWS) && !factory.isPlaying(HURT)) {
            factory.playAnimation(BROWS_IDLE);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!factory.isPlaying(ATTACK_LEG) && !factory.isPlaying(ATTACK_WEAPON) && !factory.isPlaying(ATTACK_HANDS)) {
            factory.playAnimation(RANDOM_ATTACK_ANIMATION.random());
            if (RandomUtils.doWithChance(25)) {
                if (entityIn instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) entityIn;
                    CreatureAttribute attribute = entity.getCreatureAttribute();
                    if (attribute == CreatureAttribute.UNDEAD){
                        say(ATTACK_UNDEAD.random());
                    }else
                    if (attribute == CreatureAttribute.ARTHROPOD){
                        say(ATTACK_INSECT.random());
                    }else{
                        say(ATTACK.random());
                    }
                    if (!(entityIn instanceof PlayerEntity)){
                        entityIn.attackEntityFrom(DamageSource.GENERIC, (float) getAttribute(Attributes.ATTACK_DAMAGE).getValue()*RandomUtils.randomInt(2,6));
                    }
                }
            }
            Vector3 dir = Vector3.from(EntityUtils.direction(this));
            dir.setY(1);
            EntityUtils.setMotion(entityIn, dir.mul(RandomUtils.randomFloat(1.5f,2.5f),RandomUtils.randomFloat(1,1.5f),RandomUtils.randomFloat(1.5f,2.5f)));
            Particle3D particle3D = new Particle3D(ParticleTypes.EXPLOSION).count(1).renderTimes(10).size(1,1,1);
            particle3D.play(world,entityIn.getPosX(),entityIn.getPosY(),entityIn.getPosZ());

            Particle3D particle3D2 = new Particle3D(FCParticles.COSMIC_EXPLOSION.get()).count(1).renderTimes(5).size(1,1,1);
            particle3D2.play(world,entityIn.getPosX(),entityIn.getPosY(),entityIn.getPosZ());

            playSound(FCSounds.EXPLOSION_LARGE.get(),0.5f,RandomUtils.randomFloat(1.5f,2f));
            shakeScreen(20,10);
        }
        return super.attackEntityAsMob(entityIn);
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (amount > 1) {
            if (!factory.isPlaying(HURT) && !factory.isPlaying(ANGRY_BROWS)) {
                if (RandomUtils.doWithChance(10)) {
                    if (source.getTrueSource() != null) {
                        if (amount >= 1000) {
                            say(RANDOM_IMPOSSIBLE_ATTACK_PHRASE.random());
                        } else if (amount >= 50) {
                            say(RANDOM_STRONG_ATTACK_PHRASE.random());
                        } else if (amount > 5) {
                            say(RANDOM_NORMAL_ATTACK_PHRASE.random());
                            if (RandomUtils.doWithChance(25)) {
                                factory.playAnimation(LAUGH);
                            }
                        } else if (amount <= 5) {
                            say(RANDOM_WEAK_ATTACK_PHRASE.random());
                            if (RandomUtils.doWithChance(25)) {
                                factory.playAnimation(LAUGH);
                            }
                        }
                    }
                }
                if (RandomUtils.doWithChance(20)) {
                    if (RandomUtils.doWithChance(50)) {
                        factory.playAnimation(HURT);
                    } else {
                        factory.playAnimation(ANGRY_BROWS);
                    }
                }
            }
            return super.attackEntityFrom(source, amount);
        }else{
            return false;
        }
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        if (effectInstanceIn.getPotion().equals(Effects.INSTANT_HEALTH) || effectInstanceIn.getPotion().equals(Effects.SPEED)){
            return super.addPotionEffect(effectInstanceIn);
        }
        return false;
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,3000).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 30.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.ARMOR,20).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1);
    }

    public void say(String message){
        world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(50, 50, 50)).forEach((e) -> {
            if (!world.isRemote) {
                e.sendMessage(new StringTextComponent("§5<Aknayah> §c").append(new TranslationTextComponent(message)), Util.DUMMY_UUID);
            }
        });
    }


    public void shakeScreen(int radius,int power){
        world.getEntitiesWithinAABB(EntityType.PLAYER, getBoundingBox().grow(radius, radius, radius), (e) -> true).forEach((entity) -> {
            entity.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 15, power, false, false));
        });
    }

    @Override
    protected void onDeathUpdate() {
        if (deathTicks == 0){
            factory.playAnimation(DEATH);
        }
        if (deathTicks == 20){
            for (int i = 0; i<DEATH_DIALOGUE.length; i++) {
                String phrase = DEATH_DIALOGUE[i];
                ForestCraft.TASK_MANAGER.createTask(()->say(phrase),i*40);
            }
        }
        if (deathTicks > 200){
            remove();
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), 1, 1, 1);
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
        ++this.deathTicks;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return resistanceMap;
    }



}

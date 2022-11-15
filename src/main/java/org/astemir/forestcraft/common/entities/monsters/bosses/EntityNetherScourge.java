package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.client.AColor;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCEntityStats;
import org.astemir.forestcraft.registries.FCSounds;

import javax.annotation.Nullable;

import static org.astemir.forestcraft.registries.FCItems.*;


public class EntityNetherScourge extends MonsterEntity implements IDamageResistable, IAnimated {




    public static final ItemDropTable NETHER_SCOURGE_LOOT = new ItemDropTable().
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),48,64)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),24,48)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),32,48)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),16,32)).
            item(new ItemDropPool().
                    add(50.0, ItemDrop.fromItem(()->FLESH_EATER.asItem())).
                    add(40.0, ItemDrop.fromItem(()->DEMON_BUSTER.asItem())).
                    add(30.0, ItemDrop.fromItem(()->POSSESED_SKULL.asItem())).
                    build());

    public static final ItemDropTable NETHER_SCOURGE_ENRAGED_LOOT = new ItemDropTable().
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),48,64)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),48,64)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),48,64)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),24,48)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),32,48)).
            item(ItemDrop.fromItem(()-> FCBlocks.ROTTEN_FLESH_BLOCK.asItem(),16,32)).
            item(ItemDrop.fromItem(()-> ETERNAL_HUNGER_CHESTPLATE.asItem())).
            item(ItemDrop.fromItem(()->POSSESED_SKULL.asItem())).
            item(new ItemDropPool().
                    add(50.0, ItemDrop.fromItem(()->FLESH_EATER.asItem())).
                    add(40.0, ItemDrop.fromItem(()->DEMON_BUSTER.asItem())).
                    build());

    private static final DamageResistanceMap RESISTANCE_MAP = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.3f).
            put(DamageResistanceMap.EXPLOSION,1).
            put(DamageSource.IN_FIRE,1).
            put(DamageSource.ON_FIRE,1).
            put(WitherSkullEntity.class,1);

    public static final Animation IDLE = new Animation(0,"idle").loop().time(1.04f).conflict(2);
    public static final Animation FAKE_HEADS_IDLE = new Animation(1,"fake_heads_idle").loop().time(1.04f);
    public static final Animation RUN = new Animation(2,"run").loop().time(1.04f).conflict(0);
    public static final Animation SCREAM = new Animation(3,"scream").time(1.56f);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,FAKE_HEADS_IDLE,RUN,SCREAM);

    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.RED).style(ABossbar.Style.FLAT);


    public EntityNetherScourge(EntityType type, World worldIn) {
        super(type, worldIn);
        enablePersistence();
        experienceValue = 3000;
        bossbar.boss(this);
        stepHeight = 1;
    }


    private int fallTicks = 0;



    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        if (fallDistance >= 4) {
            BlockPos middle = getPosition().down();
            fallTicks = 20;
            boolean mobGriefing = ConfigUtils.isEnabledInConfig(FCConfigurationValues.MOB_GRIEFING);
            if (mobGriefing) {
                for (float i = 0; i < 360; i += 10) {
                    if (fallTicks == 20) {
                        BlockPos around = middle.add(Math.cos(i) * 3, 0, Math.sin(i) * 3);
                        createBlock(around);
                    }
                    if (fallTicks == 15) {
                        BlockPos around = middle.add(Math.cos(i) * 4, 0, Math.sin(i) * 4);
                        createBlock(around);
                    }
                    if (fallTicks == 10) {
                        BlockPos around = middle.add(Math.cos(i) * 5, 0, Math.sin(i) * 5);
                        createBlock(around);
                    }
                    if (fallTicks == 5) {
                        BlockPos around = middle.add(Math.cos(i) * 6, 0, Math.sin(i) * 6);
                        createBlock(around);
                    }
                }
            }
            playSound(SoundEvents.ENTITY_WITHER_BREAK_BLOCK, 1, 2);
            playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 2);
            shakeScreen(20,5);
            int power = (int)distance/5;
            if (power > 5){
                power = !isEnraged() ? 5 : 10;
            }
            boolean causeFire = mobGriefing;
            world.createExplosion(this,middle.getX(),middle.getY(),middle.getZ(),power,causeFire, Explosion.Mode.NONE);
        }
        return false;
    }

    @Override
    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        ItemDropTable table = NETHER_SCOURGE_LOOT;
        if (isEnraged()){
            table = NETHER_SCOURGE_ENRAGED_LOOT;
        }
        table.dropItems(world,getPosition(),attackingPlayer);
        super.dropLoot(damageSourceIn, attackedRecently);
    }

    private void launchWitherSkullToCoords(double x, double y, double z) {
        Vector3d dir = EntityUtils.direction(this);
        if (!this.isSilent()) {
            this.world.playEvent((PlayerEntity)null, 1024, this.getPosition(), 0);
        }
        double d0 = (dir.x*2)+this.getPosXRandom(1);
        double d1 = (dir.y*2)+this.getPosYRandom();
        double d2 = (dir.z*2)+this.getPosZRandom(1);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
        WitherSkullEntity witherskullentity = new WitherSkullEntity(this.world, this, d3, d4, d5){
            @Override
            protected void onImpact(RayTraceResult result) {
                RayTraceResult.Type raytraceresult$type = result.getType();
                if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
                    this.onEntityHit((EntityRayTraceResult)result);
                } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
                    this.func_230299_a_((BlockRayTraceResult)result);
                }
                if (!this.world.isRemote) {
                    this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, false, Explosion.Mode.NONE);
                    this.remove();
                }
            }

            @Override
            public void tick() {
                super.tick();
                if (ticksExisted > 100){
                    remove();
                }
            }

            @Override
            public boolean isBurning() {
                return true;
            }


            @Override
            protected float getMotionFactor() {
                return isEnraged() ? 1.25f : 0.8f;
            }
        };
        witherskullentity.setShooter(this);
        witherskullentity.setRawPosition(d0, d1, d2);
        this.world.addEntity(witherskullentity);
    }


    public void shakeScreen(int radius,int power){
        world.getEntitiesWithinAABB(EntityType.PLAYER, getBoundingBox().grow(radius, radius, radius), (e) -> true).forEach((entity) -> {
            entity.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 15, power, false, false));
        });
    }

    private void createBlock(BlockPos block){
        FallingBlockEntity blockEntity = new FallingBlockEntity(world, block.getX(), block.getY() + 1f, block.getZ(), world.getBlockState(block));
        blockEntity.fallTime = 1;
        blockEntity.shouldDropItem = false;
        blockEntity.setMotion(0, 0.5f, 0);
        world.addEntity(blockEntity);
        world.destroyBlock(block, false);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (isEnraged()) {
            FCEntityStats.NETHER_SCOURGE_ENRAGED.getStats(difficultyIn.getDifficulty()).apply(this);
        }else{
            FCEntityStats.NETHER_SCOURGE.getStats(difficultyIn.getDifficulty()).apply(this);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    @Override
    public boolean isNonBoss() {
        return false;
    }

    @Override
    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        if (effectInstanceIn.getPotion() == Effects.WITHER || effectInstanceIn.getPotion() == Effects.INSTANT_DAMAGE) {
            return false;
        }
        return super.addPotionEffect(effectInstanceIn);
    }

    public boolean isEnraged(){
        return world.getDimensionKey() != World.THE_NETHER;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, LivingEntity.class, 6.0F));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.5f,true));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class,false,false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, VillagerEntity.class,false,false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolemEntity.class,false,false));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }


    @Override
    protected float getJumpFactor() {
        return !isEnraged() ? 3 : 5;
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
    protected SoundEvent getAmbientSound() {
        return FCSounds.NETHER_SCOURGE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.NETHER_SCOURGE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.NETHER_SCOURGE_DEATH.get();
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.75F, RandomUtils.randomFloat(0.5f, 0.6f));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,450).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D).createMutableAttribute(Attributes.ARMOR,8).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,1).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1f);
    }



    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WITHER,120,1));
        }
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected float getSoundPitch() {
        return !isEnraged() ? super.getSoundPitch() :  super.getSoundPitch()*0.75f;
    }

    @Override
    protected void onDeathUpdate() {
        super.onDeathUpdate();
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (fallTicks > 0){
            fallTicks--;
        }
        if (getAttackTarget() != null){
            int skullTicks = !isEnraged() ? 100 : 30;
            int jumpTicks = !isEnraged() ? 200 : 80;
            int screamTicks = !isEnraged() ? 300 : 150;
            if (rand.nextInt(skullTicks) == 0){
                for (int i = 0;i<RandomUtils.randomInt(8,12);i++) {
                    launchWitherSkullToCoords(getAttackTarget().getPosX(), getAttackTarget().getPosY(), getAttackTarget().getPosZ());
                }
            }
            if (rand.nextInt(jumpTicks) == 0 && !isJumping){
                jump();
                shakeScreen(20,2);
            }
            if (rand.nextInt(screamTicks) == 0){
                addPotionEffect(new EffectInstance(Effects.SPEED,200,3));
                factory.playAnimation(SCREAM);
                playSound(FCSounds.NETHER_SCOURGE_SCREAM.get(),2,RandomUtils.randomFloat(0.9f,1.1f));
            }
        }
        factory.playAnimation(FAKE_HEADS_IDLE);
        if ((limbSwingAmount > 0.01 && limbSwing > 0.01)) {
            factory.playAnimation(RUN);
        } else {
            if (!factory.isPlaying(SCREAM)) {
                factory.playAnimation(IDLE);
            }
        }
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

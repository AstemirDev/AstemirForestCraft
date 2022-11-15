package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.TaskManager;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.client.AColor;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.EntityDifficultyMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.forestcraft.common.entities.ai.TargetGoal3D;
import org.astemir.forestcraft.registries.*;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCosmicFire;
import org.astemir.api.math.RandomUtils;

import javax.annotation.Nullable;
import javax.security.sasl.SaslServer;
import java.util.EnumSet;
import static org.astemir.forestcraft.registries.FCItems.*;
import static org.astemir.forestcraft.registries.FCItems.COSMIC_TENTACLES;


public class EntityCosmicFiend extends MonsterEntity implements IDamageResistable, IAnimated {

    private static final DataParameter<Boolean> DASHING = EntityDataManager.createKey(EntityCosmicFiend.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SECOND_PHASE = EntityDataManager.createKey(EntityCosmicFiend.class, DataSerializers.BOOLEAN);


    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.PURPLE).style(ABossbar.Style.FLAT);

    private final DamageResistanceMap resistanceMap = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.5f).
            put(DamageResistanceMap.EXPLOSION,1).
            put(DamageSource.DROWN,1).
            put(DamageSource.CRAMMING,1).
            put(DamageSource.LAVA,1).
            put(DamageSource.IN_FIRE,1).
            put(DamageSource.ON_FIRE,1);

    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(10).
            speed(2);
    public static final Animation ATTACK = new Animation(1,"attack").
            time(10).conflict(0);
    public static final Animation ATTACK_LOOP = new Animation(2,"attack_loop").
            time(10).conflict(0,1).loop();
    public static final Animation DEATH = new Animation(3,"death").
            time(17).conflict(0,1,2).speed(4);


    public static final ItemDropTable COSMIC_FIEND_LOOT = new ItemDropTable().
            item(ItemDrop.fromItem(()-> SPACE_DEVASTATION)).
            item(ItemDrop.fromItem(()-> DESTROYER_OF_MOONS)).
            item(ItemDrop.fromItem(()-> BLACKHOLE_BULLET,48,64)).
            item(ItemDrop.fromItem(()-> BLACKHOLE_BULLET,48,64)).
            item(ItemDrop.fromItem(()-> BLACKHOLE_BULLET,48,64)).
            item(ItemDrop.fromItem(()-> BLACKHOLE_BULLET,48,64)).
            item(25.0f, ItemDrop.fromItem(()-> DARK_MATTER.asItem())).
            item(25.0f, ItemDrop.fromItem(()-> GALAXIA.asItem())).
            item(25.0f, ItemDrop.fromItem(()->COSMIC_TENTACLES.asItem())).
            item(1f, ItemDrop.fromItem(()->MUSIC_DISC_WE_TOOK_EACHOTHERS_HAND.asItem()));

    private int dashingTicks = 0;

    private AnimationFactory factory = new AnimationFactory(this,IDLE,ATTACK,ATTACK_LOOP,DEATH){

        @Override
        public void onAnimationTick(Animation animation, float tick) {
            if (DEATH.equals(animation)) {
                if (((int)tick) == 1){
                    for (int i = 0;i<5;i++) {
                        AstemirAPI.TASK_MANAGER.createTask(() -> {
                            Particle3D particle3D = new Particle3D(FCParticles.COSMIC_EXPLOSION.get()).count(40).speed(0.1f, 0.1f, 0.1f).renderTimes(20).size(1, 1, 1).distant();
                            particle3D.play(world, getPosXRandom(1), getPosYRandom(), getPosZRandom(1));
                            SoundUtils.playSound(world,FCSounds.EXPLOSION_LARGE.get(), SoundCategory.AMBIENT,getPosition(),5,1);
                        }, 5*i);
                        world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(20f,20f,20f), entity -> !(entity.getUniqueID().equals(getUniqueID()))).forEach((e)->{
                            if (e instanceof PlayerEntity){
                                e.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 40, 10, false, false));
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isDashing() && ATTACK.equals(animation)){
                factory.playAnimation(ATTACK_LOOP);
            }
            if (DEATH.equals(animation)){
                COSMIC_FIEND_LOOT.dropItems(world,getPosition(),attackingPlayer);
                world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(20f,20f,20f), entity -> !(entity.getUniqueID().equals(getUniqueID()))).forEach((e)->{
                    if (e instanceof PlayerEntity){
                        e.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 40, 20, false, false));
                        e.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 10, 50, false, false));
                    }
                });
                for (int i = 0;i<40;i++) {
                    Particle3D particle3D = new Particle3D(FCParticles.COSMIC_EXPLOSION.get()).count(40).speed(0.1f, 0.1f, 0.1f).renderTimes(50).size(1, 1, 1).distant();
                    particle3D.play(world, getPosXRandom(1f), getPosYRandom(), getPosZRandom(1f));
                }
                SoundUtils.playSound(world,FCSounds.EXPLOSION_GIANT.get(), SoundCategory.AMBIENT,getPosition(),5,1);
                remove();
            }
        }
    };

    public EntityCosmicFiend(EntityType type, World worldIn) {
        super(type, worldIn);
        enablePersistence();
        experienceValue = 20000;
        setDashing(false);
        bossbar.boss(this);
    }


    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        FCEntityStats.COSMIC_FIEND.getStats(difficultyIn.getDifficulty()).apply(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(DASHING,false);
        dataManager.register(SECOND_PHASE,false);
    }


    public boolean isDashing(){
        return dataManager.get(DASHING);
    }

    public boolean isInSecondPhase(){
        return dataManager.get(SECOND_PHASE);
    }

    public void setDashing(boolean dash){

        this.dataManager.set(DASHING,dash);
        if (dash){
            dashingTicks = 0;
            factory.playAnimation(ATTACK);
            setNoGravity(true);
        }else{
            dashingTicks = 0;
            setNoGravity(false);
            factory.stopAnimation(ATTACK_LOOP);
        }
    }


    private double getFallingSpeed(){
        if (!isDashing()) {
            return 0.6D;
        }
        return 1;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        Vector3d vector3d = this.getMotion();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setMotion(vector3d.mul(1.0D, getFallingSpeed(), 1.0D));
        }
        if (this.world.isRemote) {
            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.REVERSE_PORTAL, true,this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
                this.world.addParticle(FCParticles.STARDUST.get(), true,this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.25D), (this.rand.nextDouble() - 0.25D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.25D) * 2.0D);
            }
        }
        if (factory.isPlaying(ATTACK_LOOP) || factory.isPlaying(ATTACK)){
            dashingTicks++;
            if (dashingTicks >= 200){
                factory.stopAnimation(ATTACK_LOOP);
                factory.stopAnimation(ATTACK);
            }
        }
        if (!factory.isPlaying(ATTACK) && !factory.isPlaying(ATTACK_LOOP) && !factory.isPlaying(DEATH)) {
            factory.playAnimation(IDLE);
        }
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new TargetGoal3D<>(this,PlayerEntity.class,false));
        this.targetSelector.addGoal(3, new TargetGoal3D<>(this,LivingEntity.class,false));
        this.goalSelector.addGoal(7,new EntityCosmicFiend.DashAttackGoal(this));
        this.goalSelector.addGoal(7,new EntityCosmicFiend.LookAroundGoal(this));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        dataManager.set(SECOND_PHASE,compound.getBoolean("SecondPhase"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("SecondPhase",isInSecondPhase());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!isInSecondPhase()) {
            if (getHealth() <= (getMaxHealth() / 2)) {
                dataManager.set(SECOND_PHASE, true);
            }
        }
        return super.attackEntityFrom(source, amount);
    }


    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            if (CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG, player) != null) {
                CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG, player).setFog(true);
                CapabilityUtils.sendCapabilityChangedPacket(FCCapabilities.COSMO_FOG, player);
            }
            bossbar.show(player);
        }
    }


    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        if (CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG,player) != null) {
            CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG, player).setFog(false);
            CapabilityUtils.sendCapabilityChangedPacket(FCCapabilities.COSMO_FOG, player);
        }
        bossbar.hide(player);
    }

    @Override
    public void tick() {
        super.tick();
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.update();
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.COSMIC_FIEND_AMBIENT.get();
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.COSMIC_FIEND_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.COSMIC_FIEND_HURT.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,800).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 12.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 300).createMutableAttribute(Attributes.ARMOR,10).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,4).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1.5f);
    }


    @Override
    protected void onDeathUpdate() {
        factory.playAnimation(DEATH);
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    public int getAttackTime(){
        if (isInSecondPhase()){
            return 10;
        }else{
            return 15;
        }
    }

    public int getAttackCooldown(){
        if (getAttackTarget() != null) {
            boolean seeEntity = canEntityBeSeen(getAttackTarget());
            return 1;
        }
        if (isInSecondPhase()){
            return 5;
        }else{
            return 10;
        }
    }

    public int getFireballsCount(){
        if (isInSecondPhase()){
            return 10;
        }else{
            return 20;
        }
    }



    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return resistanceMap;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }


    static class DashAttackGoal extends Goal {

        private final EntityCosmicFiend parentEntity;
        public int attackTimer;

        public DashAttackGoal(EntityCosmicFiend ghast) {
            this.parentEntity = ghast;
        }

        @Override
        public boolean shouldExecute() {
            return this.parentEntity.getAttackTarget() != null;
        }

        @Override
        public void startExecuting() {
            this.attackTimer = 0;
        }


        @Override
        public void resetTask() {
            this.parentEntity.setDashing(false);
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.parentEntity.getAttackTarget();
            if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D) {
                World world = this.parentEntity.world;
                ++this.attackTimer;
                Vector3d rot;
                if (this.attackTimer == parentEntity.getAttackTime()){
                    this.parentEntity.setDashing(true);
                    parentEntity.playSound(FCSounds.COSMIC_FIEND_ATTACK.get(),2,RandomUtils.randomFloat(1.5f,1.6f));
                    rot = livingentity.getPositionVec().add(0,2f,0).add(parentEntity.getPositionVec().mul(-1,-1,-1)).normalize();
                    parentEntity.setMotion(rot.mul(5,3,5));
                    world.getEntitiesWithinAABB(LivingEntity.class, parentEntity.getBoundingBox().grow(20f,20f,20f), entity -> !(entity.getUniqueID().equals(parentEntity.getUniqueID()))).forEach((e)->{
                        if (e instanceof PlayerEntity){
                            e.addPotionEffect(new EffectInstance(FCEffects.FEAR.get(), 40, 8, false, false));
                        }
                    });
                }
                if (this.attackTimer > parentEntity.getAttackTime()){
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(parentEntity.world, parentEntity)) {
                        BlockPos.getAllInBox(parentEntity.getBoundingBox().grow(1.25f,1.25f,1.25f)).forEach((blockPos)->{
                            BlockState blockstate = parentEntity.world.getBlockState(blockPos);
                            if (!blockstate.isIn(Blocks.WATER) && !blockstate.isIn(Blocks.LAVA)) {
                                if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.MOB_GRIEFING)) {
                                    if (blockstate.canEntityDestroy(parentEntity.world, blockPos, parentEntity) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(parentEntity, blockPos, blockstate)) {
                                        if (RandomUtils.doWithChance(75)) {
                                            if (RandomUtils.doWithChance(10)) {
                                                parentEntity.world.destroyBlock(blockPos, false, parentEntity);
                                            } else {
                                                parentEntity.world.removeBlock(blockPos, false);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                    world.getEntitiesWithinAABB(Entity.class, parentEntity.getBoundingBox().grow(1.25f,1.25f,1.25f), entity -> !(entity.getUniqueID().equals(parentEntity.getUniqueID()))).forEach((e)->{
                        parentEntity.attackEntityAsMob(e);
                    });
                }
                if (this.attackTimer >= parentEntity.getAttackTime()*2) {
                    Vector3d vector3d = this.parentEntity.getLook(1.0F);
                    double d2 = livingentity.getPosX() - (this.parentEntity.getPosX() + vector3d.x * 4.0D);
                    double d3 = livingentity.getPosYHeight(0.5D) - (0.5D + this.parentEntity.getPosYHeight(0.5D));
                    double d4 = livingentity.getPosZ() - (this.parentEntity.getPosZ() + vector3d.z * 4.0D);
                    for (int i = 0;i<parentEntity.getFireballsCount();i++) {
                        EntityCosmicFire fireballentity = new EntityCosmicFire(world, this.parentEntity, d2, d3, d4);
                        fireballentity.setPosition(this.parentEntity.getPosXRandom(1f) + vector3d.x * 4.0D, this.parentEntity.getPosYHeight(0.5f) + 0.5D+ RandomUtils.randomFloat(-0.25f,0.25f), fireballentity.getPosZRandom(1) + vector3d.z * 4.0D);
                        world.addEntity(fireballentity);
                    }
                    this.attackTimer = -parentEntity.getAttackCooldown();
                    this.parentEntity.setDashing(false);
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }

        }
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    static class LookAroundGoal extends Goal {
        private final EntityCosmicFiend parentEntity;

        public LookAroundGoal(EntityCosmicFiend ghast) {
            this.parentEntity = ghast;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }


        @Override
        public boolean shouldExecute() {
            return true;
        }


        @Override
        public void tick() {
            if (this.parentEntity.getAttackTarget() == null) {
                Vector3d vector3d = this.parentEntity.getMotion();
                this.parentEntity.rotationYaw = -((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            } else {
                LivingEntity livingentity = this.parentEntity.getAttackTarget();
                if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getPosX() - this.parentEntity.getPosX();
                    double d2 = livingentity.getPosZ() - this.parentEntity.getPosZ();
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1,d2) * (180F / (float)Math.PI));
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }

        }
    }
}

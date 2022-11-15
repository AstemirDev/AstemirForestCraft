package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.client.animator.Animation;
import org.astemir.forestcraft.common.effect.ElectrocutEffect;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;


@OnlyIn(
        value = Dist.CLIENT,
        _interface = IChargeableMob.class
)
public class EntityThunderScreamer extends MonsterEntity implements IChargeableMob, IAnimated {


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(20).
            speed(2);
    public static final Animation DEATH = new Animation(1,"death").
            time(9).conflict(0);
    public static final Animation FALL = new Animation(2,"fall").
            time(4).conflict(0,1).loop();
    private int deathTicks = 0;

    private AnimationFactory factory = new AnimationFactory(this,IDLE,DEATH,FALL){
        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation.equals(DEATH)){
                factory.playAnimation(FALL);
            }
        }
    };


    public EntityThunderScreamer(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 20;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,100).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.FLYING_SPEED,1).createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D);
    }



    @Override
    protected void registerGoals() {
        this.moveController = new FlyingMovementController(this,1,true);
        goalSelector.addGoal(1, new ThunderScreamerFlyGoal(this,0.2f));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        goalSelector.addGoal(3, new EntityThunderScreamer.LookRandomlyGoal(this));
    }



    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() instanceof LivingEntity && !world.isRemote){
            ((ElectrocutEffect) FCEffects.ELECTROCUT.get()).addToEntity((LivingEntity)source.getTrueSource(),60,0);
        }
        if (source.isFireDamage()){
            return false;
        }
        if (source.equals(DamageSource.LIGHTNING_BOLT)) {
            addPotionEffect(new EffectInstance(Effects.REGENERATION,60,1,false,false));
            return false;
        }else{
            return super.attackEntityFrom(source, amount);
        }
    }




    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (!factory.isPlaying(DEATH) && !factory.isPlaying(FALL)) {
            factory.playAnimation(IDLE);
        }
        for (int i = 0;i<5;i++){
            world.addParticle(FCParticles.ELECTRO.get(),getPosXRandom(1),getPosYRandom(),getPosZRandom(1),0,0,0);
        }
        if (ticksExisted % 200 == 0){
            world.getEntitiesWithinAABB(EntityType.PLAYER, this.getBoundingBox().grow(100.0D, 100.0D, 100.0D), new Predicate<PlayerEntity>() {
                @Override
                public boolean test(PlayerEntity entity) {
                    return true;
                }
            }).forEach((player) -> {
                player.playSound(FCSounds.THUNDER_SCREAMER_DISTANT.get(), RandomUtils.randomFloat(0.8f, 1f), RandomUtils.randomFloat(0.9f, 1.1f));
            });
        }
        if (ticksExisted % 60 == 0){
            rotationYaw+=RandomUtils.randomInt(-90,90);
        }
        if (ticksExisted % 10 == 0){
            this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 2f, RandomUtils.randomFloat(0.6f,0.7f), false);
        }
        if (ticksExisted % ((getAttackTarget() == null) ? 200 : 50) == 0){
            int randomX = RandomUtils.randomInt(-10,10);
            int randomZ = RandomUtils.randomInt(-10,10);

            if (randomX < 5 && randomX > 0){
                randomX+=5;
            }else
            if (randomX > -5 && randomX < 0){
                randomX-=5;
            }

            if (randomZ < 5 && randomZ > 0){
                randomZ+=5;
            }
            if (randomZ > -5 && randomZ < 0){
                randomZ-=5;
            }


            BlockPos random = new BlockPos(getPosX()+randomX,getGroundLevel(),getPosZ()+randomZ);
            if (getAttackTarget() != null) {
                random = getAttackTarget().getPosition().add(RandomUtils.randomFloat(-2,2),0,RandomUtils.randomFloat(-2,2));
            }
            if (!world.isRemote) {
                LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(world);
                lightningBolt.moveToBlockPosAndAngles(random, 0, 0);
                world.addEntity(lightningBolt);
            }
        }
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        if (worldIn.getWorldInfo().isRaining() && worldIn.getWorldInfo().isThundering()) {
            boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
            return res;
        }else{
            return false;
        }
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if (world.isRaining() && world.isThundering()) {
            return world.getDimensionKey() == World.OVERWORLD && getPosition().getY() > world.getSeaLevel();
        }else{
            return false;
        }
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.THUNDER_SCREAMER_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();
    }



    @Override
    protected void onDeathUpdate() {
        for (int i = 0;i<10;i++){
            world.addParticle(FCParticles.ELECTRO.get(),getPosXRandom(1),getPosYRandom(),getPosZRandom(1),0,0,0);
        }
        if (deathTicks < 200){
            deathTicks++;
        }
        if (isOnGround() || deathTicks >= 200){
            remove();
            playSound(SoundEvents.ENTITY_GENERIC_EXPLODE,6,0.5f);
            createElectroCloud(world,getPosition());
            createElectroCloud(world,getPosition().add(0,0.25f,0));
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }else{
            setMotion(0,-0.5f,0);
        }
    }

    public void createElectroCloud(World worldIn,BlockPos pos){
        if (!world.isRemote) {
            AreaEffectCloudEntity cloud = EntityType.AREA_EFFECT_CLOUD.create(worldIn);
            cloud.setRadius(6f);
            cloud.setParticleData(FCParticles.ELECTRO.get());
            cloud.setDuration(800);
            cloud.setWaitTime(0);
            cloud.setRadiusOnUse(-0.001f);
            cloud.setRadiusPerTick(-0.001f);
            cloud.addEffect(new EffectInstance(FCEffects.ELECTROCUT.get(), 20, 2, false, false));
            cloud.moveToBlockPosAndAngles(pos.add(0, 0.1, 0), 0, 0);
            worldIn.addEntity(cloud);
        }
    }

    public int getGroundLevel(){
        for (int i = 0;i>100;i++){
            BlockPos pos = new BlockPos(getPosX(),getPosY()-i,getPosZ());
            if (world.getBlockState(pos).isSolid()){
                return pos.getY();
            }
        }
        return 0;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        factory.playAnimation(DEATH);
        playSound(FCSounds.THUNDER_SCREAMER_DEATH.get(),5,RandomUtils.randomFloat(1,1.1f));
    }


    @Override
    public boolean isCharged() {
        return true;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }


    public class ThunderScreamerFlyGoal extends Goal{

        private final EntityThunderScreamer bird;
        private double speed = 1;

        public ThunderScreamerFlyGoal(EntityThunderScreamer bird,double speed){
            this.bird = bird;
            this.speed = speed;
        }

        @Override
        public void tick() {
            Vector3d move = EntityUtils.direction(bird);
            if (!world.getBlockState(bird.getPosition().add(0,-1,0)).isAir()) {
                bird.rotationPitch = (float)-Math.toRadians(90);
            }
            bird.setMotion(move.getX() * speed, -move.getY() * speed * 2, move.getZ() * speed);
        }

        @Override
        public boolean shouldContinueExecuting() {
            return true;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }
    }

    public class LookRandomlyGoal extends Goal {
        private final MobEntity idleEntity;
        private double lookX;
        private double lookY;
        private double lookZ;
        private int idleTime;

        public LookRandomlyGoal(MobEntity entitylivingIn) {
            this.idleEntity = entitylivingIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return this.idleEntity.getRNG().nextFloat() < 0.02F;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return this.idleTime >= 0;
        }

        @Override
        public void startExecuting() {
            double d0 = (Math.PI * 2D) * this.idleEntity.getRNG().nextDouble();
            this.lookX = Math.cos(d0);
            this.lookY = Math.sin(d0);
            this.lookZ = Math.sin(d0);
            this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
        }

        @Override
        public void tick() {
            --this.idleTime;
            this.idleEntity.getLookController().setLookPosition(this.idleEntity.getPosX() + this.lookX, this.idleEntity.getPosYEye()+this.lookY, this.idleEntity.getPosZ() + this.lookZ);
        }
    }

}

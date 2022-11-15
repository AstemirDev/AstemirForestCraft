package org.astemir.forestcraft.common.entities.monsters;


import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.api.loot.WeightedRandom;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Random;

import static net.minecraft.item.Items.PHANTOM_MEMBRANE;
import static net.minecraft.item.Items.ROTTEN_FLESH;
import static org.astemir.forestcraft.registries.FCItems.SKY_FRAGMENT;
import static org.astemir.forestcraft.registries.FCItems.VILE_TENTACLE;

public class EntityChangeling extends MonsterEntity implements IAnimated {


    private static final DataParameter<Boolean> TRANSFORMED = EntityDataManager.createKey(EntityChangeling.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HARMED_SKIN = EntityDataManager.createKey(EntityChangeling.class, DataSerializers.BOOLEAN);


    public static final ItemDropTable CHANGELING_LOOT = new ItemDropTable().
            item(100.0, ItemDrop.fromItem(()->ROTTEN_FLESH,4,8)).
            item(5.0, ItemDrop.fromItem(()->VILE_TENTACLE,1,1));



    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(1.04f);

    public static final Animation IDLE_PARASITE = new Animation(1,"parasite_idle").
            loop().
            time(1.04f).
            speed(3f);

    public static final Animation CUTE_EYES = new Animation(2,"cute_eyes").
            loop().
            time(0.52f).
            speed(2f);

    public static final Animation FLOOF = new Animation(3,"floof").
            loop().
            time(1.04f).
            speed(2f);

    public static final Animation TRANSFORM = new Animation(4,"transform").
            time(1.28f).
            speed(1.5f).conflict(0,5);

    public static final Animation WHINING = new Animation(5,"whining_start").
            time(0.48f).
            speed(2f);

    public static final Animation ATTACK_0 = new Animation(6,"attack_0").
            time(1.04f).
            speed(0.75f).conflict(7,8);

    public static final Animation ATTACK_1 = new Animation(7,"attack_1").
            time(1.04f).
            speed(0.75f).conflict(6,8);

    public static final Animation ATTACK_2 = new Animation(8,"attack_2").
            time(1.04f).
            speed(0.75f).conflict(6,7);


    private static WeightedRandom<Animation> RANDOM_ATTACK_ANIMATION = new WeightedRandom().
            add(60,ATTACK_0).
            add(50,ATTACK_2).
            add(40,ATTACK_1).build();

    private AnimationFactory factory = createFactory();


    public EntityChangeling(EntityType type, World worldIn) {
        super(type, worldIn);
        stepHeight = 1;
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(TRANSFORMED,false);
        dataManager.register(HARMED_SKIN,false);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        dataManager.set(TRANSFORMED,compound.getBoolean("Transformed"));
        dataManager.set(HARMED_SKIN,compound.getBoolean("HasHarmedSkin"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Transformed",isTransformed());
        compound.putBoolean("HasHarmedSkin",hasHarmedSkin());
    }

    public void transform(){
        dataManager.set(TRANSFORMED,true);
    }


    public boolean isTransformed(){
        return dataManager.get(TRANSFORMED);
    }

    public boolean hasHarmedSkin(){
        return dataManager.get(HARMED_SKIN);
    }

    @Override
    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        CHANGELING_LOOT.dropItems(world,getPosition(),attackingPlayer);
        super.dropLoot(damageSourceIn, attackedRecently);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6,new FollowPlayerGoal(this,1.0f));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new ChangelingMeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10,true,false,(player)-> isTransformed()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 10,true,false,(player)-> isTransformed()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, 10,true,false,(player)-> isTransformed()));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        factory.playAnimation(FLOOF);
        if (!hasHarmedSkin()){
            if (!factory.isPlaying(TRANSFORM)){
                if (!isTransformed()) {
                    factory.playAnimation(IDLE);
                }
            }
            boolean nearbyPlayers = world.getEntitiesWithinAABB(PlayerEntity.class,getBoundingBox().grow(2,2,2)).size() > 0;
            if (nearbyPlayers){
                if (rand.nextInt(1000) == 0){
                    turn();
                }
                if (rand.nextInt(100) == 0){
                    if (!factory.isPlaying(TRANSFORM) && !factory.isPlaying(WHINING)) {
                        playAmbientSound();
                        factory.playAnimation(WHINING);
                    }
                }
                if (!factory.isPlaying(CUTE_EYES)){
                    factory.playAnimation(CUTE_EYES);
                }
            }else{
                factory.stopAnimation(CUTE_EYES);
            }
        }
        if (isTransformed()){
            if (!(factory.isPlaying(ATTACK_0) && factory.isPlaying(ATTACK_1) && factory.isPlaying(ATTACK_2))){
                factory.playAnimation(IDLE_PARASITE);
            }
        }
    }



    public static boolean canSpawn(EntityType<? extends EntityChangeling> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res && randomIn.nextInt(100) == 0;
    }




    @Override
    protected SoundEvent getAmbientSound() {
        return isTransformed() ? FCSounds.CHANGELING_PARASITE_AMBIENT.get() : FCSounds.CHANGELING_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return isTransformed() ? FCSounds.CHANGELING_PARASITE_HURT.get() : FCSounds.CHANGELING_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return isTransformed() ? FCSounds.CHANGELING_PARASITE_DEATH.get() : FCSounds.CHANGELING_DEATH.get();
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,64).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D);
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        turn();
        return super.attackEntityFrom(source, amount);
    }

    private void turn(){
        for (EntityChangeling changeling : world.getEntitiesWithinAABB(EntityChangeling.class, getBoundingBox().grow(5, 5, 5))) {
            if (!changeling.isTransformed()) {
                changeling.getFactory().playAnimation(TRANSFORM);
            }
        }
    }

    @Override
    public int getTalkInterval() {
        return 40;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        playSound(FCSounds.CHANGELING_PARASITE_ATTACK.get(), 1, RandomUtils.randomFloat(0.9f, 1.1f));
        Particle3D blood = new Particle3D(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()).size(0.5f,0.5f,0.5f).speed(0.25f,0.25f,0.25f).randomSpeed().count(8).renderTimes(4);
        Particle3D flesh = new Particle3D(ParticleTypes.BLOCK, FCBlocks.ROTTEN_FLESH_BLOCK.getDefaultState()).size(0.5f,0.5f,0.5f).speed(0.1f,0.1f,0.1f).randomSpeed().count(4).renderTimes(2);
        blood.play(world,entityIn.getPosX(),entityIn.getPosY(),entityIn.getPosZ());
        flesh.play(world,entityIn.getPosX(),entityIn.getPosY(),entityIn.getPosZ());
        factory.playAnimation(RANDOM_ATTACK_ANIMATION.random());
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    private class FollowPlayerGoal extends TemptGoal{

        public FollowPlayerGoal(CreatureEntity creatureIn, double speedIn) {
            super(creatureIn, speedIn, Ingredient.EMPTY, false);
        }


        @Override
        public boolean shouldExecute() {
            return super.shouldExecute() && !isTransformed();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && !isTransformed();
        }
    }
    private class ChangelingMeleeAttackGoal extends MeleeAttackGoal{

        public ChangelingMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
            super(creature, speedIn, useLongMemory);
        }

        @Override
        public boolean shouldExecute() {
            return super.shouldExecute() && isTransformed();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && isTransformed();
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth())*3;
        }
    }

    private AnimationFactory createFactory(){
        return new AnimationFactory(this,IDLE,IDLE_PARASITE,CUTE_EYES,FLOOF,TRANSFORM,WHINING,ATTACK_0,ATTACK_1,ATTACK_2){
            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == TRANSFORM){
                    transform();
                    playAmbientSound();
                }
            }

            @Override
            public void onAnimationTick(Animation animation,float tick) {
                if (animation == TRANSFORM){
                    if (((int)tick) == 3){
                        dataManager.set(HARMED_SKIN, true);
                        getFactory().stopAnimation(CUTE_EYES);
                    }
                    if (((int) tick) == 5) {
                        Particle3D blood = new Particle3D(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()).size(0.25f, 0.25f, 0.25f).speed(1, 1, 1).randomSpeed().count(5).renderTimes(10);
                        Particle3D gore = new Particle3D(ParticleTypes.BLOCK, FCBlocks.ROTTEN_FLESH_BLOCK.getDefaultState()).size(0.25f, 0.25f, 0.25f).speed(1, 1, 1).randomSpeed().count(5).renderTimes(5);
                        blood.play(world, getPosX(), getPosY(), getPosZ());
                        gore.play(world, getPosX(), getPosY(), getPosZ());
                        playSound(FCSounds.CHANGELING_TRANSFORM.get(), 1, 1);
                    }
                    EntityUtils.setMotion(EntityChangeling.this,EntityUtils.motion(EntityChangeling.this).mul(0,1,0));
                }
            }
        };
    }
}

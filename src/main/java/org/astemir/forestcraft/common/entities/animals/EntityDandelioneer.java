package org.astemir.forestcraft.common.entities.animals;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import javax.annotation.Nullable;


public class EntityDandelioneer extends AnimalEntity implements IAnimated {

    private static final Ingredient breedingItem = Ingredient.fromItems(Items.DANDELION, FCBlocks.GROWN_DANDELION.asItem());



    public static final Animation IDLE = new Animation(0,"idle").
            loop().time(1.04f).conflict(1,2);
    public static final Animation IDLE2 = new Animation(1,"idle2")
            .time(1.04f).conflict(0);
    public static final Animation RUN = new Animation(2,"run").
            loop().time(1.04f).conflict(0,1);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,IDLE2,RUN);

    public EntityDandelioneer(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 2;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 0.7D));
        goalSelector.addGoal(2, new BreedGoal(this, 0.5D));
        goalSelector.addGoal(3, new TemptGoal(this, 0.5D, breedingItem, false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 0.5D));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }


    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return FCEntities.DANDELIONEER.create(p_241840_1_);
    }

    @Override
    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        super.dropLoot(damageSourceIn, attackedRecently);
        if (RandomUtils.doWithChance(75)) {
            if (isChild()) {
                WorldUtils.dropItem(world, getPosition(), Items.DANDELION, Vector3d.ZERO);
            } else {
                WorldUtils.dropItem(world, getPosition(), FCBlocks.GROWN_DANDELION.asItem(), Vector3d.ZERO);
            }
        }
    }

    @Override
    protected boolean canDropLoot() {
        return true;
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return breedingItem.test(stack);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (limbSwingAmount > 0.01 && limbSwing > 0.01){
            factory.playAnimation(RUN);
        }else {
            if (!factory.isPlaying(IDLE2)) {
                factory.playAnimation(IDLE);
            }
        }
        if (rand.nextInt(400) == 0){
            if (!factory.isPlaying(RUN)) {
                factory.playAnimation(IDLE2);
            }
        }
        if (!isChild()) {
            if (rand.nextInt(40) == 0) {
                for (int i = 0; i < 3; i++) {
                    world.addParticle(FCParticles.DANDELION_SEED.get(), getPosition().getX(), getPosition().getY(), getPosition().getZ(), RandomUtils.randomFloat(-0.1f, 0.1f), RandomUtils.randomFloat(0.5f, 0.65f), RandomUtils.randomFloat(-0.1f, 0.1f));
                }
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.DANDELIONEER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.DANDELIONEER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.DANDELIONEER_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,15).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.4D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,1);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }
}

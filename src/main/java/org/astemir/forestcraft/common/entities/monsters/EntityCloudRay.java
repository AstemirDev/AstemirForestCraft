package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityMiniTornadoProjectile;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.EnumSet;
import java.util.Random;

import static net.minecraft.item.Items.PHANTOM_MEMBRANE;
import static org.astemir.forestcraft.registries.FCItems.SKY_FRAGMENT;

public class EntityCloudRay extends MonsterEntity implements IFlyingAnimal, IDamageResistable, IAnimated {

    private static DamageResistanceMap damageResistanceMap = new DamageResistanceMap().
            put(EntityMiniTornadoProjectile.class,1);


    public static final ItemDropTable CLOUD_RAY_LOOT = new ItemDropTable().
            item(20.0, ItemDrop.fromItem(()->SKY_FRAGMENT,1,2)).
            item(50.0, ItemDrop.fromItem(()->PHANTOM_MEMBRANE,1,1));


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(1.04f).
            speed(2).conflict(2);
    public static final Animation ATTACK = new Animation(1,"attack").
            time(0.52f);
    public static final Animation IDLE2 = new Animation(2,"idle2").
            time(1.04f).
            speed(2).conflict(0);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,ATTACK,IDLE2);



    public EntityCloudRay(EntityType type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return 0.0F;
    }



    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return 0.0F;
    }




    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(5, new LookRandomlyGoal(this));
        goalSelector.addGoal(1, new AttackTornadoGoal());
        goalSelector.addGoal(5, new MoveRandomGoal());
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.8f,true));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        factory.playAnimation(ATTACK);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        CLOUD_RAY_LOOT.dropItems(world,getPosition(),attackingPlayer);
        super.dropLoot(damageSourceIn, attackedRecently);
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isInWater()) {
            this.moveRelative(0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)0.8F));
        } else if (this.isInLava()) {
            this.moveRelative(0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.5D));
        } else {
            BlockPos ground = new BlockPos(this.getPosX(), this.getPosY() - 1.0D, this.getPosZ());
            float f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);
            f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)f));
        }

        this.func_233629_a_(this, false);
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }



    private int attackTicks = 0;


    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (!factory.isPlaying(IDLE2)) {
            factory.playAnimation(IDLE);
        }
        if (rand.nextInt(500) == 0){
            if (!factory.isPlaying(ATTACK)){
                factory.playAnimation(IDLE2);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.CLOUD_RAY_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.CLOUD_RAY_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.CLOUD_RAY_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,25).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.6D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D);
    }



    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return randomIn.nextInt(50) == 0 && pos.getY() >= worldIn.getSeaLevel();
    }


    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        int posY = (int) (getPosY()+RandomUtils.randomInt(75,100));
        if (posY > 160){
            posY = 160;
        }
        setPosition(getPosX(),posY,getPosZ());
        return world.getDimensionKey() == World.OVERWORLD;
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return damageResistanceMap;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    private class AttackTornadoGoal extends Goal{

        private Vector3 orbitalPosition;
        private int changeLocationTicks = 0;


        public AttackTornadoGoal() {
            setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return getAttackTarget() != null;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return getAttackTarget() != null;
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            Vector3 targetPos = new Vector3(getAttackTarget().getPosX(),getAttackTarget().getPosY()+2,getAttackTarget().getPosZ());
            orbitalPosition = targetPos;
        }

        @Override
        public void tick() {
            if (getAttackTarget() != null) {
                attackTicks++;
                if (changeLocationTicks > 0){
                    changeLocationTicks--;
                }
                if (canEntityBeSeen(getAttackTarget())) {
                    Vector3 pos = new Vector3(getPosX(),getPosY(),getPosZ());
                    Vector3 targetPos = new Vector3(getAttackTarget().getPosX()+RandomUtils.randomFloat(-1,1),getAttackTarget().getPosY()+2+RandomUtils.randomInt(1,2),getAttackTarget().getPosZ()+RandomUtils.randomFloat(-1,1));
                    if (changeLocationTicks <= 0){
                        orbitalPosition = targetPos;
                        changeLocationTicks = 20;
                    }
                    float distance = pos.distanceTo(orbitalPosition);
                    if (distance > 1) {
                        Vector3 dir = pos.direction(orbitalPosition).normalize();
                        Vector3d moveVelocity = new Vector3d(dir.getX() / 5, dir.getY() / 5, dir.getZ() / 5);
                        Vector2 yawPitch = pos.direction(targetPos).normalize().yawPitchDegrees();
                        rotationYaw = -yawPitch.getX();
                        renderYawOffset = rotationYaw;
                        setMotion(moveVelocity);
                    }
                }
                if (attackTicks >= 60){
                    attackTicks = 0;
                    factory.playAnimation(ATTACK);
                    EntityMiniTornadoProjectile projectile = new EntityMiniTornadoProjectile(world, EntityCloudRay.this);
                    Vector3d vector3d = getAttackTarget().getMotion();
                    double d0 = getAttackTarget().getPosX() + vector3d.x - getPosX();
                    double d1 = getAttackTarget().getPosYEye() - getPosY();
                    double d2 = getAttackTarget().getPosZ() + vector3d.z - getPosZ();
                    projectile.shoot(d0, d1, d2, 0.5f, 0);
                    world.addEntity(projectile);
                }
            }
        }
    }

    private class MoveRandomGoal extends Goal{

        private Vector3 direction;
        private int ticks = 0;


        public MoveRandomGoal() {
            setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            return getAttackTarget() == null;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return getAttackTarget() == null;
        }

        private void pickupDirection(){
            direction = new Vector3(RandomUtils.randomFloat(-1,1),0,RandomUtils.randomFloat(-1,1));
            boolean onGround = world.getBlockState(getPosition().down()).isSolid();
            if (RandomUtils.doWithChance(10) || onGround){
                direction.y = onGround ? 0.5f : RandomUtils.randomFloat(-0.1f,0.1f);
            }
            direction = direction.normalize();
        }

        @Override
        public void tick() {
            if (ticks % 200 == 0){
                pickupDirection();
            }
            RayTraceResult res = EntityUtils.rayTrace(world,EntityCloudRay.this,1);
            if (res instanceof BlockRayTraceResult) {
                if (world.getBlockState(((BlockRayTraceResult)res).getPos()).isSolid()){
                    pickupDirection();
                }
            }
            EntityUtils.lookAt(EntityCloudRay.this,direction);
            setMotion(direction.x*0.25f,direction.y*0.25f,direction.z*0.25f);
            ticks++;
        }
    }
}

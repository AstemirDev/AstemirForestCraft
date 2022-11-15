package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.entities.ai.TargetGoal3D;

import java.util.Random;

public class EntityNightBat extends MonsterEntity {



    private int ticksToAttack = 0;
    private int attackTicks = 0;

    private BlockPos spawnPosition;


    public EntityNightBat(EntityType type, World worldIn) {
        super(type, worldIn);
        this.moveController = new FlyingMovementController(this,80,true);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    public void tick() {
        super.tick();
        if (attackTicks <= 0) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,1f,true));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(3, new TargetGoal3D(this,PlayerEntity.class,false));
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.down();
        boolean res = reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).canEntitySpawn(worldIn, blockpos, typeIn);
        return res;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if (world.getDimensionKey().getLocation().toString().equals("minecraft:overworld")) {
            return super.canSpawn(worldIn, spawnReasonIn);
        }
        return false;
    }

    @Override
    public void livingTick() {
        if (attackTicks > 0){
            attackTicks--;
        }
        if (ticksToAttack < 64){
            ticksToAttack++;
        }else{
            playSound(SoundEvents.ENTITY_BAT_AMBIENT,1,0.65f);
            attackTicks = 32;
            ticksToAttack = 0;
        }
        super.livingTick();
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.75F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,10).createMutableAttribute(Attributes.FLYING_SPEED,2.5D).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        attackTicks = 0;
        if (entityIn instanceof LivingEntity) {
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS,40,0,false,false));
        }
        return super.attackEntityAsMob(entityIn);
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (getAttackTarget() == null || attackTicks <= 0) {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(80) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
                this.spawnPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.getPosY() + (double)this.rand.nextInt(6) - 2.0D, this.getPosZ() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
            }

            double d2 = (double)this.spawnPosition.getX() + 0.5D - this.getPosX();
            double d0 = (double)this.spawnPosition.getY() + 0.1D - this.getPosY();
            double d1 = (double)this.spawnPosition.getZ() + 0.5D - this.getPosZ();
            Vector3d vector3d = this.getMotion();
            Vector3d vector3d1 = vector3d.add((Math.signum(d2) * 0.5D - vector3d.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vector3d.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vector3d.z) * (double)0.1F);
            this.setMotion(vector3d1);
            float f = (float)(MathHelper.atan2(vector3d1.z, vector3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;
        }else{
            if (canEntityBeSeen(getAttackTarget())){
                if (attackTicks > 0){
                    EntityNightBat.this.getLookController().setLookPositionWithEntity(getAttackTarget(), (float)EntityNightBat.this.getHorizontalFaceSpeed(), (float)EntityNightBat.this.getVerticalFaceSpeed());
                    moveController.setMoveTo(getAttackTarget().getPosX(),getAttackTarget().getPosY(),getAttackTarget().getPosZ(),1.5f);
                }
            }
        }
    }

}

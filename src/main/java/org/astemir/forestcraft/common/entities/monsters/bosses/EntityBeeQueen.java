package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.loot.ItemDropTable;
import org.astemir.api.network.BossMusicMessage;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.client.AColor;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.api.client.animator.Animation;
import org.astemir.forestcraft.registries.FCEntityStats;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.entities.animals.EntityKillerBee;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static net.minecraft.item.Items.BEE_NEST;
import static net.minecraft.item.Items.HONEYCOMB;
import static org.astemir.forestcraft.registries.FCItems.*;


public class EntityBeeQueen extends MonsterEntity implements IDamageResistable, IAnimated {



    private static final DamageResistanceMap RESISTANCE_MAP = new DamageResistanceMap().
            put(AbstractArrowEntity.class,0.3f);


    public static final ItemDropTable BEEQUEEN_LOOT = new ItemDropTable().
            item(ItemDrop.fromItem(()->ROYAL_JELLY,30,40)).
            item(ItemDrop.fromItem(()->GIANT_STINGER,1,1)).
            item(ItemDrop.fromItem(()->BEE_NEST,1,2)).
            item(ItemDrop.fromItem(()->HONEYCOMB,4,6)).
            item(new ItemDropPool().
                    add(50.0, ItemDrop.fromItem(()->STINGER_SWORD.asItem())).
                    add(40.0, ItemDrop.fromItem(()->ROYAL_STAFF.asItem())).
                    add(30.0, ItemDrop.fromItem(()-> FCItems.HONEY_KEEPER.asItem())).
                    build());


    public static final Animation IDLE = new Animation(0,"idle").
            loop().
            time(37);
    public static final Animation ATTACK = new Animation(1,"attack").
            conflict(0,3).
            time(37);
    public static final Animation SCREECH = new Animation(3,"screech").
            conflict(0,1).
            loop().
            time(37);
    public static final Animation SPAWN = new Animation(2,"spawn").
            time(37);
    public static final Animation DEATH = new Animation(4,"death").
            conflict(0,1,2,3).
            time(9999);

    private final ABossbar bossbar = new ABossbar(this.getDisplayName().getString()).color(AColor.YELLOW).style(ABossbar.Style.DEFAULT);
    private BeeQueenMinionsGoal minionsGoal;
    private float deathTicks = 0;
    private float lastYaw;

    private AnimationFactory factory = new AnimationFactory(this,IDLE, ATTACK, SCREECH, SPAWN, DEATH){
        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == EntityBeeQueen.SPAWN){
                if (!world.isRemote){
                    playAnimation(SCREECH);
                    ((ServerWorld)world).getServer().getPlayerList().sendPacketToAllPlayers(new SPlaySoundEffectPacket(FCSounds.BEEQUEEN_SCREECH.get(), SoundCategory.AMBIENT, getPosX(),getPosY(),getPosZ(),2, 1));
                    world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(6, 6, 6)).forEach((player) -> {
                        EffectInstance effect = new EffectInstance(FCEffects.FEAR.get(), 20, 15, true, true);
                        ((ServerPlayerEntity)player).connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effect));
                        player.addPotionEffect(effect);
                    });
                }
            }
        }
    };

    public EntityBeeQueen(EntityType type, World worldIn) {
        super(type, worldIn);
        experienceValue = 300;
        bossbar.boss(this);
        stepHeight = 1;
    }



    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        factory.playAnimation(SPAWN);
        FCEntityStats.BEE_QUEEN.getStats(difficultyIn.getDifficulty()).apply(this);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }


    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20;
    }


    @Override
    protected boolean isDespawnPeaceful() {
        return true;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }



    @Override
    protected void registerGoals() {
        super.registerGoals();
        minionsGoal = new BeeQueenMinionsGoal(this);
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this,PlayerEntity.class,false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this,MobEntity.class,0,false,false,(entity)->{
            if (entity instanceof EntityKillerBee){
                if (((EntityKillerBee) entity).getBeeQueen() != null){
                    return !((EntityKillerBee) entity).getBeeQueen().getUniqueID().equals(this.getUniqueID());
                }else{
                    ((EntityKillerBee)entity).setBeeQueen(this);
                    return false;
                }
            }else {
                return !(entity instanceof BeeEntity);
            }
        }));
        goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this,0.5f));
        goalSelector.addGoal(3,new MeleeAttackGoal(this,0.5f,true));
        goalSelector.addGoal(4,new BeeQueenScreechGoal(this));
        goalSelector.addGoal(5,minionsGoal);
        goalSelector.addGoal(7, new LookRandomlyGoal(this));
        goalSelector.addGoal(7,new LookAtGoal(this,LivingEntity.class,20));
    }


    @Override
    public boolean isNonBoss() {
        return false;
    }


    @Override
    public int getVerticalFaceSpeed() {
        if (deathTicks > 0) {
            return 0;
        }else{
            return super.getVerticalFaceSpeed();
        }
    }

    @Override
    public int getHorizontalFaceSpeed() {
        if (deathTicks > 0) {
            return 0;
        }else{
            return super.getHorizontalFaceSpeed();
        }
    }

    @Override
    public int getFaceRotSpeed() {
        if (deathTicks > 0) {
            return 0;
        }else{
            return super.getFaceRotSpeed();
        }
    }


    @Override
    public void onDeath(DamageSource cause) {
        lastYaw = renderYawOffset;
        if (minionsGoal != null) {
            minionsGoal.killMinions();
        }
        super.onDeath(cause);
    }


    @Override
    protected void onDeathUpdate() {
        ++this.deathTicks;
        factory.playAnimation(DEATH);
        renderYawOffset = lastYaw;
        prevRenderYawOffset = lastYaw;
        if (deathTicks > 100){
            remove();
            BEEQUEEN_LOOT.dropItems(world,getPosition(),attackingPlayer);
            playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,2,2);
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                if (i < 10){
                    FallingBlockEntity block = new FallingBlockEntity(world,getPosX()+RandomUtils.randomFloat(-0.5f,0.5f),getPosY()+RandomUtils.randomFloat(0.5f,1f),getPosZ()+RandomUtils.randomFloat(-0.5f,0.5f), Blocks.HONEY_BLOCK.getDefaultState());
                    block.fallTime = 1;
                    block.setMotion(new Vector3d(RandomUtils.randomFloat(-0.15f,0.15f),RandomUtils.randomFloat(0.5f,1f),RandomUtils.randomFloat(-0.15f,0.15f)));
                    world.addEntity(block);
                }
                this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), 1, 1, 1);
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
    }


    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }



    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.show(player);
        }
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> player), new BossMusicMessage(getEntityId(),0));
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.hide(player);
        }
    }



    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (this.ticksExisted % 5 == 0 && !getShouldBeDead()){
            playSound(SoundEvents.ENTITY_ENDER_DRAGON_FLAP,0.5f,2);
        }
        if (!factory.isPlaying(SCREECH) && !factory.isPlaying(ATTACK) && !factory.isPlaying(DEATH) && !factory.isPlaying(SPAWN)) {
            factory.playAnimation(IDLE);
        }
        if (factory.isPlaying(SPAWN)){
            setMotion(0,0,0);
        }
    }



    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
            bossbar.update();
        }
        this.world.addParticle(ParticleTypes.LANDING_HONEY, this.getPosX()+RandomUtils.randomFloat(-0.5f,0.5f), this.getPosY() + 1.5f+RandomUtils.randomFloat(-0.5f,0.5f), this.getPosZ()+RandomUtils.randomFloat(-0.5f,0.5f), 0.3D, 0.3D, 0.3D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.BEEQUEEN_SCREECH.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.BEEQUEEN_SCREECH.get();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!factory.isPlaying(SCREECH)) {
            factory.playAnimation(ATTACK);
            return super.attackEntityAsMob(entityIn);
        }
        return false;
    }



    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,200).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D).createMutableAttribute(Attributes.ARMOR,4).createMutableAttribute(Attributes.ATTACK_KNOCKBACK,2).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE,1);
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return RESISTANCE_MAP;
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    static class BeeQueenMinionsGoal extends Goal {

        private final EntityBeeQueen beeQueen;
        private final CopyOnWriteArraySet<BeeEntity> minions;

        public BeeQueenMinionsGoal(EntityBeeQueen entityIn) {
            this.beeQueen = entityIn;
            this.minions = new CopyOnWriteArraySet<>();
        }



        private void spawnMinion(){
            EntityKillerBee bee = new EntityKillerBee(EntityType.BEE,beeQueen.getEntityWorld());
            bee.setBeeQueen(beeQueen);
            BlockPos spawnPos = beeQueen.getPosition().add(RandomUtils.randomFloat(0.5f,-0.5f),1,RandomUtils.randomFloat(0.5f,-0.5f));
            bee.moveToBlockPosAndAngles(spawnPos,0,0);
            bee.onInitialSpawn((ServerWorld)beeQueen.world,beeQueen.getEntityWorld().getDifficultyForLocation(spawnPos),SpawnReason.MOB_SUMMONED,null,null);
            if (beeQueen.getAttackTarget() != null) {
                bee.setAngerTarget(beeQueen.getAttackTarget().getUniqueID());
                bee.setAttackTarget(beeQueen.getAttackTarget());
            }
            bee.setAngerTime(9999);
            beeQueen.world.addEntity(bee);
            minions.add(bee);
        }

        public void killMinions(){
            minions.forEach((m)->{
                m.remove();
            });
            minions.clear();
        }

        private void spawnMinions(int count){
            beeQueen.playSound(FCSounds.BEEQUEEN_SPAWN.get(),2,1);
            for (int i = 0;i<count;i++){
                spawnMinion();
            }
        }


        @Override
        public void tick() {
            super.tick();
            float maxHealth = beeQueen.getMaxHealth();
            float health = beeQueen.getHealth();
            int needToSpawn = (int)Math.min((maxHealth*2)/health,20);
            if (minions.size() < needToSpawn){
                spawnMinions(needToSpawn-minions.size());
            }else{
                for (BeeEntity entity : minions){
                    if (!entity.isAlive()){
                        minions.remove(entity);
                    }
                    if (Math.abs(entity.getPosX()-beeQueen.getPosX()) > 40 || Math.abs(entity.getPosY()-beeQueen.getPosY()) > 40 || Math.abs(entity.getPosZ()-beeQueen.getPosZ()) >40){
                        entity.remove();
                    }
                }
            }
        }

        @Override
        public void resetTask() {
            super.resetTask();
            killMinions();
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

    static class BeeQueenScreechGoal extends Goal{

        private int timeToBegin;
        private int screechingTime;
        private final EntityBeeQueen beeQueen;

        public BeeQueenScreechGoal(EntityBeeQueen entityIn) {
            this.beeQueen = entityIn;
        }

        @Override
        public void startExecuting() {
            timeToBegin = 0;
            screechingTime = 0;
        }


        @Override
        public void tick() {
            if (timeToBegin > Math.min(Math.max(beeQueen.getHealth(),60),200)){
                beeQueen.factory.playAnimation(SCREECH);
                screechingTime = 60;
                timeToBegin = 0;
            }else{
                timeToBegin++;
            }

            if (screechingTime > 0){
                if (screechingTime % 20 == 0) {
                    beeQueen.world.getTargettableEntitiesWithinAABB(BeeEntity.class,EntityPredicate.DEFAULT, beeQueen, beeQueen.getBoundingBox().grow(20.0D, 20.0D, 20.0D)).forEach((bee)->{
                        boolean canObey = true;
                        if (bee instanceof EntityKillerBee) {
                            if (((EntityKillerBee)bee).getBeeQueen() != null){
                                canObey = ((EntityKillerBee)bee).getBeeQueen().getUniqueID().equals(beeQueen.getUniqueID());
                            }
                        }
                        if (canObey) {
                            bee.setAngerTime(9999);
                            if (beeQueen.getAttackTarget() != null) {
                                double x = ((float) beeQueen.getAttackTarget().getPosX() - bee.getPosX());
                                double y = ((float) beeQueen.getAttackTarget().getPosY() - bee.getPosY());
                                double z = ((float) beeQueen.getAttackTarget().getPosZ() - bee.getPosZ());
                                Vector3d vel = new Vector3d(x, y, z).normalize();
                                bee.rotationYaw = beeQueen.rotationYaw;
                                bee.rotationPitch = beeQueen.rotationPitch;
                                bee.setMotion(vel.getX(), vel.getY(), vel.getZ());
                                bee.setAttackTarget(beeQueen.getAttackTarget());
                                bee.setAngerTarget(beeQueen.getAttackTarget().getUniqueID());
                            }
                        }
                    });
                    beeQueen.playSound(FCSounds.BEEQUEEN_SCREECH.get(), 3f, RandomUtils.randomFloat(0.9f,1.2f));
                }
                screechingTime--;
            }else{
                if (beeQueen.factory.isPlaying(SCREECH)) {
                    this.beeQueen.factory.stopAnimation(SCREECH);
                }
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return (beeQueen.getAttackTarget() != null || beeQueen.factory.isPlaying(SCREECH)) && !beeQueen.factory.isPlaying(SPAWN);
        }

        @Override
        public boolean shouldExecute() {
            return (beeQueen.getAttackTarget() != null || beeQueen.factory.isPlaying(SCREECH)) && !beeQueen.factory.isPlaying(SPAWN);
        }
    }

}

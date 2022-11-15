package org.astemir.forestcraft.common.entities.animals;


import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;
import javax.annotation.Nullable;
import java.util.EnumSet;


public class EntityCicada extends CreatureEntity implements IFlyingAnimal, IAnimated {


    private static final DataParameter<Boolean> HANGING_ON_TREE = EntityDataManager.createKey(EntityCicada.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> POSE = EntityDataManager.createKey(EntityCicada.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityCicada.class, DataSerializers.VARINT);
    private static final DataParameter<CompoundNBT> HANGING_POS = EntityDataManager.createKey(EntityCicada.class, DataSerializers.COMPOUND_NBT);
    private static final DataParameter<CompoundNBT> REAL_POS = EntityDataManager.createKey(EntityCicada.class, DataSerializers.COMPOUND_NBT);

    public static final Animation IDLE = new Animation(0,"idle").
            loop().speed(0.75f).
            time(0.6f).conflict(1);


    public static final Animation TREE = new Animation(1,"tree").
            loop().
            time(1.2f).conflict(0);

    private AnimationFactory factory = new AnimationFactory(this,IDLE,TREE);

    private int damageTicks = 0;

    public EntityCicada(EntityType type, World worldIn) {
        super(type, worldIn);
        this.experienceValue = 0;
        this.moveController = new FlyingMovementController(this, 20, true);
        this.lookController = new LookController(this){
            @Override
            public void tick() {

            }

            @Override
            protected boolean shouldResetPitch() {
                return false;
            }
        };
        enablePersistence();
    }


    public Vector3d getHangingPos(){
        CompoundNBT nbt = dataManager.get(HANGING_POS);
        if (nbt.contains("x") && nbt.contains("y") && nbt.contains("z")){
            return new Vector3d(nbt.getDouble("x"),nbt.getDouble("y"),nbt.getDouble("z"));
        }
        return null;
    }

    public void setHangingPose(CompoundNBT nbt){
        dataManager.set(HANGING_POS,nbt);
    }

    public void setHangingPose(Vector3d vector){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("x", vector.x);
        nbt.putDouble("y", vector.y);
        nbt.putDouble("z", vector.z);
        dataManager.set(HANGING_POS,nbt);
    }

    public void resetHangingPose(){
        CompoundNBT nbt = new CompoundNBT();
        dataManager.set(HANGING_POS,nbt);
    }


    public void setRealPos(CompoundNBT nbt){
        dataManager.set(REAL_POS,nbt);
    }

    public void setRealPos(BlockPos pos){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("x", pos.getX());
        nbt.putInt("y", pos.getY());
        nbt.putInt("z", pos.getZ());
        dataManager.set(REAL_POS,nbt);
    }


    public BlockPos getRealPos(){
        CompoundNBT nbt = dataManager.get(REAL_POS);
        if (nbt.contains("x") && nbt.contains("y") && nbt.contains("z")){
            return new BlockPos(nbt.getInt("x"),nbt.getInt("y"),nbt.getInt("z"));
        }
        return null;
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
            @Override
            public boolean canEntityStandOnPos(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

            @Override
            public void tick() {
                if (!isHangingOnTree()) {
                    super.tick();
                }
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanSwim(false);
        flyingpathnavigator.setCanEnterDoors(true);
        return flyingpathnavigator;
    }


    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(HANGING_ON_TREE,false);
        dataManager.register(POSE,0);
        dataManager.register(TYPE,0);
        dataManager.register(REAL_POS,new CompoundNBT());
        dataManager.register(HANGING_POS,new CompoundNBT());
    }


    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        dataManager.set(TYPE,RandomUtils.randomInt(0,3));
        enablePersistence();
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Skin", this.getSkinType());
        Vector3d hangingPose = getHangingPos();
        if (hangingPose != null) {
            compound.put("HangPos", dataManager.get(HANGING_POS));
        }
        compound.putInt("HangingPose",dataManager.get(POSE));
        compound.putBoolean("isHanging",dataManager.get(HANGING_ON_TREE));
        CompoundNBT pos = serializeBlockPos();
        if (pos != null) {
            compound.put("RealPos", pos);
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(TYPE,compound.getInt("Skin"));
        if (compound.contains("HangPos")){
            setHangingPose((CompoundNBT)compound.get("HangPos"));
        }
        if (compound.contains("RealPos")){
            setRealPos(((CompoundNBT) compound.get("RealPos")));
        }
        dataManager.set(POSE,compound.getInt("HangingPose"));
        dataManager.set(HANGING_ON_TREE,compound.getBoolean("isHanging"));
    }

    public void setSkin(int i){
        this.dataManager.set(TYPE,i);
    }


    public CompoundNBT serializeBlockPos(){
        CompoundNBT nbt = new CompoundNBT();
        BlockPos realPos = getRealPos();
        if (realPos != null) {
            nbt.putInt("x", realPos.getX());
            nbt.putInt("y", realPos.getY());
            nbt.putInt("z", realPos.getZ());
            return nbt;
        }else{
            return new CompoundNBT();
        }
    }




    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EntityCicada.WanderGoal());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.FLY_INTO_WALL)) {
            return false;
        }else{
            setHangingOnTree(false);
            damageTicks = 60;
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FCSounds.CICADA_AMBIENT.get();
    }

    public boolean isHangingOnTree(){
        return dataManager.get(HANGING_ON_TREE);
    }


    public void setHangingOnTree(boolean b){
        this.dataManager.set(HANGING_ON_TREE,b);
        if (b){
            setMotion(0,0,0);
            setNoGravity(true);
        }else{
            resetHangingPose();
            setRealPos(new CompoundNBT());
            setNoGravity(false);
        }
    }


    public int getHangingPose(){
        return dataManager.get(POSE);
    }


    public void setHangingPose(int pose){
        this.dataManager.set(POSE,pose);
    }

    public int getSkinType(){
        return dataManager.get(TYPE);
    }


    @Override
    protected void updateAITasks() {
        if (!isHangingOnTree()) {
            super.updateAITasks();
        }
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }


    @Override
    public int getTalkInterval() {
        return 5;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        factory.tick();
        if (isHangingOnTree()){
            factory.playAnimation(TREE);
        }else{
            factory.playAnimation(IDLE);
        }
        if (damageTicks > 0){
            damageTicks--;
        }
        if (!isHangingOnTree() && damageTicks <= 0) {
            Vector3d pose = getNormalHangingPose();
            if (pose != null && getRealPos() != null) {
                setHangingPose(pose);
                setHangingOnTree(true);
            }
        }else{
            if (getHangingPos() != null && getRealPos() != null) {
                if (isNormalBlockState(world.getBlockState(getRealPos()))) {
                    Vector3d hangPos = getHangingPos();
                    setPosition(hangPos.getX(), hangPos.getY(), hangPos.getZ());
                }else{
                    setHangingOnTree(false);
                    damageTicks = 60;
                }
            }
        }
    }


    public boolean isNormalBlockState(BlockState state){
        return state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES);
    }

    public boolean isLog(Vector3d offset){
        BlockPos pos = new BlockPos(getPosX()+offset.getX(),getPosY()+offset.getY(),getPosZ()+offset.getZ());
        if (world.getEntitiesWithinAABB(EntityCicada.class,new AxisAlignedBB(pos).grow(0.2f,0.2f,0.2f)).isEmpty()) {
            return isNormalBlockState(world.getBlockState(pos));
        }else{
            return false;
        }
    }

    public Vector3d getNormalHangingPose() {
        if (isLog(new Vector3d(-1, 0, 0))) {
            setRealPos(new BlockPos(getPosX() - 1, getPosY(), getPosZ()));
            setHangingPose(0);
            return new Vector3d(getRealPos().getX() + 1.05, getRealPos().getY() + 0.5, getRealPos().getZ() + 0.5);

        } else if (isLog(new Vector3d(1, 0, 0))) {
            setRealPos(new BlockPos(getPosX() + 1, getPosY(), getPosZ()));
            setHangingPose(1);
            return new Vector3d(getRealPos().getX() - 0.05, getRealPos().getY() + 0.5, getRealPos().getZ() + 0.5);
        } else if (isLog(new Vector3d(0, 0, 1))) {
            setRealPos(new BlockPos(getPosX(), getPosY(), getPosZ() + 1));
            setHangingPose(2);
            return new Vector3d(getRealPos().getX() + 0.5, getRealPos().getY() + 0.5, getRealPos().getZ() - 0.05);

        } else if (isLog(new Vector3d(0, 0, -1))) {
            setRealPos(new BlockPos(getPosX(), getPosY(), getPosZ() - 1));
            setHangingPose(3);
            return new Vector3d(getRealPos().getX() + 0.5, getRealPos().getY() + 0.5, getRealPos().getZ() + 1.05);

        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FCSounds.CICADA_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FCSounds.CICADA_DEATH.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH,5).createMutableAttribute(Attributes.MOVEMENT_SPEED,0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D).createMutableAttribute(Attributes.FLYING_SPEED,1);
    }

    @Override
    public <T extends IAnimated> AnimationFactory<T> getFactory() {
        return factory;
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            return EntityCicada.this.navigator.noPath() && EntityCicada.this.rand.nextInt(10) == 0 && !isHangingOnTree();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return EntityCicada.this.navigator.hasPath() && !isHangingOnTree();
        }

        @Override
        public void startExecuting() {
            Vector3d vector3d = this.getRandomLocation();
            if (vector3d != null) {
                EntityCicada.this.navigator.setPath(EntityCicada.this.navigator.getPathToPos(new BlockPos(vector3d), 1), 1.0D);
            }
        }

        @Nullable
        private Vector3d getRandomLocation() {
            Vector3d vector3d = EntityCicada.this.getLook(0.0F);
            int i = 8;
            Vector3d vector3d2 = RandomPositionGenerator.findAirTarget(EntityCicada.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return vector3d2 != null ? vector3d2 : RandomPositionGenerator.findGroundTarget(EntityCicada.this, 8, 4, -2, vector3d, (float)Math.PI / 2F);
        }
    }



}

package org.astemir.forestcraft.common.entities.projectiles.throwable;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCLootTables;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.common.entities.monsters.EntityBakudan;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import java.util.function.Predicate;

public class EntityBakudanBall extends ThrowableEntity {

    private static final DataParameter<Boolean> IS_RED = EntityDataManager.createKey(EntityBakudanBall.class, DataSerializers.BOOLEAN);
    private long ticks = 0;

    public EntityBakudanBall(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }


    protected EntityBakudanBall(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    protected EntityBakudanBall(EntityType<? extends ThrowableEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public EntityBakudanBall(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.BAKUDAN_BALL, throwerIn, worldIn);
    }



    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        BlockState state = world.getBlockState(getPosition());
        if (!isBlockAir(state)){
            setMotion(0,0,0);
        }else {
            setMotion(new Vector3d(getMotion().x * 0.95, 0, getMotion().z * 0.95));
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        if (p_213868_1_.getEntity() instanceof LivingEntity && !(p_213868_1_.getEntity() instanceof EntityBakudan)) {
            if (getOwner() != null) {
                if (!p_213868_1_.getEntity().getUniqueID().equals(getOwner().getUniqueID())) {
                    destroy();
                    p_213868_1_.getEntity().attackEntityFrom(DamageSource.causeExplosionDamage(getOwner()), 10);
                }
            }
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
    }

    public LivingEntity getOwner(){
        Entity entity = func_234616_v_();
        if (entity != null){
            return (LivingEntity) entity;
        }
        return null;
    }


    @Override
    public void tick() {
        super.tick();
        world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox(), new Predicate<LivingEntity>() {
            @Override
            public boolean test(LivingEntity entity) {
                return !(entity instanceof EntityBakudan);
            }
        }).forEach((e) -> {
            if (!removed) {
                if (getOwner() != null) {
                    if (!getOwner().getUniqueID().equals(e.getUniqueID())) {
                        destroy();
                        e.attackEntityFrom(DamageSource.causeThrownDamage(this, func_234616_v_()), 10);
                    }
                }
            }
        });
        if (ticks >= 80){
            BlockPos.getAllInBox(getBoundingBox()).forEach((block)->{
                if (world.getBlockState(block).isIn(FCBlocks.BAKUDAN_TREASURE)){
                    WorldUtils.dropItem(world,block, FCLootTables.BAKUDANS_TREASURE.drop());
                    world.destroyBlock(block,false,getOwner());
                }
            });
            destroy();
        }
        ticks++;
    }


    public boolean isPlant(BlockState state){
        return state.getBlock() instanceof FlowerBlock || state.getBlock() instanceof DoublePlantBlock || state.getBlock() instanceof TallGrassBlock;
    }

    public boolean isBlockAir(BlockState state){
        Block block = state.getBlock();
        if (!block.getDefaultState().isSolid()){
            return true;
        }else
        if (block.equals(Blocks.SNOW) || isPlant(block.getDefaultState()) || state.isAir() || block.equals(Blocks.WARPED_ROOTS) || block.equals(Blocks.WARPED_FUNGUS) || block.equals(Blocks.NETHER_SPROUTS) || block.equals(Blocks.TWISTING_VINES) || block.equals(Blocks.TWISTING_VINES_PLANT)){
            return true;
        }
        return false;
    }


    public void destroy(){
        if (!world.isRemote) {
            for (int i = 0; i < 12; i++) {
                ((ServerWorld)world).spawnParticle(ParticleTypes.POOF, getPosX() + RandomUtils.randomFloat(-0.25f, 0.25f), getPosY() + RandomUtils.randomFloat(-0.25f, 0.25f), getPosZ() + RandomUtils.randomFloat(-0.25f, 0.25f), 0, 0, 0,0,0.1);
            }
            ((ServerWorld)world).spawnParticle(ParticleTypes.FLASH, getPosX(), getPosY(), getPosZ(), 0, 0, 0,0,0);
        }
        playSound(FCSounds.BAKUDAN_EXPLODE.get(),1,RandomUtils.randomFloat(0.9f,1.1f));
        remove();
    }


    @Override
    protected void registerData() {
        dataManager.register(IS_RED,false);
    }

    public void setRed(){
        dataManager.set(IS_RED,true);
    }

    public boolean isRed(){
        return dataManager.get(IS_RED);
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

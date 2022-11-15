package org.astemir.forestcraft.common.entities.projectiles.throwable;


import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;


public class EntityLiquidElectritePotion extends PotionEntity {


    public EntityLiquidElectritePotion(World worldIn, LivingEntity livingEntityIn) {
        super(worldIn, livingEntityIn);
    }

    public EntityLiquidElectritePotion(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityLiquidElectritePotion(EntityType<? extends EntityLiquidElectritePotion> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
        setItem(FCItems.LIQUID_ELECTRITE_FLASK.getDefaultInstance());
    }


    @Override
    protected Item getDefaultItem() {
        return super.getDefaultItem();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        RayTraceResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onEntityHit((EntityRayTraceResult)result);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.func_230299_a_((BlockRayTraceResult)result);
        }
        if (!this.world.isRemote) {
            playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK,2,1);
            createElectroCloud();
            this.remove();
        }
    }


    private boolean isLingering() {
        return true;
    }

    private void createElectroCloud() {
        AreaEffectCloudEntity cloud = EntityType.AREA_EFFECT_CLOUD.create(world);
        cloud.setRadius(3f);
        cloud.setParticleData(FCParticles.ELECTRO.get());
        cloud.setDuration(800);
        cloud.setWaitTime(0);
        cloud.setRadiusOnUse(-0.001f);
        cloud.setRadiusPerTick(-0.001f);
        cloud.addEffect(new EffectInstance(FCEffects.ELECTROCUT.get(), 20, 0, false, false));
        cloud.moveToBlockPosAndAngles(getPosition().add(0, 0.1, 0), 0, 0);
        world.addEntity(cloud);
    }

    public static class EntitySkyFragmentProjectile extends ProjectileItemEntity {


        public EntitySkyFragmentProjectile(EntityType<? extends EntitySkyFragmentProjectile> p_i50159_1_, World p_i50159_2_) {
            super(p_i50159_1_, p_i50159_2_);
        }

        public EntitySkyFragmentProjectile(World worldIn, LivingEntity throwerIn) {
            super(FCEntities.SKY_FRAGMENT_PROJECTILE, throwerIn, worldIn);
        }

        public EntitySkyFragmentProjectile(World worldIn, double x, double y, double z) {
            super(FCEntities.SKY_FRAGMENT_PROJECTILE, x, y, z, worldIn);
        }

        @Override
        protected Item getDefaultItem() {
            return FCItems.SKY_FRAGMENT;
        }

        @Override
        public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
            Vector3d vector3d = (new Vector3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale(velocity);
            this.setMotion(vector3d);
            float f = MathHelper.sqrt(horizontalMag(vector3d));
            this.rotationYaw = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(vector3d.y, f) * (double)(180F / (float)Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }


        @OnlyIn(Dist.CLIENT)
        private IParticleData makeParticle() {
            return generateParticle();
        }

        private IParticleData generateParticle(){
            return new ItemParticleData(ParticleTypes.ITEM, FCItems.SKY_FRAGMENT.getDefaultInstance());
        }

        @Override
        protected float getGravityVelocity() {
            return 0.035F;
        }

        @Override
        public void tick() {
            super.tick();
            this.world.addParticle(ParticleTypes.END_ROD, this.getPosX()+ RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
        }


        @Override
        public IPacket<?> createSpawnPacket() {
            return NetworkHooks.getEntitySpawningPacket(this);
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void handleStatusUpdate(byte id) {
            if (id == 3) {
                IParticleData iparticledata = this.makeParticle();
                world.playSound(getPosX(),getPosY(),getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT,4,2,false);
                for (int i = 0; i < 16; ++i) {
                    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
                    this.world.addParticle(iparticledata, this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
                }
            }
        }


        @Override
        protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
            super.onEntityHit(p_213868_1_);
            Entity entity = p_213868_1_.getEntity();
            int i = 20;
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) i);
        }

        @Override
        protected void onImpact(RayTraceResult result) {
            super.onImpact(result);
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 3);
                if (!removed){
                    WorldUtils.dropItem(world,getPosition(), FCItems.SKY_FRAGMENT);
                }
                this.remove();
            }
        }
    }
}
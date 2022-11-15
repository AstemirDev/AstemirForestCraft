package org.astemir.forestcraft.common.entities.projectiles.other;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.api.math.RandomUtils;


public class EntityDirtProjectile extends ProjectileItemEntity {

    public int damage = 1;

    public EntityDirtProjectile(EntityType<? extends EntityDirtProjectile> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public EntityDirtProjectile(World worldIn, LivingEntity throwerIn) {
        super(FCEntities.DIRT_PROJECTILE, throwerIn, worldIn);
    }

    public EntityDirtProjectile(World worldIn, double x, double y, double z) {
        super(FCEntities.DIRT_PROJECTILE, x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return FCItems.DIRT_CHUNK;
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
        return new BlockParticleData(ParticleTypes.BLOCK, Blocks.DIRT.getDefaultState());
    }

    @Override
    protected float getGravityVelocity() {
        return 0.035F;
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(generateParticle(), this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
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
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(iparticledata, this.getPosX()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosY()+RandomUtils.randomFloat(-0.1f,0.1f), this.getPosZ()+RandomUtils.randomFloat(-0.1f,0.1f), 0.1D, 0.1D, 0.1D);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        super.onEntityHit(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        int i = damage;
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) i);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }
}
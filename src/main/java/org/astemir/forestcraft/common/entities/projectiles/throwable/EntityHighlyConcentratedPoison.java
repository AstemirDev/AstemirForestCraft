package org.astemir.forestcraft.common.entities.projectiles.throwable;


import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCParticles;


public class EntityHighlyConcentratedPoison extends PotionEntity {


    public EntityHighlyConcentratedPoison(World worldIn, LivingEntity livingEntityIn) {
        super(worldIn, livingEntityIn);
    }

    public EntityHighlyConcentratedPoison(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityHighlyConcentratedPoison(EntityType<? extends EntityHighlyConcentratedPoison> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
        setItem(FCItems.FIRE_LIQUID.getDefaultInstance());
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
        cloud.setParticleData(FCParticles.SPORE.get());
        cloud.setDuration(800);
        cloud.setWaitTime(0);
        cloud.setRadiusOnUse(-0.001f);
        cloud.setRadiusPerTick(-0.001f);
        cloud.addEffect(new EffectInstance(Effects.NAUSEA, 20, 3, false, false));
        cloud.addEffect(new EffectInstance(Effects.POISON, 20, 3, false, false));
        cloud.moveToBlockPosAndAngles(getPosition().add(0, 0.1, 0), 0, 0);
        world.addEntity(cloud);
    }

}
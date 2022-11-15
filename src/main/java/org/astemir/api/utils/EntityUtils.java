package org.astemir.api.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;

import java.util.function.Predicate;

import static org.astemir.api.math.MathUtils.rad;

public class EntityUtils {


    public static void setEntityRotationYaw(Entity entity, float yaw){
        entity.rotationYaw = yaw;
        entity.setRenderYawOffset(entity.rotationYaw);
    }

    public static void lookAt(Entity entity,Vector3 direction){
        Vector2 rot = direction.yawPitchDegrees();
        float rotX = -rot.x;
        float rotY = rot.y;
        EntityUtils.setEntityRotation(entity,rotX,rotY);
    }

    public static void setEntityRotationPitch(Entity entity, float pitch){
        entity.rotationPitch = pitch;
    }

    public static void setEntityRotation(Entity entity, float yaw, float pitch){
        setEntityRotationYaw(entity,yaw);
        setEntityRotationPitch(entity,pitch);
    }

    public static void setMotion(Entity entity,Vector3 velocity){
        entity.setMotion(velocity.getX(),velocity.getY(),velocity.getZ());
        entity.velocityChanged = true;
    }

    public static void addMotion(Entity entity,Vector3 velocity){
        setMotion(entity,motion(entity).add(velocity));
    }

    public static void setMotion(Entity entity,float x,float y,float z){
        entity.setMotion(x,y,z);
    }

    public static void setMotionX(Entity entity,float x){
        entity.setMotion(x,motion(entity).getY(),motion(entity).getZ());
    }

    public static void setMotionY(Entity entity,float y){
        entity.setMotion(motion(entity).getX(),y,motion(entity).getZ());
    }

    public static void setMotionZ(Entity entity,float z){
        entity.setMotion(motion(entity).getX(),motion(entity).getY(),z);
    }

    public static void addMotion(Entity entity,float x,float y,float z){
        setMotion(entity,motion(entity).add(new Vector3(x,y,z)));
    }

    public static Vector3 motion(Entity entity){
        return new Vector3(entity.getMotion().getX(),entity.getMotion().getY(),entity.getMotion().getZ());
    }

    public static Vector3 position(Entity entity){
        return new Vector3(entity.getPosX(),entity.getPosY(),entity.getPosZ());
    }

    public static ProjectileEntity shootProjectile(ProjectileEntity projectile,Entity entity,float pitch, float yaw, float newPitch, float speed, float spread) {
        ProjectileEntity proj = shootProjectileIgnoreMotion(projectile,entity,pitch,yaw,newPitch,speed,spread);
        Vector3d vector3d = entity.getMotion();
        proj.setMotion(proj.getMotion().add(vector3d.x, entity.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
        return proj;
    }


    public static ProjectileEntity shootProjectileIgnoreMotion(ProjectileEntity projectile,Entity entity,float pitch, float yaw, float newPitch, float speed, float spread) {
        float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float f1 = -MathHelper.sin((pitch + newPitch) * ((float)Math.PI / 180F));
        float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        projectile.shoot(f, f1, f2, speed, spread);
        projectile.setShooter(entity);
        projectile.setMotion(projectile.getMotion());
        return projectile;
    }

    public static BlockRayTraceResult rayTrace(World worldIn, Entity entity, RayTraceContext.BlockMode blockMode,RayTraceContext.FluidMode fluidMode,double maxDist) {
        Vector3d eyePos = entity.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-entity.rotationYaw * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-entity.rotationYaw * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-entity.rotationPitch * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-entity.rotationPitch * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = maxDist;
        Vector3d resVector = eyePos.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.rayTraceBlocks(new RayTraceContext(eyePos, resVector, blockMode, fluidMode, entity));
    }

    public static BlockRayTraceResult rayTrace(World worldIn, LivingEntity entity, RayTraceContext.BlockMode blockMode,RayTraceContext.FluidMode fluidMode) {
        double d0 = entity.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();;
        return rayTrace(worldIn,entity,blockMode,fluidMode,d0);
    }

    public static BlockRayTraceResult rayTrace(World worldIn, Entity entity,double maxDist) {
        return rayTrace(worldIn,entity, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE,maxDist);
    }

    public static BlockRayTraceResult rayTrace(World worldIn, LivingEntity entity) {
        double d0 = entity.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        return rayTrace(worldIn,entity, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE,d0);
    }

    public static BlockRayTraceResult rayTraceFluid(World worldIn, Entity entity,double maxDist) {
        return rayTrace(worldIn,entity, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY,maxDist);
    }

    public static BlockRayTraceResult rayTraceFluid(World worldIn, LivingEntity entity) {
        double d0 = entity.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        return rayTrace(worldIn,entity, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY,d0);
    }

    public static Entity rayTraceEntity(PlayerEntity entity, Predicate<Entity> predicate, int distance){
        Vector3d dir = EntityUtils.direction(entity);
        for (double i = 0; i < distance + 0.75D * 0.5D; i += 0.5D) {
            Vector3d pos = new Vector3d(entity.getPosX() + dir.getX() * i, entity.getPosYEye()-0.2 + dir.getY() * i, entity.getPosZ() + dir.getZ() * i);
            if (!entity.world.getBlockState(new BlockPos(pos)).getBlockState().isSolid()) {
                for (LivingEntity livingEntity : entity.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 0.1, pos.getY() - 0.1, pos.getZ() - 0.1, pos.getX() + 0.1, pos.getY() + 0.1, pos.getZ() + 0.1), (e) -> !e.getUniqueID().equals(entity.getUniqueID()))) {
                    if (predicate.test(livingEntity)) {
                        return livingEntity;
                    }
                }
            }else{
                break;
            }
        }
        return null;
    }

    public static boolean hasArmorFullSet(LivingEntity entity, Item helmet, Item chest, Item legs, Item feet){
        ItemStack equippedHelmet = helmet(entity);
        ItemStack equippedChestplate = chestplate(entity);
        ItemStack equippedLeggings = leggings(entity);
        ItemStack equippedBoots = boots(entity);
        if (!equippedHelmet.isEmpty() && !equippedChestplate.isEmpty() && !equippedLeggings.isEmpty() && !equippedBoots.isEmpty()){
            return equippedHelmet.getItem().equals(helmet) && equippedChestplate.getItem().equals(chest) && equippedLeggings.getItem().equals(legs) && equippedBoots.getItem().equals(feet);
        }
        return false;
    }

    public static boolean hasArmorFullSet(LivingEntity entity, Item[] armor){
        return hasArmorFullSet(entity,armor[0],armor[1],armor[2],armor[3]);
    }


    public static Vector3d direction(Entity entity){
        float rotationYaw = entity.rotationYaw, rotationPitch = entity.rotationPitch;
        float vx = -MathHelper.sin(rad(rotationYaw)) * MathHelper.cos(rad(rotationPitch));
        float vz = MathHelper.cos(rad(rotationYaw)) * MathHelper.cos(rad(rotationPitch));
        float vy = -MathHelper.sin(rad(rotationPitch));
        return new Vector3d(vx,vy,vz);
    }


    public static ItemStack helmet(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.HEAD);
    }

    public static ItemStack chestplate(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.CHEST);
    }

    public static ItemStack leggings(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.LEGS);
    }

    public static ItemStack boots(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.FEET);
    }

    public static ItemStack mainHand(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    }

    public static ItemStack offhand(LivingEntity entity){
        return entity.getItemStackFromSlot(EquipmentSlotType.OFFHAND);
    }

    public static boolean canSpawnAtPosition(World world,BlockPos spawnPos, EntityType type){
        AxisAlignedBB bb = new AxisAlignedBB(spawnPos).expand(type.getWidth(),type.getHeight(),type.getWidth());
        return BlockPos.getAllInBox(bb).filter((pos) -> world.getBlockState(pos).isSolid() || world.getFluidState(pos).isTagged(FluidTags.LAVA)).count() == 0;
    }


    public static boolean canSpawnAtPosition(IBlockReader world, BlockPos spawnPos, EntityType type){
        AxisAlignedBB bb = new AxisAlignedBB(spawnPos).expand(type.getWidth(),type.getHeight(),type.getWidth());
        return BlockPos.getAllInBox(bb).filter((pos) -> world.getBlockState(pos).isSolid() || world.getFluidState(pos).isTagged(FluidTags.LAVA)).count() == 0;
    }


    public static void breedAnimal(PlayerEntity player, AnimalEntity entity, Hand hand, ItemStack item) {
        int i = entity.getGrowingAge();
        if (entity.isChild()) {
            if (!entity.world.isRemote) {
                item.shrink(1);
            }
            entity.ageUp((int) ((float) (-i / 20) * 0.1F), true);
            if (entity.world.isRemote) {
                player.swingArm(hand);
            }
        } else if (i == 0 && entity.canFallInLove()) {
            if (!entity.world.isRemote) {
                item.shrink(1);
                entity.setInLove(player);
            }
            if (entity.world.isRemote) {
                player.swingArm(hand);
            }
        }
    }
}

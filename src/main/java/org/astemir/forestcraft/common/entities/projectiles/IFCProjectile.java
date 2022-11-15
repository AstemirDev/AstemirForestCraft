package org.astemir.forestcraft.common.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public interface IFCProjectile {

    void shoot(LivingEntity shooter,ItemStack shootingItem,float pitch, float yaw, float newPitch, float speed, float spread);

    Supplier<SoundEvent> getBreakSound();

    boolean canCollideWithBlock();

    int getPiercingCount();

    void setPiercingCount(int count);

    float getDefaultDamage();

    void setDefaultDamage(float damage);

    float getKnockback();

    void setKnockback(float knockback);

    float calculateDamage(LivingEntity entity);

    void onDie();

    void onHitEntity(Entity entity);

    void onDamageEntity(LivingEntity entity);

    void onHitBlock(BlockPos pos, Direction face,boolean inside);

    float fallSpeed();

    ItemStack getItemShooting();

    LivingEntity getShooter();
}

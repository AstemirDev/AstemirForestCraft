package org.astemir.forestcraft.registries.properties;

import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.sound.Sound3D;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.weapons.guns.FCGunProperties;
import org.astemir.forestcraft.registries.FCSounds;

public class FCGuns {

    public static final FCGunProperties DARK_MATTER = new FCGunProperties().
            ammo(()-> FCItems.BLACKHOLE_BULLET).
            minDamage(5).
            maxDamage(5).
            ticksToShot(1).
            firstShotDelay(8).
            projectileSpeed(4).
            projectileSpread(0.1f).
            chanceToNotConsumeAmmo(90);

    public static final FCGunProperties DAYBREAK = new FCGunProperties().
            maxDamage(15).
            minDamage(12).
            knockback(1).
            projectileSpeed(0.5f).
            firstShotDelay(1).
            ticksToShot(20).
            infiniteAmmo().
            shotSound(new Sound3D(FCSounds.DAYBREAK, SoundCategory.PLAYERS,0.5f,1,1.1f));

    public static final FCGunProperties DEMON_BUSTER = new FCGunProperties().
            ammo(()-> FCItems.DEMON_BULLET).
            minDamage(9).
            maxDamage(11).
            ticksToShot(3).
            firstShotDelay(1).
            projectileSpeed(4).
            chanceToNotConsumeAmmo(75).
            shotSound(new Sound3D(FCSounds.DEMON_BUSTER_USE,SoundCategory.PLAYERS,0.25f,1,1.1f));

    public static final FCGunProperties GHAST_CANNON = new FCGunProperties().
            ammo(()->Items.FIRE_CHARGE).
            minDamage(6).
            maxDamage(6).
            ticksToShot(20).
            shotSound(new Sound3D(()->SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.9f, 1.1f));


    public static final FCGunProperties SKY_SHOOTER = new FCGunProperties().
            ammo(()-> FCItems.SKY_BULLET).
            minDamage(6).
            maxDamage(8).
            ticksToShot(10).
            projectileSpeed(2).
            knockback(0.25f).
            shotSound(new Sound3D(FCSounds.SKY_SHOOTER_USE,SoundCategory.PLAYERS,1,0.9f,1.1f));


    public static final FCGunProperties FIRE_FURY = new FCGunProperties().
            ammo(()-> FCItems.MOLTEN_BULLET).
            minDamage(9).
            maxDamage(9).
            ticksToShot(10).
            projectileSpeed(2.5f).
            knockback(0.25f).
            shotSound(new Sound3D(FCSounds.BULLET_IMPACT,SoundCategory.PLAYERS,1,0.9f,1.1f));
}

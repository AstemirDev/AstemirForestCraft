package org.astemir.forestcraft.common.items.weapons.guns;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.astemir.api.common.sound.Sound3D;

import java.util.function.Supplier;

public class FCGunProperties {

    private float minDamage = 1;
    private float maxDamage = 1;
    private float knockback = 0;
    private float projectileSpeed = 4;
    private float projectileSpread = 0;
    private int ticksToShot = 20;
    private int firstShotDelay = 20;
    private int chanceToNotConsumeAmmo = 0;
    private boolean usingAmmo = true;
    private Supplier<Item> ammoItem = ()->Items.ARROW;
    private Sound3D shotSound;

    public FCGunProperties minDamage(float minDamage) {
        this.minDamage = minDamage;
        return this;
    }

    public FCGunProperties maxDamage(float maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }

    public FCGunProperties ammo(Supplier<Item> ammoItem) {
        this.ammoItem = ammoItem;
        usingAmmo = true;
        return this;
    }

    public FCGunProperties infiniteAmmo() {
        this.usingAmmo = false;
        return this;
    }

    public FCGunProperties knockback(float knockback) {
        this.knockback = knockback;
        return this;
    }

    public FCGunProperties chanceToNotConsumeAmmo(int chanceToNotConsumeAmmo) {
        this.chanceToNotConsumeAmmo = chanceToNotConsumeAmmo;
        return this;
    }

    public FCGunProperties ticksToShot(int ticksToShot) {
        this.ticksToShot = ticksToShot;
        this.firstShotDelay = ticksToShot;
        return this;
    }

    public FCGunProperties shotSound(Sound3D shotSound) {
        this.shotSound = shotSound;
        return this;
    }

    public FCGunProperties firstShotDelay(int firstShotDelay) {
        this.firstShotDelay = firstShotDelay;
        return this;
    }

    public FCGunProperties projectileSpread(float projectileSpread) {
        this.projectileSpread = projectileSpread;
        return this;
    }

    public FCGunProperties projectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
        return this;
    }

    public float projectileSpread() {
        return projectileSpread;
    }


    public float projectileSpeed() {
        return projectileSpeed;
    }


    public Supplier<Item> ammo() {
        return ammoItem;
    }

    public Sound3D shotSound() {
        return shotSound;
    }

    public float minDamage() {
        return minDamage;
    }

    public float maxDamage() {
        return maxDamage;
    }

    public int firstShotDelay() {
        return firstShotDelay;
    }

    public boolean usingAmmo() {
        return usingAmmo;
    }

    public float knockback() {
        return knockback;
    }

    public int ticksToShot() {
        return ticksToShot;
    }

    public int chanceToNotConsumeAmmo() {
        return chanceToNotConsumeAmmo;
    }
}

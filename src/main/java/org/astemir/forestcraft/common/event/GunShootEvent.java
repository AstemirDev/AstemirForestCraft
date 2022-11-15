package org.astemir.forestcraft.common.event;

import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;

public class GunShootEvent {

    private FCBulletEntity bullet;
    private float damage = 0;
    private float speed = 0;
    private float knockback = 0;
    private float spread = 0;
    private boolean canceled = false;

    public GunShootEvent(FCBulletEntity bullet, float damage, float speed, float knockback, float spread) {
        this.bullet = bullet;
        this.damage = damage;
        this.speed = speed;
        this.knockback = knockback;
        this.spread = spread;
    }

    public FCBulletEntity getBullet() {
        return bullet;
    }

    public void setBullet(FCBulletEntity bullet) {
        this.bullet = bullet;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getKnockback() {
        return knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    public float getSpread() {
        return spread;
    }

    public void setSpread(float spread) {
        this.spread = spread;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        this.canceled = true;
    }
}

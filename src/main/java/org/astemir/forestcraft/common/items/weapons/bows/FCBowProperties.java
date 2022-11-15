package org.astemir.forestcraft.common.items.weapons.bows;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.sound.Sound3D;
import org.astemir.forestcraft.common.items.weapons.guns.FCGunProperties;

import java.util.function.Supplier;

public class FCBowProperties {

    private float damage = 2.0f;
    private float knockback = 1f;
    private float arrowSpeed = 1.5f;
    private Supplier<Item> ammoItem = ()->Items.ARROW;
    private boolean usingDefaultArrows = true;
    private Sound3D shotSound = new Sound3D(()-> SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS);


    public FCBowProperties damage(float damage) {
        this.damage = damage;
        return this;
    }

    public FCBowProperties knockback(float knockback) {
        this.knockback = knockback;
        return this;
    }

    public FCBowProperties ammo(Supplier<Item> ammoItem) {
        this.ammoItem = ammoItem;
        usingDefaultArrows = false;
        return this;
    }

    public FCBowProperties shotSound(Sound3D shotSound) {
        this.shotSound = shotSound;
        return this;
    }

    public FCBowProperties arrowSpeed(float arrowSpeed) {
        this.arrowSpeed = arrowSpeed;
        return this;
    }

    public boolean isUsingArrows() {
        return usingDefaultArrows;
    }

    public float arrowSpeed() {
        return arrowSpeed;
    }

    public Supplier<Item> ammo() {
        return ammoItem;
    }

    public Sound3D shotSound() {
        return shotSound;
    }

    public float damage() {
        return damage;
    }

    public float getDamage() {
        return damage;
    }
}

package org.astemir.api.common.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfiguration;
import org.astemir.forestcraft.configuration.FCConfigurationValues;

public class EntityStats {

    private float health = -1;
    private float damage = -1;
    private float speed = -1;
    private int armor = -1;


    public float health() {
        return health;
    }

    public float damage() {
        return damage;
    }

    public float speed() { return speed; }

    public int armor() { return armor; }

    public void apply(LivingEntity entity){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.DIFFICULTY_SCALE)) {
            if (health != -1) {
                entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
                entity.setHealth(health);
            }
            if (damage != -1) {
                entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage);
            }
            if (armor != -1) {
                entity.getAttribute(Attributes.ARMOR).setBaseValue(armor);
            }
            if (speed != -1) {
                entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
            }
        }
    }

    public EntityStats health(float health) {
        this.health = health;
        return this;
    }


    public EntityStats damage(float damage) {
        this.damage = damage;
        return this;
    }

    public EntityStats speed(float speed) {
        this.speed = speed;
        return this;
    }

    public EntityStats armor(int armor) {
        this.armor = armor;
        return this;
    }
}

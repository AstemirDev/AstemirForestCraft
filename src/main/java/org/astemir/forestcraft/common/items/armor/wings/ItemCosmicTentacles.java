package org.astemir.forestcraft.common.items.armor.wings;


import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemCosmicTentacles extends FCWings {

    public ItemCosmicTentacles() {
        super("forestcraft:cosmic_tentacles", FCArmorTierNew.CHICKEN_WINGS,-2, 0, 0);
        setArmorTexture("forestcraft:textures/armor/cosmic_tentacles.png");
        rarity(FCRarity.LEGENDARY);
        maxDamage(10000);
    }

    @Override
    public float getSpeedX(){
        return 0.25f;
    }

    @Override
    public float getSpeedZ(){
        return 0.25f;
    }

    @Override
    public float getSlowFallingSpeed() {
        return -0.1f;
    }

    @Override
    public float getFlyingSpeed(){
        return 1f;
    }

    @Override
    public int getFlyingPower(){
        return 2500;
    }
}

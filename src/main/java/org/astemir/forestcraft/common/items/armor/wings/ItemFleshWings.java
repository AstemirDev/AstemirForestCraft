package org.astemir.forestcraft.common.items.armor.wings;


import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemFleshWings extends FCWings {

    public ItemFleshWings() {
        super("forestcraft:flesh_wings", FCArmorTierNew.ETERNAL_HUNGER,-7, -1, 0);
        setArmorTexture("forestcraft:textures/armor/flesh_wings.png");
        rarity(FCRarity.MYTHICAL);
        maxDamage(5000);
    }


    @Override
    public float getSpeedX(){
        return 0.125f;
    }

    @Override
    public float getSpeedZ(){
        return 0.125f;
    }

    @Override
    public float getSlowFallingSpeed() {
        return -0.25f;
    }

    @Override
    public float getFlyingSpeed(){
        return 0.75f;
    }

    @Override
    public int getFlyingPower(){
        return 1500;
    }
}

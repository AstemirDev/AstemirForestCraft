package org.astemir.forestcraft.common.items.armor.wings;


import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemScaleWings extends FCWings {

    public ItemScaleWings() {
        super("forestcraft:scale_wings", FCArmorTierNew.CHICKEN_WINGS,-3,0,0);
        setArmorTexture("forestcraft:textures/armor/scale_wings.png");
        maxDamage(300);
    }

    public float getSpeedX(){
        return 0.05f;
    }

    public float getSpeedZ(){
        return 0.05f;
    }

    @Override
    public float getSlowFallingSpeed() {
        return -0.5f;
    }

    public float getFlyingSpeed(){
        return 0.25f;
    }

    public int getFlyingPower(){
        return 650;
    }


}

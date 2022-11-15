package org.astemir.forestcraft.common.items.armor.wings;


import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemAngelWings extends FCWings {

    public ItemAngelWings() {
        super("forestcraft:angel_wings", FCArmorTierNew.CHICKEN_WINGS,-3,0,0);
        setArmorTexture("forestcraft:textures/armor/angel_wings.png");
        maxDamage(500);
    }


    public float getSpeedX(){
        return 0.1f;
    }

    public float getSpeedZ(){
        return 0.1f;
    }

    @Override
    public float getSlowFallingSpeed() {
        return -0.5f;
    }
    public float getFlyingSpeed(){
        return 0.5f;
    }

    public int getFlyingPower(){
        return 750;
    }


}

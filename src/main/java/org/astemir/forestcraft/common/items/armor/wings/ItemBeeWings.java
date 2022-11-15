package org.astemir.forestcraft.common.items.armor.wings;


import net.minecraft.item.Rarity;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemBeeWings extends FCWings {

    public ItemBeeWings() {
        super("forestcraft:bee_wings", FCArmorTierNew.CHICKEN_WINGS,-3,0,0);
        setArmorTexture("forestcraft:textures/armor/bee_wings.png");
        rarity(Rarity.EPIC);
        maxDamage(800);
    }


    public float getSpeedX(){
        return 0.125f;
    }

    public float getSpeedZ(){
        return 0.125f;
    }

    @Override
    public float getSlowFallingSpeed() {
        return -0.5f;
    }

    public float getFlyingSpeed(){
        return 0.5f;
    }

    public int getFlyingPower(){
        return 1000;
    }


}

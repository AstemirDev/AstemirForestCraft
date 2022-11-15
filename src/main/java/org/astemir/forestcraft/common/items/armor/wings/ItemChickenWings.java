package org.astemir.forestcraft.common.items.armor.wings;


import net.minecraft.item.Rarity;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;
import org.astemir.forestcraft.common.items.constructors.FCWings;

public class ItemChickenWings extends FCWings {

    public ItemChickenWings() {
        super("forestcraft:chicken_wings", FCArmorTierNew.CHICKEN_WINGS,-3,0,0);
        setArmorTexture("forestcraft:textures/armor/chicken_wings.png");
        rarity(Rarity.UNCOMMON);
        maxDamage(60);
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
        return 0.3f;
    }

    public int getFlyingPower(){
        return 250;
    }


}

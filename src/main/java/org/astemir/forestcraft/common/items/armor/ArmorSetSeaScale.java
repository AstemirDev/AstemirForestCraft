package org.astemir.forestcraft.common.items.armor;

import org.astemir.api.common.item.AArmorSet;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ArmorSetSeaScale extends AArmorSet {


    public ArmorSetSeaScale() {
        super(FCArmorTier.SEA_SCALE);
        mainTexture("forestcraft:textures/armor/sea_scale_armor_0.png");
        subTexture("forestcraft:textures/armor/sea_scale_armor_1.png");
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:sea_scale_helmet",0,0,0);
        chestplate("forestcraft:sea_scale_chestplate",0,0,0);
        leggings("forestcraft:sea_scale_leggings",0,0,0);
        boots("forestcraft:sea_scale_boots",0,0,0);
    }

}

package org.astemir.forestcraft.common.items.armor;

import org.astemir.api.common.item.AArmorSet;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ArmorSetAquamarine extends AArmorSet {


    public ArmorSetAquamarine() {
        super(FCArmorTier.AQUAMARINE);
        mainTexture( "forestcraft:textures/armor/aquamarine_armor_0.png");
        subTexture("forestcraft:textures/armor/aquamarine_armor_1.png");
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:aquamarine_helmet",0,0,0);
        chestplate("forestcraft:aquamarine_chestplate",0,0,0);
        leggings("forestcraft:aquamarine_leggings",0,0,0);
        boots("forestcraft:aquamarine_boots",0,0,0);
    }
}

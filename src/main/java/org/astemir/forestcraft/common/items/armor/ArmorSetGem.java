package org.astemir.forestcraft.common.items.armor;

import org.astemir.api.common.item.AArmorSet;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ArmorSetGem extends AArmorSet {


    public ArmorSetGem() {
        super(FCArmorTier.GEM);
        mainTexture( "forestcraft:textures/armor/gem_armor_0.png");
        subTexture("forestcraft:textures/armor/gem_armor_1.png");
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:gem_helmet",0,0,0);
        chestplate("forestcraft:gem_chestplate",0,0,0);
        leggings("forestcraft:gem_leggings",0,0,0);
        boots("forestcraft:gem_boots",0,0,0);
    }
}

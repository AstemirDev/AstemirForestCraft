package org.astemir.forestcraft.common.items.equipment;

import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;

public class EquipmentGem extends AWeaponToolSet {

    public EquipmentGem() {
        super(FCItemTier.GEM);
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:gem_sword",3, -2.4f);
        pickaxe("forestcraft:gem_pickaxe",1, -2.8f);
        axe("forestcraft:gem_axe",5, -3f);
        shovel("forestcraft:gem_shovel",1.5f, -3f);
        hoe("forestcraft:gem_hoe",-3, -1f);
    }
}

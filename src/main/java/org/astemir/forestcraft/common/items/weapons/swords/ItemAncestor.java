package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemAncestor extends AItemSword {

    public ItemAncestor() {
        super("forestcraft:ancestor",FCItemTier.ANCIENT_RUNESTONE, 15, -3f);
        rarity(Rarity.RARE);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
        maxDamage(2000);
    }

}

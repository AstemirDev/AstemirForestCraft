package org.astemir.forestcraft.common.items.shields;

import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import org.astemir.api.common.item.AItemTier;
import org.astemir.api.common.item.AShieldItem;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemArchaicShield extends AShieldItem {

    public ItemArchaicShield() {
        super("forestcraft:archaic_shield");
        maxDamage(836);
        rarity(Rarity.UNCOMMON);
        repairMaterial(AItemTier.repairMaterialFrom(()-> Ingredient.fromItems(FCBlocks.ARCHAIC_STONE.asItem())));
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SHIELDS);
        setupISTER(ForestCraft.PROXY::setupISTER);
    }
}

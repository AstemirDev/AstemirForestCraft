package org.astemir.api.loot;

import net.minecraft.item.ItemStack;

public class ItemDropPool extends DropPool<ItemDrop, ItemStack> {

    @Override
    public ItemDropPool add(double chance, ItemDrop element) {
        return (ItemDropPool)super.add(chance, element);
    }

    @Override
    public ItemDropPool canHasNoResult() {
        return (ItemDropPool)super.canHasNoResult();
    }

    @Override
    public ItemDropPool build() {
        return (ItemDropPool)super.build();
    }
}

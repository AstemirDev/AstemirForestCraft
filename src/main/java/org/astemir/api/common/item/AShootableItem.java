package org.astemir.api.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import org.astemir.api.common.item.vanilla.ModShootableItem;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class AShootableItem extends AItem{

    public static final Predicate<ItemStack> ARROWS = (item) -> item.getItem().isIn(ItemTags.ARROWS);

    private Predicate<ItemStack> ammoPredicate = ARROWS;

    public AShootableItem(String registryName) {
        super(registryName);
    }

    public AShootableItem ammo(Predicate<ItemStack> ammoPredicate) {
        this.ammoPredicate = ammoPredicate;
        return this;
    }

    @Override
    public Item build(Item.Properties properties) {
        ModShootableItem shootableItem = new ModShootableItem(properties).itemConstructor(this);
        return shootableItem;
    }

    public int getUseTime(ItemStack stack,int count){
        return getUseDuration(stack)-count;
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    public Predicate<ItemStack> getInventoryAmmoPredicate(){return ammoPredicate;}
}

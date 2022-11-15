package org.astemir.api.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;

public class ItemUtils {

    public static <T extends AItem> T getItemConstructor(Item item){
        if (item instanceof IModItem){
            return (T) ((IModItem)item).itemConstructor();
        }
        return null;
    }

    public static <T extends AItem> T getItemConstructor(ItemStack item){
        return getItemConstructor(item.getItem());
    }

    public static boolean isModItem(Item item){
        return getItemConstructor(item) != null;
    }

    public static boolean isModItem(ItemStack stack){
        return getItemConstructor(stack) != null;
    }

    public static ItemStack getPotionItemStack(Potion potion){
        ItemStack itemStack = new ItemStack(Items.POTION);
        itemStack = PotionUtils.addPotionToItemStack(itemStack, potion);
        return itemStack;
    }

    public static ItemStack count(Item item, int count){
        ItemStack result = item.getDefaultInstance();
        result.setCount(count);
        return result;
    }
}

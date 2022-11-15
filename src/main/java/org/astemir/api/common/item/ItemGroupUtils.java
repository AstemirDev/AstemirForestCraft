package org.astemir.api.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.function.Supplier;

public class ItemGroupUtils {

    public static ItemGroup newGroup(String modId,String tabName,Supplier<ItemStack> icon,GroupSorting sorting){
        return new ItemGroup(modId+"."+tabName) {
            @Override
            public ItemStack createIcon() {
                return icon.get();
            }

            @Override
            public void fill(NonNullList<ItemStack> items) {
                super.fill(items);
                items.sort((a, b) -> {
                    int priorA = (a.getItem() instanceof IModItem) ? ((IModItem<?>) a.getItem()).getSortPriority() : 1;
                    int priorB = (b.getItem() instanceof IModItem) ? ((IModItem<?>) b.getItem()).getSortPriority() : 1;
                    return priorB - priorA;
                });
                if (sorting != null){
                    sorting.sort(items);
                }
            }
        };
    }

    public static ItemGroup newGroup(String modId,String tabName,Supplier<ItemStack> icon){
        return newGroup(modId,tabName,icon,null);
    }

    @FunctionalInterface
    public static interface GroupSorting{
        public abstract void sort(NonNullList<ItemStack> items);
    }
}

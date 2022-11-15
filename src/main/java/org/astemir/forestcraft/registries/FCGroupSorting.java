package org.astemir.forestcraft.registries;

import net.minecraft.item.ItemStack;
import org.astemir.api.common.item.*;
import org.astemir.forestcraft.common.items.constructors.FCBow;
import org.astemir.forestcraft.common.items.constructors.FCGun;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.common.items.constructors.FCWings;

import java.util.Comparator;

public class FCGroupSorting {


    public static final ItemGroupUtils.GroupSorting BLOCKS = (items)-> items.sort((a, b)-> Comparator.comparing((block)->(((ItemStack)block).getItem()).getTranslationKey()).compare(b,a));

    public static final ItemGroupUtils.GroupSorting MISC = (items)-> items.sort((a, b)->{
        double priorA = 1;
        double priorB = 1;
        if (ItemUtils.getItemConstructor(a) != null && ItemUtils.getItemConstructor(b) != null){
            if (ItemUtils.getItemConstructor(a) instanceof AItem && ItemUtils.getItemConstructor(b) instanceof AItem){
                return Comparator.comparing(AItem::getGroupPriority).thenComparing(AItem::getDefaultRarity).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }
        }
        return (int) (priorB-priorA);
    });

    public static final ItemGroupUtils.GroupSorting TOOLS = (items)-> items.sort((a, b)->{
        double priorA = 1;
        double priorB = 1;
        if (ItemUtils.getItemConstructor(a) != null && ItemUtils.getItemConstructor(b) != null){
            if (ItemUtils.getItemConstructor(a) instanceof FCScytheItem && ItemUtils.getItemConstructor(b) instanceof FCScytheItem){
                return Comparator.comparing(FCScytheItem::getGroupPriority).thenComparing(FCScytheItem::getScytheEfficiency).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }else
            if (ItemUtils.getItemConstructor(a) instanceof AItemTool && ItemUtils.getItemConstructor(b) instanceof AItemTool){
                return Comparator.comparing(AItemTool::getGroupPriority).thenComparing(AItemTool::getHarvestLevel).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }
        }
        return (int) (priorB-priorA);
    });

    public static final ItemGroupUtils.GroupSorting WEAPONS = (items)-> items.sort((a, b)->{
        double priorA = 1;
        double priorB = 1;
        if (ItemUtils.getItemConstructor(a) != null && ItemUtils.getItemConstructor(b) != null){
            if (ItemUtils.getItemConstructor(a) instanceof AItemSword && ItemUtils.getItemConstructor(b) instanceof AItemSword){
                return Comparator.comparing(AItemSword::getGroupPriority).thenComparing(AItemSword::getAttackDamage).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }else
            if (ItemUtils.getItemConstructor(a) instanceof AItemArmor && ItemUtils.getItemConstructor(b) instanceof AItemArmor){
                if (ItemUtils.getItemConstructor(a) instanceof FCWings && ItemUtils.getItemConstructor(b) instanceof FCWings) {
                    return Comparator.comparing(FCWings::getGroupPriority).thenComparing(FCWings::getFlyingPower).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
                }else {
                    return Comparator.comparing(AItemArmor::getGroupPriority).thenComparing(AItemArmor::getSlotType).thenComparing(AItemArmor::getFullDamageReduction).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
                }
            }else
            if (ItemUtils.getItemConstructor(a) instanceof FCGun && ItemUtils.getItemConstructor(b) instanceof FCGun){
                return Comparator.comparing(FCGun::getGroupPriority).thenComparing(FCGun::getSpeed).thenComparing(FCGun::getAverageDamage).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }else
            if (ItemUtils.getItemConstructor(a) instanceof FCBow && ItemUtils.getItemConstructor(b) instanceof FCBow){
                return Comparator.comparing(FCBow::getGroupPriority).thenComparing(FCBow::getDamage).compare(ItemUtils.getItemConstructor(b), ItemUtils.getItemConstructor(a));
            }
        }
        return (int) (priorB-priorA);
    });

    public static final ItemGroupUtils.GroupSorting FOOD = (items)-> items.sort((a, b)->{
        double priorA = (a.getItem().isFood()) ? a.getItem().getFood().getHealing()+a.getItem().getFood().getSaturation() : 1;
        double priorB = (b.getItem().isFood()) ? b.getItem().getFood().getHealing()+b.getItem().getFood().getSaturation() : 1;
        return (int) (priorB-priorA);
    });
}

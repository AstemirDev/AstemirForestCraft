package org.astemir.forestcraft.common.items.equipment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;


public class EquipmentJewelWart extends AWeaponToolSet {

    public EquipmentJewelWart() {
        super(FCItemTier.JEWEL_WART);
        lore(TextUtils.effectTooltip(()->Effects.HASTE,1,2,false));
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:jewel_wart_sword",2, -2f);
        pickaxe("forestcraft:jewel_wart_pickaxe",1, -2.8f);
        axe("forestcraft:jewel_wart_axe",5, -3f);
        shovel("forestcraft:jewel_wart_shovel",1.5f, -3f);
        hoe("forestcraft:jewel_wart_hoe",-3, -2.4f);
    }


    @Override
    public void onSwing(AItem item, ItemStack stack, LivingEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.HASTE,5,1,false,false));
    }
}

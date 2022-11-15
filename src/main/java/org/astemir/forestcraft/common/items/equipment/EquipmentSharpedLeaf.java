package org.astemir.forestcraft.common.items.equipment;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;

public class EquipmentSharpedLeaf extends AWeaponToolSet {

    public EquipmentSharpedLeaf() {
        super(FCItemTier.SHARPED_LEAF);
        lore(TextUtils.effectTooltip(FCEffects.BROKEN_ARMOR,4,2,false));
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("sharped_leaf_sword",3, -2.4f);
        pickaxe("sharped_leaf_pickaxe",1, -2.8f);
        axe("sharped_leaf_axe",5, -3f);
        shovel("sharped_leaf_shovel",1.5f, -3f);
        hoe("sharped_leaf_hoe",-3, -2.4f);
    }

    @Override
    public void onHit(AItem item, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),80,3));
    }
}

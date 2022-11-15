package org.astemir.forestcraft.common.items.equipment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;


public class EquipmentVita extends AWeaponToolSet {

    public EquipmentVita() {
        super(FCItemTier.VITA);
        lore(TextUtils.effectTooltip(()->Effects.REGENERATION,3,1,false));
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:vita_sword",2.5f, -2.25f);
        pickaxe("forestcraft:vita_pickaxe",1.5f, -2.95f);
        axe("forestcraft:vita_axe",5.5f, -3.25f);
        shovel("forestcraft:vita_shovel",2f, -3.25f);
        hoe("forestcraft:vita_hoe",-3.5f, -2.65f);
    }


    @Override
    public void onSwing(AItem item, ItemStack stack, LivingEntity entity) {
        if (RandomUtils.doWithChance(10)) {
            entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 1, false, false));
        }
    }
}

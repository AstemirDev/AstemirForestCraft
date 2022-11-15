package org.astemir.forestcraft.common.items.equipment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.utils.TextUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;


public class EquipmentBloom extends AWeaponToolSet {

    public EquipmentBloom() {
        super(FCItemTier.BLOOMING);
        lore(TextUtils.translate(FCStrings.BLOOM,TextFormatting.GRAY),TextUtils.effectTooltip(()->Effects.REGENERATION,4,0,false));
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:blooming_sword",2, -2.5f);
        pickaxe("forestcraft:blooming_pickaxe",1, -3f);
        axe("forestcraft:blooming_axe",5, -3.2f);
        shovel("forestcraft:blooming_shovel",1.5f, -3.2f);
        hoe("forestcraft:blooming_hoe",-3, -2.6f);
    }


    @Override
    public void onSwing(AItem item, ItemStack stack, LivingEntity entity) {
        if (!WorldUtils.isNight(entity.world) && entity.world.getDimensionKey() == World.OVERWORLD){
            entity.addPotionEffect(new EffectInstance(Effects.REGENERATION,80,0,false,false));
        }
    }
}

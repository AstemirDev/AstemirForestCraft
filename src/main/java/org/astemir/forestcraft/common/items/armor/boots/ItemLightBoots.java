package org.astemir.forestcraft.common.items.armor.boots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemLightBoots extends AItemArmor {


    public ItemLightBoots() {
        super("forestcraft:light_boots",ArmorMaterial.LEATHER,EquipmentSlotType.FEET,0,0);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        setArmorTexture("forestcraft:textures/armor/light_boots.png");
        lore(TextUtils.translate(FCStrings.LIGHT_BOOTS,TextFormatting.GRAY));
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,5,0,false,false));
    }
}

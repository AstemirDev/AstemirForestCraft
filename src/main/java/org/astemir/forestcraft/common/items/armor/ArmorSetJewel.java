package org.astemir.forestcraft.common.items.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AArmorSet;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;

public class ArmorSetJewel extends AArmorSet {


    public ArmorSetJewel() {
        super(FCArmorTier.JEWEL_WART);
        mainTexture("forestcraft:textures/armor/jewel_wart_armor_0.png");
        subTexture("forestcraft:textures/armor/jewel_wart_armor_1.png");
        lore(TextUtils.translate(FCStrings.SET_BONUS, TextFormatting.GRAY), TextUtils.effectTooltip(()->Effects.SPEED,1,1,false));
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:jewel_wart_helmet",0,0,0);
        chestplate("forestcraft:jewel_wart_chestplate",0,0,0);
        leggings("forestcraft:jewel_wart_leggings",0,0,0);
        boots("forestcraft:jewel_wart_boots",0,0,0);
    }

    @Override
    public void onTickWhileArmorEquipped(AItem item, ItemStack itemStack, PlayerEntity entity) {
        if (EntityUtils.hasArmorFullSet(entity, FCItems.JEWEL_WART_ARMOR)){
            entity.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 1, false, false));
        }
    }
}

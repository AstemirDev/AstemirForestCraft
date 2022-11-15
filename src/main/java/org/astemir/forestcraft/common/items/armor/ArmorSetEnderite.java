package org.astemir.forestcraft.common.items.armor;

import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AArmorSet;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ArmorSetEnderite extends AArmorSet {


    public ArmorSetEnderite() {
        super(FCArmorTier.ENDERITE);
        mainTexture("forestcraft:textures/armor/enderite_armor_0.png");
        subTexture("forestcraft:textures/armor/enderite_armor_1.png");
        lore(TextUtils.translate(FCStrings.SET_BONUS, TextFormatting.GRAY), TextUtils.translate(FCStrings.ENDERITE_SET_BONUS,TextFormatting.LIGHT_PURPLE));
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:enderite_helmet",0,0,0);
        chestplate("forestcraft:enderite_chestplate",0,0,0);
        leggings("forestcraft:enderite_leggings",0,0,0);
        boots("forestcraft:enderite_boots",0,0,0);
    }


}

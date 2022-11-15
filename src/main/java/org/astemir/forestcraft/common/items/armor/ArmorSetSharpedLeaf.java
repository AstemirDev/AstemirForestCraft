package org.astemir.forestcraft.common.items.armor;

import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AArmorSet;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ArmorSetSharpedLeaf extends AArmorSet {


    public ArmorSetSharpedLeaf() {
        super(FCArmorTier.SHARPED_LEAF);
        mainTexture("forestcraft:textures/armor/sharped_leaf_armor_0.png");
        subTexture("forestcraft:textures/armor/sharped_leaf_armor_1.png");
        lore(TextUtils.translate(FCStrings.SET_BONUS, TextFormatting.GRAY), TextUtils.effectTooltip(FCEffects.BROKEN_ARMOR,2,2,false));
        groupPriority(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);

        helmet("forestcraft:sharped_leaf_helmet",0,0,0);
        chestplate("forestcraft:sharped_leaf_chestplate",0,0,0);
        leggings("forestcraft:sharped_leaf_leggings",0,0,0);
        boots("forestcraft:sharped_leaf_boots",0,0,0);
    }


}

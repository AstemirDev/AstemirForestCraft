package org.astemir.forestcraft.common.items.summon;


import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.items.constructors.FCSummonItem;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemEquinoxTotem extends FCSummonItem {

    public ItemEquinoxTotem() {
        super("forestcraft:equinox_totem", 20,()-> FCEntities.ARCHAIC_SENTINEL, FCSounds.ANCIENT_RUNESTONE_HERO_EQUINOX);
        rarity(Rarity.RARE);
        lore(TextUtils.translate(FCStrings.EQUINOX_TOTEM).mergeStyle(TextFormatting.GRAY));
    }
}

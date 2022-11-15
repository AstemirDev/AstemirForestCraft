package org.astemir.forestcraft.common.items.summon;

import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.items.constructors.FCSummonItem;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemDemonFood extends FCSummonItem {

    public ItemDemonFood() {
        super("forestcraft:demon_food",20,()-> FCEntities.NETHER_SCOURGE,FCSounds.NETHER_SCOURGE_SCREAM);
        rarity(Rarity.RARE);
        lore(TextUtils.translate(FCStrings.DEMON_FOOD, TextFormatting.RED));
    }

}

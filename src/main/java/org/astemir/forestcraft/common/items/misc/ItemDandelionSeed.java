package org.astemir.forestcraft.common.items.misc;

import net.minecraft.block.Blocks;
import org.astemir.api.common.item.AItemBlockNamedItem;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemDandelionSeed extends AItemBlockNamedItem {

    public ItemDandelionSeed() {
        super("forestcraft:dandelion_seed", Blocks.DANDELION);
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        maxStack(64);
    }
}

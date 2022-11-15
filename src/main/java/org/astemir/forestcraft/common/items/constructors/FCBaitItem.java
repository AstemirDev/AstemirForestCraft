package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.IBait;

public class FCBaitItem extends AItem implements IBait {

    private int baitPower;

    public FCBaitItem(String registryName,int baitPower) {
        super(registryName);
        this.baitPower = baitPower;
        maxStack(64);
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        lore(TextUtils.translate(FCStrings.BAIT,TextFormatting.GRAY).append(TextUtils.text(" "+baitPower+"%",TextFormatting.GRAY)));
    }


    @Override
    public void setBaitPower(int power) {
        this.baitPower = power;
    }

    public int getBaitPower() {
        return baitPower;
    }
}

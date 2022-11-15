package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.IBait;

import java.util.function.Supplier;

public class FCInsectBaitItem<T extends Entity> extends FCInsectItem<T> implements IBait {

    private int baitPower;

    public FCInsectBaitItem(String name, Supplier<EntityType<T>> type, int baitPower) {
        super(name, type);
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

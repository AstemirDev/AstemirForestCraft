package org.astemir.forestcraft.common.items.insect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.item.ItemStack;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


public class EndermiteItem extends FCInsectItem<EndermiteEntity> {

    public EndermiteItem() {
        super("forestcraft:endermite",()->EntityType.ENDERMITE);
    }

    @Override
    public EndermiteEntity spawnEntity(ItemStack item, EndermiteEntity entity) {
        return super.spawnEntity(item, entity);
    }

    @Override
    public ItemStack caughtEntity(EndermiteEntity entity) {
        ItemStack stack = FCItems.BEE.getDefaultInstance();
        return stack;
    }
}

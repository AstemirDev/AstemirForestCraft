package org.astemir.forestcraft.common.items.insect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.item.ItemStack;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


public class SilverfishItem extends FCInsectItem<SilverfishEntity> {

    public SilverfishItem() {
        super("forestcraft:silverfish",()->EntityType.SILVERFISH);
    }

    @Override
    public SilverfishEntity spawnEntity(ItemStack item, SilverfishEntity entity) {
        return super.spawnEntity(item, entity);
    }

    @Override
    public ItemStack caughtEntity(SilverfishEntity entity) {
        ItemStack stack = FCItems.SILVERFISH.getDefaultInstance();
        return stack;
    }
}

package org.astemir.forestcraft.common.items.insect;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.animals.EntityKillerBee;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


public class KillerBeeItem extends FCInsectItem<EntityKillerBee> {

    public KillerBeeItem() {
        super("forestcraft:killer_bee",()-> FCEntities.KILLER_BEE);
    }

    @Override
    public EntityKillerBee spawnEntity(ItemStack item, EntityKillerBee entity) {
        CompoundNBT tag = item.getShareTag();
        if (tag != null) {
            if (tag.contains("Age")) {
                entity.setGrowingAge(tag.getInt("Age"));
            }
            if (tag.contains("AngerTicks")){
                entity.setAngerTime(tag.getInt("AngerTicks"));
            }
        }
        return super.spawnEntity(item, entity);
    }

    @Override
    public ItemStack caughtEntity(EntityKillerBee entity) {
        ItemStack stack = FCItems.KILLER_BEE.getDefaultInstance();
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Age", entity.getGrowingAge());
        tag.putInt("AngerTicks", entity.getAngerTime());
        stack.setTag(tag);
        return stack;
    }
}

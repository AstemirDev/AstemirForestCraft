package org.astemir.forestcraft.common.items.insect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


public class BeeItem extends FCInsectItem<BeeEntity> {

    public BeeItem() {
        super("forestcraft:bee",()->EntityType.BEE);
    }

    @Override
    public BeeEntity spawnEntity(ItemStack item, BeeEntity entity) {
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
    public ItemStack caughtEntity(BeeEntity entity) {
        ItemStack stack = FCItems.BEE.getDefaultInstance();
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Age", entity.getGrowingAge());
        tag.putInt("AngerTicks", entity.getAngerTime());
        stack.setTag(tag);
        return stack;
    }
}

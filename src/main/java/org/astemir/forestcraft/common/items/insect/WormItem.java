package org.astemir.forestcraft.common.items.insect;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.animals.EntityWorm;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectBaitItem;


public class WormItem extends FCInsectBaitItem<EntityWorm> {


    public WormItem(String name,int baitPower) {
        super(name,()-> FCEntities.WORM,baitPower);
    }

    @Override
    public EntityWorm spawnEntity(ItemStack item, EntityWorm entity) {
        if (item.getItem() == FCItems.WORM){
            entity.setWormType(0);
        }else
        if (item.getItem() == FCItems.GOLDEN_WORM){
            entity.setWormType(1);
        }else
        if (item.getItem() == FCItems.DIAMOND_WORM){
            entity.setWormType(2);
        }
        return super.spawnEntity(item, entity);
    }

    @Override
    public ItemStack uniqueItemStack(EntityWorm entityWorm) {
        if (entityWorm.getWormType() == 0){
            return FCItems.WORM.getDefaultInstance();
        }else
        if (entityWorm.getWormType() == 1) {
            return FCItems.GOLDEN_WORM.getDefaultInstance();
        }else
        if (entityWorm.getWormType() == 2) {
            return FCItems.DIAMOND_WORM.getDefaultInstance();
        }
        return null;
    }

    @Override
    public ItemStack caughtEntity(EntityWorm entity) {
        if (entity.getWormType() == 0){
            return FCItems.WORM.getDefaultInstance();
        }else
        if (entity.getWormType() == 1) {
            return FCItems.GOLDEN_WORM.getDefaultInstance();
        }else
        if (entity.getWormType() == 2) {
            return FCItems.DIAMOND_WORM.getDefaultInstance();
        }
        return FCItems.WORM.getDefaultInstance();
    }
}

package org.astemir.forestcraft.common.items.insect;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.common.entities.animals.EntityCicada;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


public class CicadaItem extends FCInsectItem<EntityCicada> {

    public CicadaItem() {
        super("forestcraft:cicada",()-> FCEntities.CICADA);
    }

    @Override
    public EntityCicada spawnEntity(ItemStack item, EntityCicada entity) {
        CompoundNBT tag = item.getShareTag();
        if (tag != null) {
            if (tag.contains("Skin")) {
                entity.setSkin(tag.getInt("Skin"));
            }
        }
        return super.spawnEntity(item, entity);
    }

    @Override
    public ItemStack caughtEntity(EntityCicada entity) {
        ItemStack cicada = FCItems.CICADA.getDefaultInstance();
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Skin", entity.getSkinType());
        cicada.setTag(tag);
        return cicada;
    }
}

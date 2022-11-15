package org.astemir.api.client.item;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ItemModelHolder<T extends Model> {

    private T itemModel;
    private int ownerId;
    private long lastRenderedTick = 0;
    private ItemStack itemStack;

    public ItemModelHolder(int ownerId, ItemStack itemStack,T itemModel) {
        this.ownerId = ownerId;
        this.itemStack = itemStack;
        this.itemModel = itemModel;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public long getLastRenderedTick() {
        return lastRenderedTick;
    }

    public void setLastRenderedTick(long lastRenderedTick) {
        this.lastRenderedTick = lastRenderedTick;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public T getItemModel() {
        return itemModel;
    }
}

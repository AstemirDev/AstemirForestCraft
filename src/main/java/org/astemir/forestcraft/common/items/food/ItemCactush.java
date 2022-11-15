package org.astemir.forestcraft.common.items.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemFoodProperties;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemCactush extends AItem {

    public ItemCactush() {
        super("forestcraft:cactush");
        food(new AItemFoodProperties().hungerRestore(2).saturation(0.2f).alwaysEdible());
        itemGroup(FCItemGroups.FOOD);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, LivingEntity entity) {
        super.onFoodEaten(itemStack, entity);
        entity.attackEntityFrom(DamageSource.CACTUS,4);
    }
}

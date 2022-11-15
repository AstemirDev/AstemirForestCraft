package org.astemir.forestcraft.common.items.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemFoodProperties;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemCookedTentacle extends AItem {


    public ItemCookedTentacle() {
        super("forestcraft:cooked_tentacle");
        food(new AItemFoodProperties().hungerRestore(3).saturation(0.8f).alwaysEdible());
        itemGroup(FCItemGroups.FOOD);
        lore(TextUtils.translate(FCStrings.RESTORES_AIR,TextFormatting.GRAY),TextUtils.translate(FCStrings.COOKED_TENTACLE,TextFormatting.GRAY));
    }


    @Override
    public void onFoodEaten(ItemStack itemStack, LivingEntity entity) {
        super.onFoodEaten(itemStack, entity);
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) entity;
            playerIn.setAir(Math.max(playerIn.getMaxAir(),playerIn.getAir()+60));
        }
    }
}

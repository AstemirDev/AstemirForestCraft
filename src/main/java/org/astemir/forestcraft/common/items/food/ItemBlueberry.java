package org.astemir.forestcraft.common.items.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemFoodProperties;
import org.astemir.api.math.Pair;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemBlueberry extends AItem {

    public ItemBlueberry() {
        super("forestcraft:blueberry");
        food(new AItemFoodProperties().
                hungerRestore(2).
                saturation(0.1f).
                eatingTime(AItemFoodProperties.FAST_EATING_TIME).
                effects(new Pair<>(20,()->new EffectInstance(Effects.REGENERATION,60,0))));
        itemGroup(FCItemGroups.FOOD);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, LivingEntity entity) {
        super.onFoodEaten(itemStack, entity);
        if (RandomUtils.doWithChance(5)){
            entity.attackEntityFrom(DamageSource.CACTUS,4);
        }
    }
}

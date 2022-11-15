package org.astemir.forestcraft.common.items.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemFoodProperties;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemBoomFish extends AItem {

    public ItemBoomFish() {
        super("forestcraft:boom_fish");
        food(new AItemFoodProperties().hungerRestore(2).saturation(0.2f).alwaysEdible());
        itemGroup(FCItemGroups.FOOD);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, LivingEntity entity) {
        super.onFoodEaten(itemStack, entity);
        Explosion explosion = entity.world.createExplosion(entity,entity.getPosX(), entity.getPosY(), entity.getPosZ(), 3, Explosion.Mode.DESTROY);
        entity.attackEntityFrom(DamageSource.causeExplosionDamage(explosion),20);
    }
}

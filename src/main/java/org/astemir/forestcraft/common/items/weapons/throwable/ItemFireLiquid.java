package org.astemir.forestcraft.common.items.weapons.throwable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityFireLiquidProjectile;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemFireLiquid extends AItem {

    public ItemFireLiquid() {
        super("forestcraft:fire_liquid");
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.MISC);
        maxStack(16);
    }


    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            if (!player.world.isRemote) {
                EntityFireLiquidProjectile snowballentity = new EntityFireLiquidProjectile(player.world, player);
                snowballentity.setItem(stack);
                EntityUtils.shootProjectileIgnoreMotion(snowballentity,player,player.rotationPitch,player.rotationYaw,0.0f,1f,1.0f);
                player.world.addEntity(snowballentity);
                player.getCooldownTracker().setCooldown(stack.getItem(),5);
                stack.shrink(1);
            }
        }
        return super.onLeftClick(stack, player);
    }
}

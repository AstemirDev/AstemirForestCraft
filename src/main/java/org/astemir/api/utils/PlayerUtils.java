package org.astemir.api.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;

import java.util.function.Predicate;

public class PlayerUtils {


    public static boolean hasCooldown(PlayerEntity playerEntity,Item item){
        return playerEntity.getCooldownTracker().hasCooldown(item);
    }

    public static boolean cooldownItem(PlayerEntity playerEntity,Item item,int ticks){
        if (!hasCooldown(playerEntity,item)){
            playerEntity.getCooldownTracker().setCooldown(item,ticks);
            return true;
        }else{
            return false;
        }
    }

    public static boolean hasCooldown(PlayerEntity playerEntity,ItemStack item){
        return playerEntity.getCooldownTracker().hasCooldown(item.getItem());
    }

    public static boolean cooldownItem(PlayerEntity playerEntity,ItemStack item,int ticks){
        if (!hasCooldown(playerEntity,item)){
            playerEntity.getCooldownTracker().setCooldown(item.getItem(),ticks);
            return true;
        }else{
            return false;
        }
    }

    public static void forceItemCooldown(PlayerEntity playerEntity, Item item, int ticks){
        playerEntity.getCooldownTracker().setCooldown(item,ticks);
    }


    public static void forceItemCooldown(PlayerEntity playerEntity, ItemStack item, int ticks){
        playerEntity.getCooldownTracker().setCooldown(item.getItem(),ticks);
    }

    public static void damageItem(PlayerEntity playerEntity,ItemStack item,Hand hand,int damage){
        item.damageItem(damage, playerEntity, (entity) -> {
            playerEntity.sendBreakAnimation(hand);
        });
    }

    public static void giveItem(PlayerEntity playerEntity,Item item){
        giveItem(playerEntity,item.getDefaultInstance());
    }

    public static void giveItem(PlayerEntity playerEntity,ItemStack itemStack){
        if (!playerEntity.inventory.addItemStackToInventory(itemStack)) {
            WorldUtils.dropItem(playerEntity.world, playerEntity.getPosition(), itemStack, new Vector3d(0, 0.25f, 0));
        }
    }

    public static ItemStack findItem(PlayerEntity playerEntity,Predicate<ItemStack> predicate) {
        if (predicate.test(playerEntity.getHeldItem(Hand.MAIN_HAND))){
            return playerEntity.getHeldItem(Hand.MAIN_HAND);
        }else
        if (predicate.test(playerEntity.getHeldItem(Hand.OFF_HAND))){
            return playerEntity.getHeldItem(Hand.OFF_HAND);
        }else {
            for (int i = 0; i < playerEntity.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack1 = playerEntity.inventory.getStackInSlot(i);
                if (predicate.test(itemstack1)) {
                    return itemstack1;
                }
            }
        }
        return null;
    }

}

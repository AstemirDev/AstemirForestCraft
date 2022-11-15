package org.astemir.forestcraft.common.items.tools.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.IModItem;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.MapBuilder;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.constructors.FCInsectItem;


import java.util.Map;
import java.util.function.Supplier;

public class ItemNet extends AItem {

    private static Map<EntityType, Supplier<Item>> INSECTS = new MapBuilder<EntityType,Supplier<Item>>().
            put(EntityType.BEE,()-> FCItems.BEE).
            put(EntityType.ENDERMITE,()-> FCItems.ENDERMITE).
            put(EntityType.SILVERFISH,()-> FCItems.SILVERFISH).
            put(FCEntities.KILLER_BEE,()-> FCItems.KILLER_BEE).
            put(FCEntities.WORM,()-> FCItems.WORM).
            put(FCEntities.CICADA,()-> FCItems.CICADA).
            build();

    public ItemNet() {
        super("forestcraft:net");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxDamage(16);
        maxStack(1);
    }

    @Override
    public boolean onEntityClick(ItemStack stack, PlayerEntity playerIn, Entity target, Hand hand) {
        if (INSECTS.containsKey(target.getType())) {
            if (playerIn.world.isRemote){
                SoundUtils.playSound(playerIn.world, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS,target.getPosition(),1,1);
            }else{
                Item item = ItemNet.INSECTS.get(target.getType()).get();
                ItemStack result = item.getDefaultInstance();
                if (item instanceof IModItem){
                    ItemStack unique = ((FCInsectItem)((IModItem)item).itemConstructor()).uniqueItemStack(target);
                    if (unique != null){
                        result = unique;
                    }
                }
                PlayerUtils.damageItem(playerIn, stack, hand, 1);
                PlayerUtils.giveItem(playerIn, result);
                target.remove();
            }
            playerIn.setActiveHand(hand);
            playerIn.swingArm(hand);
        }
        return super.onEntityClick(stack, playerIn, target, hand);
    }
}

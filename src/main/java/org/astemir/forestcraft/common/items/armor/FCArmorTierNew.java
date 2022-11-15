package org.astemir.forestcraft.common.items.armor;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import org.astemir.api.math.MapBuilder;
import org.astemir.api.common.item.AArmorTier;
import org.astemir.forestcraft.registries.FCBlocks;

import static net.minecraft.inventory.EquipmentSlotType.*;
import static net.minecraft.inventory.EquipmentSlotType.FEET;

public class FCArmorTierNew {


    public static final AArmorTier ETERNAL_HUNGER = new AArmorTier(
            "eternal_hunger",
            50,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(EquipmentSlotType.HEAD,5).
                    put(EquipmentSlotType.CHEST,7).build(),
            15,
            ()-> SoundEvents.BLOCK_NETHER_WART_BREAK,
            2,
            0,
            AArmorTier.repairMaterialFrom(()-> Ingredient.fromItems(FCBlocks.ROTTEN_FLESH_BLOCK.getBlock().asItem()))
    );


    public static final AArmorTier OBSIDIAN = new AArmorTier(
            "obsidian",
            44,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,3).
                    put(CHEST,8).
                    put(LEGS,6).
                    put(FEET,3).build(),
            10,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0,
            AArmorTier.repairMaterialFrom(()->Ingredient.fromItems(Blocks.OBSIDIAN.asItem()))
    );


    public static final AArmorTier CHICKEN_WINGS = new AArmorTier(
            "chicken_wings",
            10,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(EquipmentSlotType.CHEST,2).build(),
            15,
            ()-> SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0,
            0,
            AArmorTier.repairMaterialFrom(()-> Ingredient.fromItems(Items.FEATHER))
    );
}

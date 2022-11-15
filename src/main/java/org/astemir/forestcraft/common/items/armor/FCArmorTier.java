package org.astemir.forestcraft.common.items.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import org.astemir.forestcraft.registries.FCItems;


import java.util.function.Supplier;

public enum FCArmorTier implements IArmorMaterial {

    INSANE("insane", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, () -> {
        return Ingredient.fromItems(FCItems.ALPHA_INSANE_DOG_EYE);
    }),

    OBSIDIAN("obsidian_boots", 30, new int[]{2, 5, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.05F, () -> {
        return Ingredient.fromItems(Items.OBSIDIAN);
    }),

    GEM("gem", 10, new int[]{3, 4, 6, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> {
        return Ingredient.fromItems(FCItems.GEM);
    }),

    SHARPED_LEAF("sharped_leaf", 20, new int[]{3, 5, 7, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, () -> {
        return Ingredient.fromItems(FCItems.SHARPED_LEAF);
    }),

    SEA_SCALE("sea_scale", 25, new int[]{4, 6, 7, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, () -> {
        return Ingredient.fromItems(FCItems.ANCIENT_SCALE);
    }),

    JEWEL_WART("jewel_wart", 30, new int[]{4, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F, () -> {
        return Ingredient.fromItems(FCItems.JEWEL_WART);
    }),

    AQUAMARINE("aquamarine", 25, new int[]{3, 7, 8, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.5F, 0F, () -> {
        return Ingredient.fromItems(FCItems.AQUAMARINE);
    }),

    ENDERITE("enderite", 42, new int[]{5, 7, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.25F, () -> {
        return Ingredient.fromItems(FCItems.ENDERITE_INGOT);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    FCArmorTier(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}


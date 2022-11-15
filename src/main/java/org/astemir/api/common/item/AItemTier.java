package org.astemir.api.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import java.util.function.Supplier;

public class AItemTier implements IItemTier {

    public static final AItemTier WOOD = new AItemTier(
            0,
            59,
            2.0F,
            0.0F,
            15,
            AItemTier.repairMaterialFrom(()->Ingredient.fromTag(ItemTags.PLANKS)));

    public static final AItemTier STONE = new AItemTier(
            1,
            131,
            4.0F,
            1.0F,
            5,
            AItemTier.repairMaterialFrom(()->Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)));

    public static final AItemTier IRON = new AItemTier(
            2,
            250,
            6.0F,
            2.0F,
            14,
            AItemTier.repairMaterialFrom(()->Ingredient.fromItems(Items.IRON_INGOT)));

    public static final AItemTier GOLD = new AItemTier(
            0,
            32,
            12.0F,
            0.0F,
            22,
            AItemTier.repairMaterialFrom(()->Ingredient.fromItems(Items.GOLD_INGOT)));

    public static final AItemTier DIAMOND = new AItemTier(
            3,
            1561,
            8.0F,
            3.0F,
            10,
            AItemTier.repairMaterialFrom(()->Ingredient.fromItems(Items.DIAMOND)));

    public static final AItemTier NETHERITE = new AItemTier(
            4,
            2031,
            9.0F,
            4.0F,
            15,
            AItemTier.repairMaterialFrom(()->Ingredient.fromItems(Items.NETHERITE_INGOT)));



    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;
    public static final LazyValue<Ingredient> EMPTY = new LazyValue(()->Ingredient.EMPTY);

    public AItemTier(int toolLevel, int durability, float efficiency, float attackDamage, int enchantability, LazyValue<Ingredient> repairMaterial) {
        this.harvestLevel = toolLevel;
        this.maxUses = durability;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    public static LazyValue<Ingredient> repairMaterialFrom(Supplier<Ingredient> ingredient){
        return new LazyValue(ingredient);
    }
}

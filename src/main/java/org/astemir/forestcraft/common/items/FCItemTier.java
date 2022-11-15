package org.astemir.forestcraft.common.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import org.astemir.forestcraft.registries.FCItems;

import java.util.function.Supplier;

public enum FCItemTier implements IItemTier {

    WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
        return Ingredient.fromTag(ItemTags.PLANKS);
    }),
    DIRT(0, 79, 2.0F, 0.0F, 15, () -> {
        return Ingredient.fromItems(Items.DIRT);
    }),
    STONE(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS);
    }),
    IRON(2, 250, 6.0F, 2.0F, 14, () -> {
        return Ingredient.fromItems(net.minecraft.item.Items.IRON_INGOT);
    }),
    MESA(2, 130, 6.0F, 2.0F, 14, () -> {
        return Ingredient.fromItems(Items.TERRACOTTA);
    }),
    TIERLESS(2, 200, 6.0F, 2.0F, 20, () -> {
        return Ingredient.fromItems(Items.AIR);
    }),
    KELP(2, 150, 6.0F, 2.5F, 14, () -> {
        return Ingredient.fromItems(Items.KELP);
    }),
    SHARPED_LEAF(2, 350, 6.5F, 2.5F, 20, () -> {
        return Ingredient.fromItems(FCItems.SHARPED_LEAF);
    }),
    GEM(2, 170, 6.5F, 2.5F, 15, () -> {
        return Ingredient.fromItems(FCItems.GEM);
    }),
    INSANE(3, 650, 7.0F, 3.0F, 5, () -> {
        return Ingredient.fromItems(FCItems.ALPHA_INSANE_DOG_EYE);
    }),
    STINGER(3, 1061, 7.0F, 3.25F, 10, () -> {
        return Ingredient.fromItems(FCItems.GIANT_STINGER);
    }),
    MOLTEN(2, 450, 7F, 2.85F, 5, () -> {
        return Ingredient.fromItems(FCItems.MOLTEN_INGOT);
    }),
    DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
        return Ingredient.fromItems(net.minecraft.item.Items.DIAMOND);
    }),
    SOUL(4, 1561, 8.0F, 3.0F, 10, () -> {
        return Ingredient.fromItems(FCItems.SOUL_ESSENCE);
    }),
    PRISMARINE(3, 1661, 8.0F, 3.0F, 15, () -> {
        return Ingredient.fromItems(Items.PRISMARINE_SHARD);
    }),
    ANCIENT_RUNESTONE(4, 1661, 8.0F, 3.0F, 15, () -> {
        return Ingredient.fromItems(FCItems.ARCHAIC_FRAGMENT);
    }),
    SUPER_MESA(3, 1061, 8.0F, 3.5F, 20, () -> {
        return Ingredient.fromItems(Items.TERRACOTTA);
    }),
    TERROR_BRINGER(3, 261, 8.0F, 3.0F, 10, () -> {
        return Ingredient.fromItems(Items.BONE);
    }),
    JEWEL_WART(3, 1561, 8.5F, 3.25F, 15, () -> {
        return Ingredient.fromItems(FCItems.JEWEL_WART);
    }),
    VITA(3, 1261, 8F, 3.5F, 15, () -> {
        return Ingredient.fromItems(FCItems.VITA);
    }),
    BLOOMING(5, 1161, 7.5F, 3.75F, 15, () -> {
        return Ingredient.fromItems(FCItems.BLOOMING_INGOT);
    }),
    GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
        return Ingredient.fromItems(net.minecraft.item.Items.GOLD_INGOT);
    }),
    ANCIENT_GOLD(0, 131, 12.0F, 2.5F, 22, () -> {
        return Ingredient.fromItems(net.minecraft.item.Items.GOLD_INGOT);
    }),
    AQUAMARINE(3, 961, 8.5F, 4F, 10, () -> {
        return Ingredient.fromItems(FCItems.AQUAMARINE);
    }),
    NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> {
        return Ingredient.fromItems(Items.NETHERITE_INGOT);
    }),
    ENDERITE(5, 3061, 12.0F, 5.0F, 20, () -> {
        return Ingredient.fromItems(FCItems.ENDERITE_INGOT);
    }),
    COSMIC(8, 6061, 15.0F, 7.0F, 25, () -> {
        return Ingredient.fromItems(Items.AIR);
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    FCItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
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
}


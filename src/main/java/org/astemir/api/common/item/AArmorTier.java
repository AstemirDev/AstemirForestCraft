package org.astemir.api.common.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import org.astemir.api.math.MapBuilder;

import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.inventory.EquipmentSlotType.*;

public class AArmorTier implements IArmorMaterial {

    public static final AArmorTier LEATHER = new AArmorTier(
      "leather",
      5,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,1).
                    put(CHEST,3).
                    put(LEGS,2).
                    put(FEET,1).build(),
            15,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.LEATHER))
    );

    public static final AArmorTier CHAINMAIL = new AArmorTier(
            "chainmail",
            15,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,2).
                    put(CHEST,5).
                    put(LEGS,4).
                    put(FEET,1).build(),
            12,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            0,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.IRON_INGOT))
    );

    public static final AArmorTier IRON = new AArmorTier(
            "iron",
            15,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,2).
                    put(CHEST,6).
                    put(LEGS,5).
                    put(FEET,2).build(),
            9,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.IRON_INGOT))
    );

    public static final AArmorTier GOLD = new AArmorTier(
            "gold",
            7,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,2).
                    put(CHEST,5).
                    put(LEGS,3).
                    put(FEET,1).build(),
            25,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            0,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.GOLD_INGOT))
    );

    public static final AArmorTier DIAMOND = new AArmorTier(
            "diamond",
            33,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,3).
                    put(CHEST,8).
                    put(LEGS,6).
                    put(FEET,3).build(),
            10,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.DIAMOND))
    );

    public static final AArmorTier TURTLE = new AArmorTier(
            "turtle",
            25,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,2).
                    put(CHEST,6).
                    put(LEGS,5).
                    put(FEET,2).build(),
            9,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,
            0.0f,
            0,
            repairMaterialFrom(()->Ingredient.fromItems(Items.SCUTE))
    );

    public static final AArmorTier NETHERITE = new AArmorTier(
            "netherite",
            37,
            new MapBuilder<EquipmentSlotType,Integer>().
                    put(HEAD,3).
                    put(CHEST,8).
                    put(LEGS,6).
                    put(FEET,3).build(),
            15,
            ()->SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            3.0f,
            0.1f,
            repairMaterialFrom(()->Ingredient.fromItems(Items.NETHERITE_INGOT))
    );


    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private Map<EquipmentSlotType,Integer> damageReductionAmountMap;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;


    public AArmorTier(String name, int maxDamageFactor, Map<EquipmentSlotType,Integer> damageReductionAmount, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, LazyValue<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountMap = damageReductionAmount;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        if (damageReductionAmountMap == null){
            return 0;
        }else
        if (damageReductionAmountMap.get(slotIn) == null){
            return 0;
        }else {
            return this.damageReductionAmountMap.get(slotIn);
        }
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return soundEvent.get();
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public static LazyValue<Ingredient> repairMaterialFrom(Supplier<Ingredient> ingredient){
        return new LazyValue(ingredient);
    }
}

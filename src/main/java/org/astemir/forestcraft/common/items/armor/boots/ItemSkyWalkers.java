package org.astemir.forestcraft.common.items.armor.boots;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.UUID;

public class ItemSkyWalkers extends AItemArmor {


    public ItemSkyWalkers() {
        super("forestcraft:sky_walkers",ArmorMaterial.LEATHER,EquipmentSlotType.FEET,0,0);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        setArmorTexture("forestcraft:textures/armor/sky_walkers.png");
        rarity(Rarity.UNCOMMON);
        lore(TextUtils.translate(FCStrings.LIGHT_BOOTS,TextFormatting.GRAY));
    }

    @Override
    public void addAttributes(ImmutableMultimap.Builder builder) {
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("A2392104-0A63-2124-AB29-6456FD734102"), "Movement speed", 0.065, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,5,0,false,false));
    }
}

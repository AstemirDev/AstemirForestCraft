package org.astemir.forestcraft.common.items.armor.chestplates;


import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;

import java.util.UUID;

public class ItemEternalHungerChestplate extends AItemArmor {


    public ItemEternalHungerChestplate() {
        super("forestcraft:eternal_hunger_chestplate", FCArmorTierNew.ETERNAL_HUNGER, EquipmentSlotType.CHEST, 2, 1, 0);
        setArmorTexture("forestcraft:textures/armor/eternal_hunger_chestplate.png");
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        rarity(FCRarity.MYTHICAL);
        lore(TextUtils.translate(FCStrings.ETERNAL_HUNGER_CHESTPLATE, TextFormatting.RED));
    }

    @Override
    public void addAttributes(ImmutableMultimap.Builder builder) {
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("A2392104-0A63-2124-AB29-6456FD734102"), "Movement speed", -0.01, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.STRENGTH,20,1,true,true));
        entity.addPotionEffect(new EffectInstance(Effects.HUNGER,20,2,true,true));
    }

}

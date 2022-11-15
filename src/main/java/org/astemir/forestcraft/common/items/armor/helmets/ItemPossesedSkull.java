package org.astemir.forestcraft.common.items.armor.helmets;


import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;

import java.util.UUID;

public class ItemPossesedSkull extends AItemArmor {


    public ItemPossesedSkull() {
        super("forestcraft:possesed_skull", FCArmorTierNew.ETERNAL_HUNGER, EquipmentSlotType.HEAD, -1, -2, 0);
        setArmorTexture("forestcraft:textures/entity/nether_scourge/nether_scourge.png");
        setArmorModel(FCItemModels.POSSESED_SKULL_MODEL);
        rarity(FCRarity.MYTHICAL);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
    }

    @Override
    public void addAttributes(ImmutableMultimap.Builder builder) {
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("A2392104-0A63-2124-AB29-6456FD734102"), "Movement speed", 0.01, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE,20,0,true,true));
    }

}

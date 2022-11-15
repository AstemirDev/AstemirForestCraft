package org.astemir.forestcraft.common.items.armor.boots;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Rarity;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.UUID;

public class ItemSevenLeagueBoots extends AItemArmor {


    public ItemSevenLeagueBoots() {
        super("forestcraft:seven_league_boots",ArmorMaterial.LEATHER,EquipmentSlotType.FEET,0,0);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        rarity(Rarity.UNCOMMON);
        setArmorTexture("forestcraft:textures/armor/seven_league_boots.png");
    }

    @Override
    public void addAttributes(ImmutableMultimap.Builder builder) {
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("A2392104-0A63-2124-AB29-6456FD734102"), "Movement speed", 0.05, AttributeModifier.Operation.ADDITION));
    }

}

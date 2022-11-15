package org.astemir.forestcraft.common.items.weapons.swords;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

import java.util.List;
import java.util.UUID;

public class ItemTraveller extends AItemSword {

    public ItemTraveller() {
        super("forestcraft:traveller",FCItemTier.IRON, 3, -2.4f);
        maxStack(1);
        maxStack(500);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        loreAdd(new TranslationTextComponent(FCStrings.TRAVELLER).mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public void addAttributes(ImmutableMultimap.Builder builder) {
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("B2204113-0A63-2124-AB10-6155CB234103"), "Weapon modifier", 0.05, AttributeModifier.Operation.ADDITION));
    }

}

package org.astemir.forestcraft.common.items.armor.boots;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.armor.FCArmorTierNew;


public class ItemFireResistantBoots extends AItemArmor {


    public ItemFireResistantBoots() {
        super("forestcraft:fire_resistant_boots", FCArmorTierNew.OBSIDIAN,EquipmentSlotType.FEET,0,0);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        setArmorTexture("forestcraft:textures/armor/fire_resistant_boots.png");
        lore(TextUtils.translate(FCStrings.OBSIDIAN_BOOTS,TextFormatting.GRAY));
    }


    @Override
    public boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source, float damage) {
        if (source == DamageSource.HOT_FLOOR) {
            return false;
        }
        if (source == DamageSource.LAVA || source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE) {
            if (entity instanceof PlayerEntity) {
                if (!PlayerUtils.hasCooldown((PlayerEntity) entity, itemStack.getItem())) {
                    PlayerUtils.cooldownItem((PlayerEntity) entity, itemStack.getItem(), 400);
                    entity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 300, 0, false, false));
                    return false;
                }
            }
        }
        return true;
    }
}

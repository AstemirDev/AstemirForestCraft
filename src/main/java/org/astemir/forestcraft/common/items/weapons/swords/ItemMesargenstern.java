package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;


public class ItemMesargenstern extends AItemSword {


    public ItemMesargenstern() {
        super("forestcraft:mesargenstern",FCItemTier.MESA, 3, -2.5f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
        rarity(Rarity.UNCOMMON);
        loreAdd(new TranslationTextComponent(FCStrings.MESARGENSTERN).mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)attacker;
            if (!playerEntity.getCooldownTracker().hasCooldown(stack.getItem())) {
                int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS,stack);
                if (EntityUtils.hasArmorFullSet(attacker, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS)) {
                    target.attackEntityFrom(DamageSource.causeMobDamage(attacker), 10+i);
                    playerEntity.getCooldownTracker().setCooldown(stack.getItem(),20);
                }
            }
        }
        return super.onHit(stack, target, attacker);
    }
}

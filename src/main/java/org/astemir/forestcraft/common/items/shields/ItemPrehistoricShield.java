package org.astemir.forestcraft.common.items.shields;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemTier;
import org.astemir.api.common.item.AShieldItem;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemPrehistoricShield extends AShieldItem {


    public ItemPrehistoricShield() {
        super("forestcraft:prehistoric_shield");
        maxDamage(2036);
        rarity(Rarity.EPIC);
        repairMaterial(AItemTier.repairMaterialFrom(()-> Ingredient.fromItems(FCBlocks.ARCHAIC_STONE.asItem())));
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SHIELDS);
        setupISTER(ForestCraft.PROXY::setupISTER);
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
        livingEntityIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE,20,0,true,true));
    }
}

package org.astemir.forestcraft.common.items.tools.other;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;

import net.minecraft.item.Rarity;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemFishingRod;
import org.astemir.forestcraft.common.entities.projectiles.fishing.EntityFireFishingBobber;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemMoltenFishingRod extends AItemFishingRod {


    public ItemMoltenFishingRod() {
        super("forestcraft:molten_fishing_rod");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(256);
        rarity(Rarity.UNCOMMON);
    }


    @Override
    public FishingBobberEntity createFishingBobber(PlayerEntity player, World world, int speedBonus, int luckBonus) {
        return new EntityFireFishingBobber(player,world,luckBonus,speedBonus);
    }
}


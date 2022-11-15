package org.astemir.forestcraft.common.items.weapons.bows;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.items.constructors.FCBow;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.properties.FCBows;

public class ItemSoulConqueror extends FCBow {


    public ItemSoulConqueror() {
        super("forestcraft:soul_conqueror", FCBows.SOUL_CONQUEROR);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.BOWS);
        maxDamage(600);
    }


    @Override
    public AbstractArrowEntity createArrow(World world, PlayerEntity player, AbstractArrowEntity original) {
        AbstractArrowEntity arrow = original;
        original.setFire(999999);
        return arrow;
    }
}

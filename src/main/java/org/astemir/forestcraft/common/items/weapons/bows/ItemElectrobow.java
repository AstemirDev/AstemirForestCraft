package org.astemir.forestcraft.common.items.weapons.bows;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.world.World;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.entities.projectiles.arrows.EntityElectriteArrow;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.constructors.FCBow;
import org.astemir.forestcraft.registries.properties.FCBows;

public class ItemElectrobow extends FCBow {


    public ItemElectrobow() {
        super("forestcraft:electrobow", FCBows.ELECTROBOW);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.BOWS);
        maxDamage(900);
        loreAdd(TextUtils.effectTooltip(FCEffects.ELECTROCUT,5,0,false));
    }


    @Override
    public AbstractArrowEntity createArrow(World world, PlayerEntity player, AbstractArrowEntity original) {
        return new EntityElectriteArrow(world,player);
    }
}

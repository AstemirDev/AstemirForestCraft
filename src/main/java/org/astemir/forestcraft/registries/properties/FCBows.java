package org.astemir.forestcraft.registries.properties;

import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import org.astemir.api.common.sound.Sound3D;
import org.astemir.forestcraft.common.items.weapons.bows.FCBowProperties;
import org.astemir.forestcraft.registries.FCSounds;

public class FCBows {

    public static final FCBowProperties SOUL_CONQUEROR = new FCBowProperties().
            damage(2.5f);

    public static final FCBowProperties ELECTROBOW = new FCBowProperties().
            shotSound(new Sound3D(FCSounds.ELECTRON_HIT, SoundCategory.PLAYERS)).
            damage(3);

    public static final FCBowProperties INSANE_BOW = new FCBowProperties().
            shotSound(new Sound3D(FCSounds.LAZERBEAM,SoundCategory.PLAYERS)).
            ammo(()-> Items.REDSTONE).
            damage(4);

}

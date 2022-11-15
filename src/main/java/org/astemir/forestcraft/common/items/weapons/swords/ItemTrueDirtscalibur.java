package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityDirtProjectile;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemTrueDirtscalibur extends AItemSword {

    public ItemTrueDirtscalibur() {
        super("forestcraft:true_dirtscalibur",FCItemTier.DIRT, 8, -2.4f);
        rarity(Rarity.RARE);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public int getMaxDamage() {
        return 2048;
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            SoundUtils.playSound(player.world, FCSounds.SCYTHE_WHOOSH_2.get(),SoundCategory.PLAYERS,player,1,0.75f,0.8f);
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            if (!player.world.isRemote) {
                for (int i = 0;i<3;i++) {
                    EntityDirtProjectile dirtProj = new EntityDirtProjectile(player.getEntityWorld(), player);
                    dirtProj.damage = 5;
                    dirtProj.setItem(FCItems.DIRT_CHUNK.getDefaultInstance());
                    EntityUtils.shootProjectileIgnoreMotion(dirtProj,player,player.rotationPitch,player.rotationYaw,0.0f,1,5);
                    player.world.addEntity(dirtProj);
                }
                player.getCooldownTracker().setCooldown(stack.getItem(),10);
            }
        }
        return super.onLeftClick(stack, player);
    }
}

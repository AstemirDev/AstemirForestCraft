package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityDirtProjectile;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;


public class ItemDirtscalibur extends AItemSword {

    public ItemDirtscalibur() {
        super("forestcraft:dirtscalibur",FCItemTier.DIRT, 3, -2f);
        rarity(Rarity.UNCOMMON);
        getDefaultLore().add(new TranslationTextComponent(FCStrings.DIRTSCALIBUR).mergeStyle(TextFormatting.GRAY));
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }


    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            ItemStack dirt = PlayerUtils.findItem(player, itemStack -> itemStack.getItem().equals(Items.DIRT));
            if (dirt != null) {
                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
                if (!player.world.isRemote) {
                    for (int i = 0;i<3;i++) {
                        EntityDirtProjectile dirtProj = new EntityDirtProjectile(player.getEntityWorld(), player);
                        dirtProj.setItem(FCItems.DIRT_CHUNK.getDefaultInstance());
                        EntityUtils.shootProjectileIgnoreMotion(dirtProj,player,player.rotationPitch,player.rotationYaw,0.0f,0.75f,10.f);
                        player.world.addEntity(dirtProj);
                    }
                    dirt.shrink(1);
                    player.getCooldownTracker().setCooldown(stack.getItem(),10);
                }
            }
        }
        return super.onLeftClick(stack, player);
    }

}

package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.SoundCategory;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.effect.ElectrocutEffect;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;


public class ItemElectron extends AItemSword {


    public ItemElectron() {
        super("forestcraft:electron",FCItemTier.DIAMOND, 5, -2.5f);
        lore(TextUtils.effectTooltip(FCEffects.ELECTROCUT,6,0,false));
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof CreeperEntity){
            CompoundNBT nbt = new CompoundNBT();
            nbt.put("powered",IntNBT.valueOf(1));
            target.readAdditional(nbt);
        }
        if (!target.isPotionActive(FCEffects.ELECTROCUT.get())) {
            ((ElectrocutEffect) FCEffects.ELECTROCUT.get()).addToEntity(target, 120, 0);
        }
        return super.onHit(stack, target, attacker);
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!PlayerUtils.hasCooldown(player,stack)){
            player.world.playSound(player.getPosX(),player.getPosY(),player.getPosZ(), FCSounds.ELECTRON_HIT.get(), SoundCategory.PLAYERS,1f, RandomUtils.randomFloat(0.9f,1.1f),false);
            PlayerUtils.cooldownItem(player,stack,15);
        }
        return super.onLeftClick(stack, player);
    }

}

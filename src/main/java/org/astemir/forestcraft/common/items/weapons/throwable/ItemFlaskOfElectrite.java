package org.astemir.forestcraft.common.items.weapons.throwable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityLiquidElectritePotion;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemFlaskOfElectrite extends AItem {

    public ItemFlaskOfElectrite() {
        super("forestcraft:flask_of_liquid_electrite");
        itemGroup(FCItemGroups.MISC, FCItemGroups.Priorities.POTIONS);
        maxStack(1);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            EntityLiquidElectritePotion potionentity = new EntityLiquidElectritePotion(worldIn, playerIn);
            potionentity.setItem(itemstack);
            EntityUtils.shootProjectileIgnoreMotion(potionentity,playerIn,playerIn.rotationPitch,playerIn.rotationYaw,-20.0F,0.5F,1.0F);
            worldIn.addEntity(potionentity);
        }
        playerIn.addStat(Stats.ITEM_USED.get(itemstack.getItem()));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_LINGERING_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}

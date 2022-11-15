package org.astemir.api.common.item;



import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModFishingRodItem;

public class AItemFishingRod extends AItem{

    public AItemFishingRod(String registryName) {
        super(registryName);
    }


    @Override
    public Item build(Item.Properties properties) {
        ModFishingRodItem shootableItem = new ModFishingRodItem(properties).itemConstructor(this);
        return shootableItem;
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.fishingBobber != null) {
            if (!worldIn.isRemote) {
                int i = playerIn.fishingBobber.handleHookRetraction(itemstack);
                itemstack.damageItem(i, playerIn, (player) -> {
                    player.sendBreakAnimation(handIn);
                });
            }
            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!worldIn.isRemote) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                worldIn.addEntity(createFishingBobber(playerIn,worldIn,k,j));
            }
            playerIn.addStat(Stats.ITEM_USED.get(itemstack.getItem()));
        }
        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }

    public FishingBobberEntity createFishingBobber(PlayerEntity player,World world,int speedBonus,int luckBonus){
        return new FishingBobberEntity(player, world,luckBonus,speedBonus);
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }
}

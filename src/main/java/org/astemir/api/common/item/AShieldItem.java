package org.astemir.api.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.LazyValue;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModShieldItem;

import java.util.List;

public class AShieldItem extends AItem{

    private LazyValue<Ingredient> repairMaterial;

    public AShieldItem(String registryName) {
        super(registryName);
    }

    public AShieldItem repairMaterial(LazyValue<Ingredient> repairMaterial) {
        this.repairMaterial = repairMaterial;
        return this;
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModShieldItem shieldItem = (ModShieldItem) new ModShieldItem(properties).itemConstructor(this);
        return shieldItem;
    }

    @Override
    public void dynamicLoreBuilding(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        BannerItem.appendHoverTextFromTileEntityTag(stack, tooltip);
        super.dynamicLoreBuilding(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public boolean isShield(LivingEntity entity, ItemStack stack) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repairMaterial.getValue().test(repair) || super.getIsRepairable(toRepair, repair);
    }
}

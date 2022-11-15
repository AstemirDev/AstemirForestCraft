package org.astemir.forestcraft.common.items.armor.wings.curios;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModArmorItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ModWings extends ModArmorItem implements ICurioItem {

    public ModWings(IArmorMaterial tier, EquipmentSlotType slot, Properties builderIn, float damageReduction, float toughness, float knockbackResistance) {
        super(tier, slot, builderIn, damageReduction, toughness, knockbackResistance);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        PlayerEntity wearer = (PlayerEntity)slotContext.getWearer();
        ItemStack chestplate = EntityUtils.chestplate(wearer).copy();
        if (chestplate.getItem() instanceof ElytraItem){
            wearer.setItemStackToSlot(EquipmentSlotType.CHEST,Items.AIR.getDefaultInstance());
            PlayerUtils.giveItem(wearer,chestplate);
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return ActionResult.func_233538_a_(playerIn.getHeldItem(handIn), worldIn.isRemote());
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        if (armorType == EquipmentSlotType.CHEST) {
            return false;
        }
        return super.canEquip(stack,armorType,entity);
    }
}

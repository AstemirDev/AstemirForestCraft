package org.astemir.forestcraft.common.items.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemGuideBook extends AItem {


    public ItemGuideBook() {
        super("forestcraft:guidebook");
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        maxStack(1);
        rarity(Rarity.UNCOMMON);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote) {
            ForestCraft.PROXY.openGuidebook();
        }
        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}

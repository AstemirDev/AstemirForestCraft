package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.common.item.AItem;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCSounds;

public class FCLootBox extends AItem {

    public final ItemDropPool lootTable;

    public FCLootBox(String name, ItemDropPool loot) {
        super(name);
        this.lootTable = loot;
        rarity(Rarity.UNCOMMON);
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        lore(TextUtils.translate(FCStrings.CAN_BE_OPENED, TextFormatting.GRAY));
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            PlayerUtils.giveItem(playerIn,lootTable.drop());
            playerIn.getHeldItem(handIn).shrink(1);
        }else{
            playerIn.playSound(FCSounds.CASE_OPEN.get(),1, RandomUtils.randomFloat(0.9f,1.1f));
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}


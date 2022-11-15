package org.astemir.forestcraft.common.items.tools.other;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;

import java.util.List;
import java.util.Map;


public class ItemCrosser extends AItem {

    protected static final Map<Block, BlockState> LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.SAND, FCBlocks.SAND_WITH_CROSS.getDefaultState(),Blocks.RED_SAND, FCBlocks.RED_SAND_WITH_CROSS.getDefaultState()));

    public ItemCrosser() {
        super("forestcraft:crosser");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(64);
        loreAdd(new TranslationTextComponent(FCStrings.CROSSER).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
            BlockState blockstate = getTillingState(world.getBlockState(blockpos));
            if (blockstate != null) {
                PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.5F);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    if (playerentity != null) {
                        context.getItem().damageItem(1, playerentity, (player) -> {
                            player.sendBreakAnimation(context.getHand());
                        });
                    }
                }
                return ActionResultType.func_233537_a_(world.isRemote);
            }
        }

        return ActionResultType.PASS;
    }

    public static BlockState getTillingState(BlockState originalState) {
        return LOOKUP.get(originalState.getBlock());
    }

}

package org.astemir.api.common.item;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModMusicDiscItem;

import java.util.List;
import java.util.function.Supplier;

public class AItemMusicDisc extends AItem{

    private Supplier<SoundEvent> music;

    public AItemMusicDisc(String registryName,Supplier<SoundEvent> music) {
        super(registryName);
        this.music = music;
    }

    @Override
    public void dynamicLoreBuilding(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.dynamicLoreBuilding(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(stack.getTranslationKey() + ".desc").mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public Item build(Item.Properties properties) {
        ModMusicDiscItem shieldItem = (ModMusicDiscItem) new ModMusicDiscItem(music,properties).itemConstructor(this);
        return shieldItem;
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.isIn(Blocks.JUKEBOX) && !blockstate.get(JukeboxBlock.HAS_RECORD)) {
            ItemStack itemstack = context.getItem();
            if (!world.isRemote) {
                ((JukeboxBlock)Blocks.JUKEBOX).insertRecord(world, blockpos, blockstate, itemstack);
                world.playEvent((PlayerEntity)null, 1010, blockpos, Item.getIdFromItem(itemstack.getItem()));
                itemstack.shrink(1);
                PlayerEntity playerentity = context.getPlayer();
                if (playerentity != null) {
                    playerentity.addStat(Stats.PLAY_RECORD);
                }
            }
            return ActionResultType.func_233537_a_(world.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }
}

package org.astemir.forestcraft.common.items.tools.other;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.IAnimatedItem;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCStructures;


public class ItemOldCompass extends AItem implements IAnimatedItem {

    public ItemOldCompass() {
        super("forestcraft:old_compass");
        itemGroup(FCItemGroups.EQUIPMENT, FCItemGroups.Priorities.MISC);
        maxStack(1);
        rarity(Rarity.RARE);
        setupISTER(ForestCraft.PROXY::setupISTER);
    }

    @Override
    public void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity) {
        CompoundNBT nbt = itemStack.getOrCreateTag();
        if (entity.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) entity.world;
            BlockPos pos = serverWorld.func_241117_a_(FCStructures.ANCIENT_RUNESTONE_TEMPLE,entity.getPosition(),100,false);
            nbt.put("StructurePosition", NBTUtil.writeBlockPos(pos));
        }
    }

    @Override
    public void readShareTag(ItemStack stack, CompoundNBT nbt) {
        super.readShareTag(stack, nbt);
    }

    @Override
    public int getModelIndex() {
        return FCItemModels.OLD_COMPASS;
    }
}

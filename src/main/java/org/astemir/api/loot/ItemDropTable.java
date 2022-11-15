package org.astemir.api.loot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import java.util.List;


public class ItemDropTable extends DropTable<IDropable<ItemStack>, ItemStack>{


    public void dropItems(World world, BlockPos pos,PlayerEntity playerEntity){
        List<ItemStack> list = drop(playerEntity);
        for (ItemStack item : list) {
            WorldUtils.dropItem(world,pos,item,new Vector3d(RandomUtils.randomFloat(-0.1f,0.1f),0.25f,RandomUtils.randomFloat(-0.1f,0.1f)));
        }
    }

    @Override
    public ItemDropTable item(IDropable<ItemStack>... drop) {
        return (ItemDropTable) super.item(drop);
    }

    @Override
    public ItemDropTable item(double chance, IDropable<ItemStack>... drop) {
        return (ItemDropTable) super.item(chance, drop);
    }
}

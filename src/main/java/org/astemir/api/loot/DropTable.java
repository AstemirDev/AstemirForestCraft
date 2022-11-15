package org.astemir.api.loot;


import net.minecraft.entity.player.PlayerEntity;
import java.util.ArrayList;
import java.util.List;

public class DropTable<T extends IDropable<A>,A> implements IDropable<List<A>>{

    private List<T> loot = new ArrayList<>();


    public DropTable item(T... drop){
        for (T randomDrop : drop) {
            loot.add(randomDrop);
        }
        return this;
    }

    public DropTable item(double chance, T... drop){
        for (T randomDrop : drop) {
            if (randomDrop instanceof IDropChance) {
                ((IDropChance)randomDrop).chance(chance);
            }
        }
        return item(drop);
    }


    @Override
    public List<A> drop(PlayerEntity player) {
        List<A> list = new ArrayList<>();
        for (T drop : loot) {
            A droppedItem = drop.drop(player);
            if (droppedItem != null){
                list.add(droppedItem);
            }
        }
        return list;
    }

    @Override
    public List<A> drop() {
        return drop(null);
    }

}

package org.astemir.api.loot;


import net.minecraft.entity.player.PlayerEntity;



public class DropPool<T extends IDropable<A>,A> extends WeightedRandom<T> implements IDropable<A>{

    @Override
    public A drop(PlayerEntity player) {
        IDropable<A> drop = random(player);
        if (drop != null) {
            return drop.drop(player);
        }
        return null;
    }

    @Override
    public A drop() {
        return drop(null);
    }
}

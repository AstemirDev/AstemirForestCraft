package org.astemir.api.loot;


import net.minecraft.entity.player.PlayerEntity;


public interface IDropable<T> {

    T drop(PlayerEntity player);

    T drop();
}
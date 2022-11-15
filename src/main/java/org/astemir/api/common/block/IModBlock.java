package org.astemir.api.common.block;


import net.minecraft.block.BlockState;

public interface IModBlock<T extends ABlock> {

    T blockConstructor();

    public void defaultState(BlockState state);

    IModBlock blockConstructor(T constructor);
}

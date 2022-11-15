package org.astemir.api.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ACapabilityStorage<T extends ACapability> implements Capability.IStorage<T>{

    @Nullable
    @Override
    public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
        return instance.writeNBT();
    }

    @Override
    public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
        instance.readNBT(nbt);
        return;
    }
}

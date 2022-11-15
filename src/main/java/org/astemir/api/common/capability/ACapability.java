package org.astemir.api.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;


public abstract class ACapability {

    public abstract <T extends ACapability> void cloneData(T another);
    public abstract CompoundNBT writeNBT();

    public abstract void readNBT(INBT nbt);
}

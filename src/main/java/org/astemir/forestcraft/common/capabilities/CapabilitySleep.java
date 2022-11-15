package org.astemir.forestcraft.common.capabilities;


import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import org.astemir.api.common.capability.ACapability;


public class CapabilitySleep extends ACapability {

    private int ticks;

    @Override
    public <T extends ACapability> void cloneData(T another) {
        setSleepingTicks(((CapabilitySleep)another).getSleepingTicks());
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("Ticks",getSleepingTicks());
        return nbt;
    }

    @Override
    public void readNBT(INBT nbt) {
        setSleepingTicks(((CompoundNBT)nbt).getInt("Ticks"));
    }


    public void setSleepingTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getSleepingTicks() {
        return ticks;
    }
}

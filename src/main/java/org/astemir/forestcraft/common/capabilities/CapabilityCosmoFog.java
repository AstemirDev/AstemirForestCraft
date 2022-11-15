package org.astemir.forestcraft.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import org.astemir.api.common.capability.ACapability;

public class CapabilityCosmoFog extends ACapability {

    private boolean fog = false;

    @Override
    public <T extends ACapability> void cloneData(T another) {
        fog = ((CapabilityCosmoFog)another).hasFog();
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("Fog",fog);
        return nbt;
    }

    @Override
    public void readNBT(INBT nbt) {
        this.fog = ((CompoundNBT)nbt).getBoolean("Fog");
    }

    public boolean hasFog() {
        return fog;
    }

    public void setFog(boolean fog) {
        this.fog = fog;
    }
}

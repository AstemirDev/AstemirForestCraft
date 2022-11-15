package org.astemir.forestcraft.common.capabilities;


import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.world.World;
import org.astemir.api.common.capability.ACapability;


public class CapabilityFlyingOnWings extends ACapability {

    private int flyingTicks;
    private int totalFlyingTicks = 0;

    @Override
    public <T extends ACapability> void cloneData(T another) {
        flyingTicks = (((CapabilityFlyingOnWings)another).getFlyingTicks());
        totalFlyingTicks = (((CapabilityFlyingOnWings)another).getTotalFlyingTicks());
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("FlyingTicks",flyingTicks);
        nbt.putInt("TotalFlyingTicks",totalFlyingTicks);
        return nbt;
    }

    @Override
    public void readNBT(INBT nbt) {
        flyingTicks = (((CompoundNBT)nbt).getInt("FlyingTicks"));
        totalFlyingTicks = (((CompoundNBT)nbt).getInt("TotalFlyingTicks"));
    }

    public int getFlyingTicks() {
        return flyingTicks;
    }

    public void update() {
        if (flyingTicks > 0) {
            this.flyingTicks -=1;
        }
    }


    public int getTotalFlyingTicks() {
        return totalFlyingTicks;
    }

    public void setTotalFlyingTicks(int totalFlyingTicks) {
        this.totalFlyingTicks = totalFlyingTicks;
    }

    public boolean canFly(int maxFlyingTicks) {
        return totalFlyingTicks < maxFlyingTicks;
    }

    public boolean setFlyingTicks(int flyingTicks, int maxFlyingTicks) {
        if (canFly(maxFlyingTicks)) {
            this.flyingTicks = flyingTicks;
            this.totalFlyingTicks += flyingTicks;
            return true;
        }
        return false;
    }
}

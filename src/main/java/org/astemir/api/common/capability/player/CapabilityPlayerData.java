package org.astemir.api.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import org.astemir.api.common.capability.ACapability;

public class CapabilityPlayerData extends ACapability {

    private PlayerDataHandler playerData;

    public CapabilityPlayerData() {
        this.playerData = new PlayerDataHandler();
    }

    @Override
    public <T extends ACapability> void cloneData(T another) {
        this.playerData = ((CapabilityPlayerData)another).getPlayerData();
    }

    @Override
    public CompoundNBT writeNBT() {
        if (playerData != null) {
            return playerData.serialize();
        }
        return new CompoundNBT();
    }

    @Override
    public void readNBT(INBT nbt) {
        PlayerDataHandler data = new PlayerDataHandler();
        data.deserialize(nbt);
        setPlayerData(data);
    }

    public void setPlayerData(PlayerDataHandler data) {
        this.playerData = data;
    }

    public PlayerDataHandler getPlayerData() {
        return playerData;
    }


    @Override
    public String toString() {
        return "CapabilityPlayerData{" +
                "playerData=" + playerData +
                '}';
    }
}

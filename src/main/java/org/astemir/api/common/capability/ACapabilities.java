package org.astemir.api.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.astemir.api.common.capability.player.CapabilityPlayerData;

// Call after register all properties of capabilities
public class ACapabilities {

    @CapabilityInject(CapabilityPlayerData.class)
    public static Capability<CapabilityPlayerData> PLAYER_DATA_CAPABILITY;
}

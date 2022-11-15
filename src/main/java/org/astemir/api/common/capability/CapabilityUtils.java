package org.astemir.api.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.network.CapabilitySyncMessage;
import org.astemir.forestcraft.ForestCraft;

public class CapabilityUtils {


    public static <T extends ACapability> T getCapability(Capability<T> capability, Entity entity){
        return entity.getCapability(capability).orElse(null);
    }

    public static <T extends ACapability> T getCapability(Capability<T> capability, ItemStack item){
        return item.getCapability(capability).orElse(null);
    }

    public static void sendCapabilityChangedPacket(Capability capability, ServerPlayerEntity player){
        if (capability != null) {
            ACapability cap = getCapability(capability, player);
            if (cap != null && player != null) {
                ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> player), new CapabilitySyncMessage(player.getEntityId(), cap.getClass().getCanonicalName(),cap.writeNBT()));
            }
        };
    }
}

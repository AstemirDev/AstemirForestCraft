package org.astemir.api.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandler {

    private SimpleChannel network;

    public void startChannel(ResourceLocation channelName, String version) {
        network = NetworkRegistry.newSimpleChannel(channelName, () -> version,
                (v) -> v.equals(version),
                (v) -> v.equals(version));
    }

    public <MSG> void registerMessage(int index, Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer, final Optional<NetworkDirection> networkDirection) {
        network.registerMessage(index, messageType, encoder, decoder, messageConsumer, networkDirection);
    }

    public <MSG> void registerMessage(int index, Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
        network.registerMessage(index, messageType, encoder, decoder, messageConsumer);
    }

    public SimpleChannel getNetwork() {
        return network;
    }
}

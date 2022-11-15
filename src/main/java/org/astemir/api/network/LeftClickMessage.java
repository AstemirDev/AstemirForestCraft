package org.astemir.api.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.common.event.ItemEventHandler;

import java.util.function.Supplier;

public class LeftClickMessage {

    private Hand hand;

    public LeftClickMessage(Hand hand) {
        this.hand = hand;
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(LeftClickMessage message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            PlayerEntity player = context.get().getSender();
            if (player != null) {
                ItemEventHandler.onLeftClick(player, player.getHeldItem(message.hand));
            }
        }
    }


    public static LeftClickMessage decode(PacketBuffer buf) {
        return new LeftClickMessage(buf.readEnumValue(Hand.class));
    }

    public static void encode(LeftClickMessage message,PacketBuffer buf) {
        buf.writeEnumValue(message.hand);
    }

}

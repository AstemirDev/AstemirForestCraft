package org.astemir.forestcraft.network;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.ui.BossbarHandler;
import org.astemir.forestcraft.client.FCClientAPI;
import org.astemir.forestcraft.client.ScreenShaker;

import java.util.function.Supplier;


public class ScreenShakeMessage {


    private int shakeTicks = 20;
    private int shakePower = 1;


    public ScreenShakeMessage(int shakeTicks, int shakePower) {
        this.shakeTicks = shakeTicks;
        this.shakePower = shakePower;
    }

    public static ScreenShakeMessage decode(PacketBuffer buffer) {
        ScreenShakeMessage message = new ScreenShakeMessage(buffer.readInt(),buffer.readInt());
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(shakeTicks);
        buffer.writeInt(shakePower);
    }

    public static class Handler {

        public static void checkConditions(final ScreenShakeMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
            NetworkEvent.Context context = ctxSupplier.get();
            LogicalSide side = context.getDirection().getReceptionSide();
            context.setPacketHandled(true);
            if (!side.isClient()) {
                return;
            }
            ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
            if (playerEntity == null) {
                return;
            }
            context.enqueueWork(()->processMessage(message,playerEntity));
        }


        private static void processMessage(ScreenShakeMessage message, ClientPlayerEntity player) {
            FCClientAPI.SCREEN_SHAKER.shake(message.shakeTicks,message.shakePower);
        }

    }

}

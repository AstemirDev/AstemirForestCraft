package org.astemir.forestcraft.network;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.sound.Audio;
import org.astemir.forestcraft.client.sound.item.MinigunSound;

import java.util.function.Supplier;


public class MinigunSoundMessage {

    private int entityID;



    public MinigunSoundMessage(int entityId) {
        this.entityID = entityId;
    }

    public static MinigunSoundMessage decode(PacketBuffer buffer) {
        MinigunSoundMessage message = new MinigunSoundMessage(buffer.readInt());
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityID);
    }

    public static class Handler {

        public static void checkConditions(final MinigunSoundMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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
            context.enqueueWork(() -> processMessage(message, playerEntity));
        }

        private static boolean isPlaying(){
            for (Audio sound : AstemirAPI.CLIENT.AUDIO_MANAGER.getSounds()) {
                if (sound instanceof MinigunSound){
                    return true;
                }
            }
            return false;
        }


        private static void processMessage(MinigunSoundMessage message, ClientPlayerEntity player) {
            Entity entity = player.world.getEntityByID(message.entityID);
            if (entity != null) {
                MinigunSound sound = new MinigunSound((LivingEntity) entity);
                if (!isPlaying()) {
                    sound.play();
                }
            }
        }

    }

}

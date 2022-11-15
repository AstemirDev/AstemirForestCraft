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
import org.astemir.forestcraft.client.sound.other.ElectrocutSound;
import java.util.function.Supplier;


public class ElectroSoundMessage {

    private int entityID;
    private int dur;

    public ElectroSoundMessage(int id, int dur) {
        this.entityID = id;
        this.dur = dur;
    }

    public static ElectroSoundMessage decode(PacketBuffer buffer) {
        ElectroSoundMessage message = new ElectroSoundMessage(buffer.readInt(),buffer.readInt());
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityID);
        buffer.writeInt(this.dur);
    }


    public static class Handler {


        public static void checkConditions(final ElectroSoundMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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


        private static boolean doesEntityAlreadyHasElectrocut(LivingEntity entity){
            for (Audio sound : AstemirAPI.CLIENT.AUDIO_MANAGER.getSounds()){
                if (sound instanceof ElectrocutSound){
                    if (((ElectrocutSound)sound).getTarget().getUniqueID().equals(entity)){
                        return true;
                    }
                }
            }
            return false;
        }

        private static void processMessage(ElectroSoundMessage message, ClientPlayerEntity player) {
            Entity entity = player.world.getEntityByID(message.entityID);
            if (entity != null) {
                if (entity instanceof LivingEntity) {
                    if (!doesEntityAlreadyHasElectrocut((LivingEntity) entity)) {
                        ElectrocutSound sound = new ElectrocutSound((LivingEntity) entity, message.dur);
                        sound.play();
                    }
                }
            }
        }

    }


}

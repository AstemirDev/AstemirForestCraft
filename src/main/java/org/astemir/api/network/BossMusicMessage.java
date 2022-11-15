package org.astemir.api.network;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.sound.Audio;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;


public class BossMusicMessage {

    private int entityID;
    private int bossThemeIndex;

    public BossMusicMessage(int entityId,int bossThemeIndex) {
        this.entityID = entityId;
        this.bossThemeIndex = bossThemeIndex;
    }

    public static BossMusicMessage decode(PacketBuffer buffer) {
        BossMusicMessage message = new BossMusicMessage(buffer.readInt(),buffer.readInt());
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityID);
        buffer.writeInt(this.bossThemeIndex);
    }

    public static class Handler {

        public static void checkConditions(final BossMusicMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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


        private static void processMessage(BossMusicMessage message, ClientPlayerEntity player) {
            Entity entity = player.world.getEntityByID(message.entityID);
            if (entity != null) {
                try {
                    if (AstemirAPI.CLIENT.AUDIO_MANAGER != null) {
                        if (AstemirAPI.CLIENT.AUDIO_MANAGER.hasIndexedAudio(message.bossThemeIndex)) {
                            Audio audio = AstemirAPI.CLIENT.AUDIO_MANAGER.getIndexedAudio(message.bossThemeIndex).getDeclaredConstructor(LivingEntity.class).newInstance(entity);
                            audio.play();
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

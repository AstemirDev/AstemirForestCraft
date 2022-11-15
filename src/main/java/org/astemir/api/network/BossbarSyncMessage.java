package org.astemir.api.network;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.ui.BossbarHandler;
import org.astemir.api.common.bossbar.ABossbar;

import java.util.function.Supplier;


public class BossbarSyncMessage {


    public static final int ADD = 0;
    public static final int REMOVE = 1;
    public static final int UPDATE = 2;


    private ABossbar bossbar;
    private int entityId;
    private int messageType;

    public BossbarSyncMessage(int entityId,int messageType,ABossbar bossbar) {
        this.entityId = entityId;
        this.messageType = messageType;
        this.bossbar = bossbar;
    }



    public static BossbarSyncMessage decode(PacketBuffer buffer) {
        BossbarSyncMessage message = new BossbarSyncMessage(buffer.readInt(),buffer.readInt(),new ABossbar().decode(buffer.readCompoundTag()));
        return message;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeInt(messageType);
        buffer.writeCompoundTag(bossbar.encode());
    }

    public static class Handler {

        public static void checkConditions(final BossbarSyncMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
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


        private static void processMessage(BossbarSyncMessage message, ClientPlayerEntity player) {
            Entity entity = player.world.getEntityByID(message.entityId);
            if (entity != null) {
                BossbarHandler handler = AstemirAPI.CLIENT.BOSSBAR_HANDLER;
                switch (message.messageType){
                    case ADD:{
                        message.bossbar.boss((LivingEntity) player.world.getEntityByID(message.bossbar.getBossId()));
                        message.bossbar.value(0);
                        message.bossbar.previousValue(0);
                        handler.addBossbar(message.bossbar);
                        break;
                    }
                    case REMOVE:{
                        message.bossbar.value(0);
                        message.bossbar.previousValue(0);
                        handler.removeBossbar(message.bossbar);
                        break;
                    }
                    case UPDATE:{
                        ABossbar serverBossbar = handler.getBossbar(message.bossbar);
                        if (serverBossbar != null) {
                            serverBossbar.bossName(message.bossbar.bossName());
                            serverBossbar.value(message.bossbar.value());
                            serverBossbar.previousValue(message.bossbar.previousValue());
                            serverBossbar.color(message.bossbar.color());
                            serverBossbar.style(message.bossbar.style());
                        }
                        break;
                    }
                }
            }
        }

    }

}

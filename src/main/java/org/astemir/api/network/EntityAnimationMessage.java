package org.astemir.api.network;


import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.astemir.api.client.animator.IAnimated;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class EntityAnimationMessage {

    private int type;
    private int id;
    private int entityID;

    public EntityAnimationMessage() {
    }

    public EntityAnimationMessage(int type, int entityID, int id) {
        this.type = type;
        this.entityID = entityID;
        this.id = id;
    }


    public static void encode(final EntityAnimationMessage message, final PacketBuffer buf) {
        buf.writeVarInt(message.type);
        buf.writeVarInt(message.entityID);
        buf.writeVarInt(message.id);
    }

    public static EntityAnimationMessage decode(final PacketBuffer buf) {
        final EntityAnimationMessage message = new EntityAnimationMessage();
        message.type = buf.readVarInt();
        message.entityID = buf.readVarInt();
        message.id = buf.readVarInt();
        return message;
    }

    public static class Handler implements BiConsumer<EntityAnimationMessage, Supplier<NetworkEvent.Context>> {
        @Override
        public void accept(final EntityAnimationMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
            final NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                IAnimated entity = (IAnimated) Minecraft.getInstance().world.getEntityByID(message.entityID);
                if (entity != null) {
                    if (message.type == 0) {
                        entity.getFactory().playAnimation(message.id);
                    }else
                    if (message.type == 1) {
                        entity.getFactory().stopAnimation(message.id);
                    }
                }
            });
            context.setPacketHandled(true);
        }
    }

}

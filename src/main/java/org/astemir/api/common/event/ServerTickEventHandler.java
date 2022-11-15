package org.astemir.api.common.event;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.AstemirAPI;

public class ServerTickEventHandler {

    @SubscribeEvent
    public static void onTickServer(TickEvent.ServerTickEvent e){
        if (e.phase == TickEvent.Phase.START){
            AstemirAPI.TASK_MANAGER.update();
        }
    }
}

package org.astemir.api.common.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.client.event.EntityAnimationEvent;
import org.astemir.api.client.event.TileEntityAnimationEvent;

public class AnimationEventHandler {


    @SubscribeEvent
    public static void onTickAnimationEntity(EntityAnimationEvent.Tick e){
        if (e.getEntity() instanceof IAnimated){
            ((IAnimated)e.getEntity()).getFactory().onAnimationTick(e.getAnimation(),e.getTick());
        }
    }

    @SubscribeEvent
    public static void onEndAnimationEntity(EntityAnimationEvent.End e){
        if (e.getEntity() instanceof IAnimated){
            ((IAnimated)e.getEntity()).getFactory().onAnimationEnd(e.getAnimation());
        }
    }

    @SubscribeEvent
    public static void onEndAnimationTileEntity(TileEntityAnimationEvent.End e){
        if (e.getEntity() instanceof IAnimated){
            ((IAnimated)e.getEntity()).getFactory().onAnimationEnd(e.getAnimation());
        }
    }
}

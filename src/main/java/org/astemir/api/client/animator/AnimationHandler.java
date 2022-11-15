package org.astemir.api.client.animator;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.client.event.EntityAnimationEvent;
import org.astemir.api.client.event.TileEntityAnimationEvent;
import org.astemir.api.common.entity.AnimationFactory;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.api.network.EntityAnimationMessage;
import org.astemir.api.network.TileEntityAnimationMessage;


public enum AnimationHandler {

    INSTANCE;

    public <T extends IAnimated> void sendEntityAnimationMessage(AnimationFactory<T> factory, Animation animation, int type) {
        Entity entity = (Entity) factory.getAnimatable();
        if (entity.world.isRemote) {
            return;
        }
        if (type == 0) {
            factory.playAnimation(animation.getID());
        }else
        if (type == 1){
            factory.stopAnimation(animation.getID());
        }
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (Entity)factory.getAnimatable()), new EntityAnimationMessage(type,((Entity)factory.getAnimatable()).getEntityId(), animation.getID()));
    }

    public <T extends IAnimated> void sendTileEntityAnimationMessage(AnimationFactory<T> factory,Animation animation, int type) {
        TileEntity tileEntity = (TileEntity) factory.getAnimatable();
        if (tileEntity.getWorld().isRemote) {
            return;
        }
        if (type == 0){
            factory.playAnimation(animation.getID());
        }else
        if (type == 1){
            factory.stopAnimation(animation.getID());
        }
        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.NEAR.with(()->new PacketDistributor.TargetPoint(tileEntity.getPos().getX(),tileEntity.getPos().getY(),tileEntity.getPos().getZ(),100,tileEntity.getWorld().getDimensionKey())), new TileEntityAnimationMessage(type,((TileEntity)factory.getAnimatable()).getPos(), animation.getID()));
    }

    public <T extends IAnimated> void updateAnimations(AnimationFactory<T> factory) {
        IAnimated animatable = factory.getAnimatable();
        for (AnimationData activeAnimation : factory.getActiveAnimations()) {
            Animation animation = factory.getAnimation(activeAnimation.getId());
            if (animatable instanceof Entity) {
                if (activeAnimation.getTicks() == 0) {
                    EntityAnimationEvent event = new EntityAnimationEvent.Start((Entity) animatable, animation);
                    if (!MinecraftForge.EVENT_BUS.post(event)) {
                       this.sendEntityAnimationMessage(factory, event.getAnimation(), 0);
                    }
                }
                if (activeAnimation.getTicks() < animation.getTime() * animation.getSpeed()) {
                    activeAnimation.setPreviousTicks(activeAnimation.getTicks());
                    activeAnimation.setTicks(activeAnimation.getTicks() + 1);
                    MinecraftForge.EVENT_BUS.post(new EntityAnimationEvent.Tick((Entity) animatable, animation, (float) activeAnimation.getTicks()));
                }
                if (activeAnimation.getTicks() >= animation.getTime() * animation.getSpeed()) {
                    MinecraftForge.EVENT_BUS.post(new EntityAnimationEvent.End((Entity) animatable, animation));
                    if (animation.isLoop()) {
                        activeAnimation.setPreviousTicks(activeAnimation.getTicks());
                        activeAnimation.setTicks(0);
                    } else {
                        factory.stopAnimation(animation);
                    }
                }
            }else
            if (animatable instanceof TileEntity){
                if (activeAnimation.getTicks() == 0) {
                    TileEntityAnimationEvent event = new TileEntityAnimationEvent.Start((TileEntity) animatable, animation);
                    if (!MinecraftForge.EVENT_BUS.post(event)) {
                        this.sendTileEntityAnimationMessage(factory, event.getAnimation(),0);
                    }
                }
                if (activeAnimation.getTicks() < animation.getTime()*animation.getSpeed()) {
                    activeAnimation.setPreviousTicks(activeAnimation.getTicks());
                    activeAnimation.setTicks(activeAnimation.getTicks() + 1);
                    MinecraftForge.EVENT_BUS.post(new TileEntityAnimationEvent.Tick((TileEntity) animatable, animation, (float) activeAnimation.getTicks()));
                }
                if (activeAnimation.getTicks() >= animation.getTime()*animation.getSpeed()) {
                    MinecraftForge.EVENT_BUS.post(new TileEntityAnimationEvent.End((TileEntity) animatable, animation));
                    if(animation.isLoop()) {
                        activeAnimation.setPreviousTicks(activeAnimation.getTicks());
                        activeAnimation.setTicks(0);
                    }else{
                        factory.stopAnimation(animation);
                    }
                }
            }
        }
    }


}

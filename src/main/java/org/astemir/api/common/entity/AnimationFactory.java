package org.astemir.api.common.entity;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.animator.*;
import org.astemir.api.network.AnimationsResetMessage;
import org.astemir.api.network.TileEntityAnimationResetMessage;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class AnimationFactory<T extends IAnimated>{

    private CopyOnWriteArrayList<AnimationData> activeAnimations = new CopyOnWriteArrayList<>();
    private List<Animation> animations;
    private T animatable;

    public AnimationFactory(T animatable,Animation... animations) {
        this.animatable = animatable;
        this.animations = Arrays.asList(animations);
    }

    public AnimationFactory(T animatable) {
        this.animatable = animatable;
    }


    public boolean isPlaying(int id) {
        for (AnimationData activeAnimation : getActiveAnimations()) {
            if (activeAnimation.getId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean isPlaying(Animation animation) {
        return isPlaying(animation.getID());
    }

    public void stopAllAnimations() {
        for (Animation animation : animations) {
            stopAnimation(animation);
        }
    }

    public void reset(World world){
        if (world.isRemote){
            if (Minecraft.getInstance().getConnection() != null){
                if (Minecraft.getInstance().getConnection().getNetworkManager() != null){
                    if (animatable instanceof Entity) {
                        AstemirAPI.PACKET_HANDLER.getNetwork().sendToServer(new AnimationsResetMessage(((Entity)animatable).getEntityId()));
                    }else
                    if (animatable instanceof TileEntity){
                        AstemirAPI.PACKET_HANDLER.getNetwork().sendToServer(new TileEntityAnimationResetMessage(((TileEntity)animatable).getPos()));
                    }
                }
            }
        }else{
            stopAllAnimations();
        }
    }



    public void stopAnimation(Animation animation) {
        if (animation == null) return;
        if (animatable instanceof Entity) {
            AnimationHandler.INSTANCE.sendEntityAnimationMessage(this, animation, 1);
        }else
        if (animatable instanceof TileEntity){
            AnimationHandler.INSTANCE.sendTileEntityAnimationMessage(this, animation, 1);
        }
    }

    public void stopAnimation(int id) {
        for (AnimationData activeAnimation : getActiveAnimations()) {
            if (activeAnimation.getId() == id){
                activeAnimations.remove(activeAnimation);
            }
        }
    }

    public void playAnimation(int id) {
        if (!isPlaying(id)) {
            Animation animation = getAnimation(id);
            activeAnimations.add(new AnimationData(animation.getID(), 0,animation.getSpeed()));
            for (AnimationData activeAnimation : getActiveAnimations()) {
                if (animation.isConflict()) {
                    if (animation.isConflictsWith(activeAnimation.getId())) {
                        activeAnimations.remove(activeAnimation);
                    }
                }
            }
        }
    }

    public void playAnimation(AnimationData data) {
        activeAnimations.add(data);
    }

    public void playAnimation(Animation animation) {
        if (!isPlaying(animation)) {
            if (animatable instanceof Entity) {
                playAnimation(animation.getID());
            }else
            if (animatable instanceof TileEntity){
                AnimationHandler.INSTANCE.sendTileEntityAnimationMessage(this, animation, 0);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationTick(Animation animation,float tick){

    }

    public void tick() {
        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    public Animation getAnimation(int id) {
        for (Animation animation : getAnimations()) {
            if (animation.getID() == id){
                return animation;
            }
        }
        return null;
    }

    public AnimationData getAnimationData(Animation animation) {
        for (AnimationData data : getActiveAnimations()) {
            if (data.getId() == animation.getID()){
                return data;
            }
        }
        return null;
    }


    public List<AnimationData> getActiveAnimations() {
        return activeAnimations;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public T getAnimatable() {
        return animatable;
    }


    public void setAnimatable(T animatable) {
        this.animatable = animatable;
    }

    public void setAnimations(List<Animation> animations) {
        this.animations = animations;
    }
}



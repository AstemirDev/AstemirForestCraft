package org.astemir.api.client.animator;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.AstemirAPI;
import org.astemir.api.math.Triplet;
import org.astemir.api.math.Vector3;
import org.astemir.api.math.MathUtils;
import org.astemir.forestcraft.client.event.ClientEvents;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class Animator<T extends IAnimatedModel> {

    private final Map<String, AModelRenderer> bonesMap;
    private final Map<String, AnimationTrack> animations;

    public Animator(AnimationFile file,T model) {
        Map<String, AModelRenderer> bonesMap = new HashMap<>();
        for (Field declaredField : model.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(model);
                if (fieldValue instanceof AModelRenderer){
                    AModelRenderer bone = (AModelRenderer)fieldValue;
                    bonesMap.put(declaredField.getName(),bone);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.bonesMap = bonesMap;
        if (file != null) {
            this.animations = file.getAnimations();
        }else{
            this.animations = new HashMap<>();
        }
    }


    public void resetUnusedBones(List<AnimationData> activeAnimations, IAnimated entity){
        List<String> usedBones = new ArrayList<>();
        Map<String, Triplet<Boolean, Boolean, Boolean>> bonesOptions = new HashMap<>();
        for (AnimationData activeAnimation : activeAnimations) {
            AnimationTrack track = getTrack(activeAnimation,entity);
            if (track != null) {
                for (AnimationBone animationBone : track.getBonesList()) {
                    usedBones.add(animationBone.getBoneName());
                    boolean usingPositions = false;
                    boolean usingRotations = false;
                    boolean usingScales = false;
                    if (animationBone.getPositions() != null) {
                        usingPositions = true;
                    }
                    if (animationBone.getRotations() != null) {
                        usingRotations = true;
                    }
                    if (animationBone.getScales() != null) {
                        usingScales = true;
                    }
                    Triplet options = new Triplet(usingPositions, usingRotations, usingScales);
                    bonesOptions.put(animationBone.getBoneName(), options);
                }
            }
        }
        for (String boneName : bonesMap.keySet()) {
            if (!usedBones.contains(boneName)){
                bonesMap.get(boneName).reset();
            }else{
                AModelRenderer bone = bonesMap.get(boneName);
                Triplet<Boolean,Boolean,Boolean> option = bonesOptions.get(boneName);
                if (!option.getKey()) {
                    bone.resetPosition();
                }
                if (!option.getValue()) {
                    bone.resetRotation();
                }
                if (!option.getTranscription()) {
                    bone.resetScale();
                }
            }
        }
    }

    private AnimationTrack getTrack(AnimationData data, IAnimated animated){
        String name = "";
        for (Animation animatedAnimation : animated.getFactory().getAnimations()) {
            if (animatedAnimation.getID() == data.getId()){
                name = animatedAnimation.getName();
            }
        }
        return animations.get(name);
    }

    private AnimationTrack getTrack(Animation animation){
        return animations.get(animation.getName());
    }


    public void play(IAnimatedModel model,Animation animation,float delta){
        int time = (int)(animation.getTime()*animation.getSpeed());
        int ticks = (int) (AstemirAPI.CLIENT.TICKS % time);
        AnimationTrack track = getTrack(animation);
        int previousTime = ticks-1;
        if (previousTime < 0){
            previousTime = 0;
        }
        float previousNormalizedTime = ((float)previousTime/20)/animation.getSpeed();
        float normalizedTime = ((float)ticks/20)/animation.getSpeed();

        if (track != null) {
            track.getBonesList().forEach((b) -> {
                AModelRenderer modelRenderer = bonesMap.get(b.getBoneName());
                if (modelRenderer != null) {
                    if (model.animatePart(modelRenderer, animation, delta)) {
                        modelRenderer.using = false;
                        if (b.getPositions() != null) {
                            Vector3 newPos = MathUtils.interpolate(interpolatePoints(b.getPositions(), previousNormalizedTime), interpolatePoints(b.getPositions(), normalizedTime), delta);
                            modelRenderer.setPosition(newPos);
                            modelRenderer.positioning = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.positioning = false;
                        }
                        if (b.getRotations() != null) {
                            Vector3 newRot = MathUtils.interpolate(interpolatePoints(b.getRotations(), previousNormalizedTime), interpolatePoints(b.getRotations(), normalizedTime), delta);
                            newRot.setX(newRot.getX() + modelRenderer.defaultRotationX);
                            newRot.setY(newRot.getY() + modelRenderer.defaultRotationY);
                            newRot.setZ(newRot.getZ() + modelRenderer.defaultRotationZ);
                            modelRenderer.setRotation(newRot);
                            modelRenderer.rotating = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.rotating = false;
                        }
                        if (b.getScales() != null) {
                            Vector3 newScale = MathUtils.interpolate(interpolatePoints(b.getScales(), previousNormalizedTime), interpolatePoints(b.getScales(), normalizedTime), delta);
                            modelRenderer.setScale(newScale);
                            modelRenderer.scaling = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.scaling = false;
                        }
                    }
                }
            });
        }
    }

    public void play(IAnimatedModel model, AnimationData animation, IAnimated animated, float delta){
        AnimationTrack track = getTrack(animation,animated);
        int previousTime = animation.getTicks()-1;
        if (previousTime < 0){
            previousTime = 0;
        }
        float previousNormalizedTime = ((float)previousTime/20)/animation.getSpeed();
        float normalizedTime = ((float)animation.getTicks()/20)/animation.getSpeed();

        if (track != null) {
            track.getBonesList().forEach((b) -> {
                AModelRenderer modelRenderer = bonesMap.get(b.getBoneName());
                if (modelRenderer != null) {
                    if (model.animatePart(animated,modelRenderer,animated.getFactory().getAnimation(animation.getId()),delta)) {
                        modelRenderer.using = false;
                        if (b.getPositions() != null) {
                            Vector3 newPos = MathUtils.interpolate(interpolatePoints(b.getPositions(), previousNormalizedTime),interpolatePoints(b.getPositions(), normalizedTime),delta);
                            modelRenderer.setPosition(newPos);
                            modelRenderer.positioning = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.positioning = false;
                        }
                        if (b.getRotations() != null) {
                            Vector3 newRot = MathUtils.interpolate(interpolatePoints(b.getRotations(), previousNormalizedTime),interpolatePoints(b.getRotations(), normalizedTime),delta);
                            newRot.setX(newRot.getX() + modelRenderer.defaultRotationX);
                            newRot.setY(newRot.getY() + modelRenderer.defaultRotationY);
                            newRot.setZ(newRot.getZ() + modelRenderer.defaultRotationZ);
                            modelRenderer.setRotation(newRot);
                            modelRenderer.rotating = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.rotating = false;
                        }
                        if (b.getScales() != null) {
                            Vector3 newScale = MathUtils.interpolate(interpolatePoints(b.getScales(), previousNormalizedTime),interpolatePoints(b.getScales(), normalizedTime),delta);
                            modelRenderer.setScale(newScale);
                            modelRenderer.scaling = true;
                            modelRenderer.using = true;
                        } else {
                            modelRenderer.scaling = false;
                        }
                    }
                }
            });
        }
    }

    public static Vector3 interpolatePoints(AnimationFrame[] points, float position){
        float delta = 0;
        if (points.length == 0) {
            return Vector3.ZERO;
        }
        if (points.length == 1) {
            return points[0].getValue();
        }
        double firstPos = points[0].getPosition();
        double secondPos = points[points.length - 1].getPosition();
        Vector3 firstValue = points[0].getValue();
        Vector3 secondValue = points[points.length - 1].getValue();
        if (position < firstPos) {
            float c = (float) (delta * (firstPos - position));
            return firstValue.add(new Vector3(c,c,c).mul(-1));
        }
        else if (position > secondPos)
        {
            float c = (float) (delta * (position - secondPos));
            return secondValue.add(new Vector3(c,c,c));
        }

        for (int i = 1;i<points.length;i++) {
            AnimationFrame point = points[i];
            if (point.getPosition() >= position) {
                float num1 = (float)((position - firstPos) / (point.getPosition() - firstPos));
                float num2 = num1 * num1;
                float num3 = num2 * num1;
                Vector3 num4 = firstValue.mul(2.0f * num3 - 3.0f * num2 + 1.0f);
                float num5 = (float)(num3 - 2.0 * num2 + num1) * delta;
                Vector3 num6 = point.getValue().mul(3.0f * num2 - 2.0f * num3);
                Vector3 num7 = num4.add(new Vector3(num5,num5,num5));
                Vector3 num8 = num7.add(num6);
                float num9 = (num3 - num2) * delta;
                return num8.add(new Vector3(num9,num9,num9));
            }
            firstPos = point.getPosition();
            firstValue = point.getValue();
        }
        return Vector3.ZERO;
    }


    public AnimationTrack getAnimation(String name){
        return animations.get(name);
    }

    public AnimationTrack getAnimation(Animation animation){
        return animations.get(animation.getName());
    }



}

package org.astemir.api.client.animator;

import java.util.ArrayList;
import java.util.List;

public class AnimationTrack {

    private boolean loop = false;
    private double length = 2;
    private List<AnimationBone> bonesList;

    public AnimationTrack(boolean loop, double length) {
        this.loop = loop;
        this.length = length;
        this.bonesList = new ArrayList<>();
    }

    public void addBone(AnimationBone bone){
        this.bonesList.add(bone);
    }

    public boolean isLoop() {
        return loop;
    }

    public double getLength() {
        return length;
    }

    public List<AnimationBone> getBonesList() {
        return bonesList;
    }

    public AnimationBone getBone(String name){
        for (AnimationBone animationBone : bonesList) {
            if (animationBone.getBoneName().equals(name)){
                return animationBone;
            }
        }
        return null;
    }

    public List<AModelRenderer> getModelRenderers(IAnimatedModel model) {
        List<AModelRenderer> list = new ArrayList<>();
        for (AnimationBone animationBone : bonesList) {
            AModelRenderer modelRenderer = model.getModelRenderer(animationBone.getBoneName());
            if (modelRenderer != null){
                list.add(modelRenderer);
            }
        }
        return list;
    }

}

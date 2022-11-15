package org.astemir.api.client.animator;

import java.util.Set;

public interface IAnimatedModel<T> {

    public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta);

    public boolean animatePart(AModelRenderer part, Animation animation, float delta);

    public void initializeAnimator(AnimationFile file);

    public AModelRenderer getModelRenderer(String name);

    public Set<AModelRenderer> getCubes();
}

package org.astemir.api.client.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.IAnimated;

public class EntityAnimationEvent<T extends Entity & IAnimated> extends Event {

    protected Animation animation;
    private final T entity;

    EntityAnimationEvent(T entity, Animation animation) {
        this.entity = entity;
        this.animation = animation;
    }

    public T getEntity() {
        return this.entity;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    @Cancelable
    public static class Start<T extends Entity & IAnimated> extends EntityAnimationEvent<T> {

        public Start(T entity, Animation animation) {
            super(entity, animation);
        }

        public void setAnimation(Animation animation) {
            this.animation = animation;
        }
    }

    public static class Tick<T extends Entity & IAnimated> extends EntityAnimationEvent<T> {
        protected float tick;

        public Tick(T entity, Animation animation, float tick) {
            super(entity, animation);
            this.tick = tick;
        }

        public float getTick() {
            return this.tick;
        }
    }


    @Cancelable
    public static class End<T extends Entity & IAnimated> extends EntityAnimationEvent<T> {

        public End(T entity, Animation animation) {
            super(entity, animation);
        }

        public void setAnimation(Animation animation) {
            this.animation = animation;
        }
    }


}
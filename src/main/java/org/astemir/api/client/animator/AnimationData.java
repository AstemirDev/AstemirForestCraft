package org.astemir.api.client.animator;





public class AnimationData {

    private int id = 0;
    private int ticks = 0;
    private int previousTicks = 0;
    private float speed = 1;

    public AnimationData(int id, int ticks, float speed) {
        this.id = id;
        this.ticks = ticks;
        this.speed = speed;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getPreviousTicks() {
        return previousTicks;
    }

    public void setPreviousTicks(int previousTicks) {
        this.previousTicks = previousTicks;
    }

    public float getSpeed() {
        return speed;
    }

}

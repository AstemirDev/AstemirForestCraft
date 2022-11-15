package org.astemir.api.client.animator;

public class Animation {

    private int id;
    private String name;
    private boolean conflict = false;
    private boolean loop = false;
    private int time = 0;
    private float speed = 1;
    private int[] conflictsWith;

    public Animation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animation conflict(int... ids){
        conflict = true;
        conflictsWith = ids;
        return this;
    }

    public Animation loop(){
        loop = true;
        return this;
    }

    public Animation time(int time){
        this.time = time;
        return this;
    }

    public Animation time(float time){
        this.time = (int)(time*20);
        return this;
    }


    public Animation speed(float speed){
        this.speed = speed;
        return this;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isConflict() {
        return conflict;
    }

    public int[] getConflictsWith() {
        return conflictsWith;
    }

    public boolean isConflictsWith(int id) {
        for (int i : getConflictsWith()) {
            if (i == id){
                return true;
            }
        }
        return false;
    }

    public int getTime() {
        return time;
    }

    public boolean isLoop() {
        return loop;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "{" + name + '}';
    }

    public boolean equals(Animation obj) {
        return id == obj.id;
    }
}

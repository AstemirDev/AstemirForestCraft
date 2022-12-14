package org.astemir.api.client.animator;

import org.astemir.api.math.Vector3;

public class AnimationFrame {

    private Vector3 value;
    private float time;

    public AnimationFrame(float time, Vector3 value) {
        this.value = value;
        this.time = time;
    }

    public Vector3 getValue() {
        return value;
    }

    public float getPosition() {
        return time;
    }
}

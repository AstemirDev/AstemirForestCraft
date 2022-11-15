package org.astemir.api.math;


public class Rectangle {

    private Vector2 position;
    private Vector2 size;

    public Rectangle(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}

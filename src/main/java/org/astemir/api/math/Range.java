package org.astemir.api.math;

public class Range {

    private float beginValue;
    private float endValue;
    private float value;
    private float speed;
    private boolean reversed = false;

    public Range(float beginValue,float endValue,float speed){
        this.beginValue = beginValue;
        this.endValue = endValue;
        this.speed = speed;
    }

    public Range(float beginValue, float endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
        this.speed = 1;
    }

    public void update(){
        if (!reversed) {
            if (value < endValue) {
                value += speed;
            }else{
                reversed = true;
            }
        }else{
            if (value > beginValue){
                value-=speed;
            }else{
                reversed = false;
            }
        }
    }

    public void update(float partialTicks){
        if (!reversed) {
            if (value+partialTicks < endValue) {
                value += partialTicks;
            }else{
                value = endValue;
                reversed = true;
            }
        }else{
            if (value-partialTicks > beginValue){
                value-=partialTicks;
            }else{
                value = beginValue;
                reversed = false;
            }
        }
    }

    public float getBeginValue() {
        return beginValue;
    }

    public float getEndValue() {
        return endValue;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void reverse(){
        reversed = true;
    }
}

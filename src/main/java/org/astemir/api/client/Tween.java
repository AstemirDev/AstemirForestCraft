package org.astemir.api.client;


import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;
import org.astemir.api.math.MathUtils;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Tween {

    private CopyOnWriteArrayList<TweenRunnable> runnables = new CopyOnWriteArrayList<>();

    public float runTask(float a, float b, float t, Consumer<Float> func,Runnable r){
        createTween(a,b,t,func,r);
        return a;
    }

    public Vector2 runTask(Vector2 a,Vector2 b,float t,Consumer<Vector2> func,Runnable r){
        createTween(a,b,t,func,r);
        return a;
    }

    public Vector3 runTask(Vector3 a,Vector3 b,float t,Consumer<Vector3> func,Runnable r){
        createTween(a,b,t,func,r);
        return a;
    }

    public AColor runTask(AColor a, AColor b, float t, Consumer<AColor> func, Runnable r){
        createTween(a,b,t,func,r);
        return a;
    }

    public float runTask(float a, float b, float t, Consumer<Float> func){
        createTween(a,b,t,func,null);
        return a;
    }

    public Vector2 runTask(Vector2 a,Vector2 b,float t,Consumer<Vector2> func){
        createTween(a,b,t,func,null);
        return a;
    }

    public Vector3 runTask(Vector3 a,Vector3 b,float t,Consumer<Vector3> func){
        createTween(a,b,t,func,null);
        return a;
    }

    public AColor runTask(AColor a, AColor b, float t, Consumer<AColor> func){
        createTween(a,b,t,func,null);
        return a;
    }


    private TweenRunnable createTween(Object a,Object b,float t,Consumer func,Runnable r){
        TweenRunnable runnable = new TweenRunnable(a,b,t,func,r);
        runnables.add(runnable);
        return runnable;
    }


    private Object interpolate(Object a,Object b,float t){
        if (a instanceof Float && b instanceof Float){
            return MathUtils.interpolate((float)a,(float)b,t);
        }else
        if (a instanceof Vector2 && b instanceof Vector2){
            return ((Vector2) a).interpolate((Vector2) b,t);
        }else
        if (a instanceof Vector3 && b instanceof Vector3){
            return ((Vector3) a).interpolate((Vector3) b,t);
        }else
        if (a instanceof AColor && b instanceof AColor){
            return ((AColor) a).interpolate((AColor) b,t);
        }
        return null;
    }

    private boolean isApproximatelyEquals(Object a,Object b){
        if (a instanceof Float && b instanceof Float){
            return MathUtils.equalsApproximately((float)a,(float)b);
        }else
        if (a instanceof Vector2 && b instanceof Vector2){
            return MathUtils.equalsApproximately((Vector2) a,(Vector2)b);
        }else
        if (a instanceof Vector3 && b instanceof Vector3){
            return MathUtils.equalsApproximately((Vector3) a,(Vector3)b);
        }else
        if (a instanceof AColor && b instanceof AColor){
            return MathUtils.equalsApproximately((AColor) a,(AColor)b);
        }
        return false;
    }

    public void update(float partialTicks){
        for (TweenRunnable task : runnables) {
            task.value = interpolate(task.value,task.result,partialTicks*task.t);
            try {
                task.function.accept(task.value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isApproximatelyEquals(task.value,task.result)){
                runnables.remove(task);
                if (task.end != null) {
                    task.end.run();
                }
            }
        }
    }

    public void kill(){
        runnables.clear();
    }

    public static class TweenRunnable<T>{

        private T value;
        private T result;
        private float t;
        private Runnable end;
        private Consumer<T> function;

        public TweenRunnable(T value, T result, float t,Consumer<T> function,Runnable end) {
            this.value = value;
            this.result = result;
            this.t = t;
            this.function = function;
            this.end = end;
        }


        public T getValue() {
            return value;
        }
    }

}

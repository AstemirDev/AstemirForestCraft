package org.astemir.api.common;

import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager {

    private CopyOnWriteArrayList<Task> tasks = new CopyOnWriteArrayList<>();
    private long ticks = 0;

    public void update(){
        for (Task task : tasks) {
            if (ticks >= task.getEndTicks()) {
                task.getRunnable().run();
                tasks.remove(task);
            }
        }
        ticks++;
    }


    public void createTask(Runnable runnable,int delay){
        tasks.add(new Task(runnable, (int) (this.ticks+delay)));
    }

    private class Task{

        private Runnable runnable;
        private int endLife = 0;

        public Task(Runnable runnable,int endLife) {
            this.runnable = runnable;
            this.endLife = endLife;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public int getEndTicks() {
            return endLife;
        }
    }
}

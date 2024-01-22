package me.neptune.api.util.math;

public class StopWatch implements Passable {
    private volatile long time;

    @Override
    public boolean passed(long ms) {
        return System.currentTimeMillis() - time >= ms;
    }

    public StopWatch reset() {
        time = System.currentTimeMillis();
        return this;
    }
}

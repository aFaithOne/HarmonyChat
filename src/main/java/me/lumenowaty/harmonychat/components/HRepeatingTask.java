package me.lumenowaty.harmonychat.components;

public abstract class HRepeatingTask extends HTask {

    protected final int period;
    protected final int delayed;

    public HRepeatingTask(int period, int delayed) {
        this.period = period;
        this.delayed = delayed;
    }

    public int getPeriod() {
        return period;
    }

    public int getDelayed() {
        return delayed;
    }
}

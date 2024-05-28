package com.cycas.design.state;

/**
 * @author xin.na
 * @since 2024/5/14 9:39
 */
public class Work {

    private int hour;
    private State current;
    private boolean workFinish;

    public Work() {
        current = new ForenoonState();
    }

    public void writeProgram() {
        this.current.writeProgram(this);
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void setState(State current) {
        this.current = current;
    }

    public void setWorkFinish(boolean workFinish) {
        this.workFinish = workFinish;
    }

    public boolean getWorkFinish() {
        return workFinish;
    }
}

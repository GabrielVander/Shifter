package com.pdm.shifter.model;

public class Punch {

    private Long time;
    private String userId;
    private String type;
    private boolean isDatePunch = false;

    public Punch() {
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDatePunch() {
        return isDatePunch;
    }

    public void setDatePunch(boolean datePunch) {
        isDatePunch = datePunch;
    }
}

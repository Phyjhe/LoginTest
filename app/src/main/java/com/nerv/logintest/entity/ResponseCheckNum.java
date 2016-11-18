package com.nerv.logintest.entity;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ResponseCheckNum {

    /**
     * status : 0
     * exists : false
     * message : 手机号不存在
     */

    private int status;
    private boolean exists;
    private String message;
    /**
     * timeleft : 60
     */

    private int timeleft;
    /**
     * correct : false
     */

    private boolean correct;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}

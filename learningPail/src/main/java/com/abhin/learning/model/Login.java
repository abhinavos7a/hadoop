package com.abhin.learning.model;

/**
 * Created by abhin on 3/2/15.
 */
public class Login {

    private String userName;
    private long unixTime;

    public Login(String userName, long unixTime){
        this.userName = userName;
        this.unixTime = unixTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public String toString(){
        return new String(userName + "\n" + unixTime);
    }
}

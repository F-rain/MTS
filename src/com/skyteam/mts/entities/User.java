package com.skyteam.mts.entities;

/**
 * Created by rick- on 2017/6/29.
 */
public class User {
    private String UserID;
    private String UserName;
    private String Password;
    private String UserTel;
    private int UserLevel;
    private double RestMoney;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserTel() {
        return UserTel;
    }

    public void setUserTel(String userTel) {
        UserTel = userTel;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(int userLevel) {
        UserLevel = userLevel;
    }

    public double getRestMoney() {
        return RestMoney;
    }

    public void setRestMoney(double restMoney) {
        RestMoney = restMoney;
    }


}

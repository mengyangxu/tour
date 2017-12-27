package com.xmy.bean;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:34 2017/12/27
 */
public class Friend {
    private int user_id;
    private int friend_id;
    private String information; //验证消息
    private int state; //状态 1请求添加 2拒绝请求 3同意并成为好友

    public Friend() {
    }

    public Friend(int user_id, int friend_id, String information, int state) {
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.information = information;
        this.state = state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

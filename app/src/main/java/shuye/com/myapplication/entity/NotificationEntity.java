package shuye.com.myapplication.entity;

import java.io.Serializable;

public class NotificationEntity implements Serializable {
    int id;
    String not_titel;
    String not_content;
    String not_time;
    boolean isshow;
    boolean ischeck;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsshow() {
        return isshow;
    }

    public void setIsshow(boolean isshow) {
        this.isshow = isshow;
    }

    public String getNot_titel() {
        return not_titel;
    }

    public void setNot_titel(String not_titel) {
        this.not_titel = not_titel;
    }

    public String getNot_content() {
        return not_content;
    }

    public void setNot_content(String not_content) {
        this.not_content = not_content;
    }

    public String getNot_time() {
        return not_time;
    }

    public void setNot_time(String not_time) {
        this.not_time = not_time;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}

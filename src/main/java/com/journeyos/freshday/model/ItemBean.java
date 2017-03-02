package com.journeyos.freshday.model;

/**
 * Created by mike.li on 2017/2/20.
 */

public class ItemBean {
    private String time;
    private String name;
    private String tab;
    private int type;

    public ItemBean() {
    }

    public ItemBean(String time, String name, String tab, int type) {
        this.time = time;
        this.name = name;
        this.tab = tab;
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

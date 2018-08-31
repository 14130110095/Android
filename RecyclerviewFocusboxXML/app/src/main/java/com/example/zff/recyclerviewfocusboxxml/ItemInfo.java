package com.example.zff.recyclerviewfocusboxxml;

/**
 * Created by zff on 2018/6/21.
 */

public class ItemInfo {
    private int picture;
    private String picname;
    private boolean isSelected;

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getPicture() {
        return picture;
    }

    public String getPicname() {
        return picname;
    }

    public boolean isSelected() {
        return isSelected;
    }
}

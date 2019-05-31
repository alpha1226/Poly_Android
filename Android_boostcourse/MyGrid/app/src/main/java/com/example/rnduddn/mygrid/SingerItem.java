package com.example.rnduddn.mygrid;

public class SingerItem {
    String name;
    String mobile;
    int resId;

    public SingerItem(String name, String mobile, int resid) {
        this.name = name;
        this.mobile = mobile;
        this.resId= resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

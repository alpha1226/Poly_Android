package com.example.a0420space;

public class MyMissile {
    int x,y;
    int missileSpeed=35;

    public MyMissile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(){
        y-=missileSpeed;
    }
}
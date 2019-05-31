package com.example.a0420space;

public class Planet {
    int x,y;
    int planetSpeed=15;

    public Planet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(){
        y+=planetSpeed;
    }
}

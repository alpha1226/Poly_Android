package com.example.a0419_mathgame;

import android.graphics.Canvas;
import android.support.constraint.solver.widgets.WidgetContainer;

import java.util.ArrayList;
import java.util.Random;

public class Balloon {
    int x,y;
    int speed;


    public Balloon(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move(){
        y+=speed;
    }
}

package com.example.a0419_mathgame;

public class AnswerBalloon {
    int x,y;
    int speed;

    public AnswerBalloon(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move(){
        y+=speed;
    }
}

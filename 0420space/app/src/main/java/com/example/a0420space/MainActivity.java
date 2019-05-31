package com.example.a0420space;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Bitmap spaceship;
    int spaceship_x, spaceship_y;
    int spaceshipWidth; // 우주선 가로크리
    Bitmap leftKey, rightKey;
    int leftKey_x, leftKey_y, rightKey_x, rightKey_y;
    int Width, Height;
    int score;
    int button_width;

    Bitmap missileButton;
    int missilebutton_x, missileButton_y;
    int missileWidth;
    int missile_middle;
    Bitmap missile;
    Bitmap planetimg;

    int count;
    ArrayList<MyMissile> myM = new ArrayList<MyMissile>();
    ArrayList<Planet> planet = new ArrayList<Planet>();

    Bitmap screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        Width=display.getWidth();
        Height = display.getHeight();

        spaceship = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);

        int x=Width/8;
        int y=Height/11;

        spaceship=Bitmap.createScaledBitmap(spaceship,x,y,true);

        spaceship_x = Width*1/9;
        spaceship_y=Height*6/9;

        leftKey=BitmapFactory.decodeResource(getResources(),R.drawable.leftkey);
        leftKey_x=Width*5/9;
        leftKey_y=Height*7/9;
        button_width=Width/6;

        leftKey = Bitmap.createScaledBitmap(leftKey,button_width,button_width,true);

        rightKey=BitmapFactory.decodeResource(getResources(),R.drawable.rightkey);
        rightKey_x=Width*7/9;
        rightKey_y=Height*7/9;
        rightKey=Bitmap.createScaledBitmap(rightKey,button_width,button_width,true);

        missileButton = BitmapFactory.decodeResource(getResources(),R.drawable.missilebutton);
        missileButton = Bitmap.createScaledBitmap(missileButton,button_width,button_width,true);
        missilebutton_x = Width*1/11;
        missileButton_y=Height*7/9;

        missile=BitmapFactory.decodeResource(getResources(),R.drawable.missile0);
        missile = Bitmap.createScaledBitmap(missile,button_width/4,button_width/4,true);
        missileWidth=missile.getWidth();

        planetimg = BitmapFactory.decodeResource(getResources(),R.drawable.planet);
        planetimg=Bitmap.createScaledBitmap(planetimg,button_width,button_width,true);

        screen=BitmapFactory.decodeResource(getResources(),R.drawable.screen);
        screen=Bitmap.createScaledBitmap(screen,Width,Height,true);
    }

    class MyView extends View {
        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.BLUE);
            ghandler.sendEmptyMessageDelayed(0,1000);
        }

        @Override
        synchronized protected void onDraw(Canvas canvas) {
            Random r1=new Random();
            int x = r1.nextInt(Width);

            if(planet.size()<5) planet.add(new Planet(x,-100));


            Paint p1 = new Paint();
            p1.setColor(Color.RED);
            p1.setTextSize(50);
            canvas.drawBitmap(screen,0,0,p1);

            canvas.drawText(Integer.toString(count),0,300,p1);
            canvas.drawText("점수 : "+Integer.toString(score),0,200,p1);

            canvas.drawBitmap(spaceship,spaceship_x,spaceship_y,p1);
            canvas.drawBitmap(leftKey,leftKey_x,leftKey_y,p1);
            canvas.drawBitmap(rightKey,rightKey_x,rightKey_y,p1);

            canvas.drawBitmap(missileButton,missilebutton_x,missileButton_y,p1);
            for(MyMissile tmp:myM)
                canvas.drawBitmap(missile,tmp.x,tmp.y,p1);
            for(Planet tmp:planet)
                canvas.drawBitmap(planetimg,tmp.x,tmp.y,p1);

            moveMissile();
            movePlanet();
            checkCollision();
            count++;
        }

        public void moveMissile(){
            for(int i=myM.size()-1;i>=0;i--){
                myM.get(i).move();
            }
            for(int i=myM.size()-1;i>=0;i--){
                if(myM.get(i).y<0) myM.remove(i);
            }
        }

        public void movePlanet(){
            for(int i=planet.size()-1;i>=0;i--){
                planet.get(i).move();
            }
            for(int i=planet.size()-1;i>=0;i--){
                if(planet.get(i).y>Height) planet.remove(i);
            }
        }

        public void checkCollision(){
            for(int i=planet.size()-1;i>0;i--){
                for(int j=myM.size()-1;j>=0;j--){
                    if(
                            myM.get(j).x+missile_middle>planet.get(i).x
                            && myM.get(j).x+missile_middle<planet.get(i).x+button_width
                            && myM.get(j).y>planet.get(i).y
                            && myM.get(j).y<planet.get(i).y+button_width
                    ) {
                        planet.remove(i);
                        myM.get(j).y=-30;
                        score+=10;
                    }
                }
            }
        }
        Handler ghandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                invalidate();
                ghandler.sendEmptyMessageDelayed(0,30);
            }
        };


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int x=0,y=0;

            if(event.getAction()==MotionEvent.ACTION_DOWN||
            event.getAction()== MotionEvent.ACTION_MOVE){
                x=(int) event.getX();
                y=(int) event.getY();
            }

            if((x>leftKey_x&&(x<leftKey_x+button_width)&&(y>leftKey_y)&&(x<leftKey_y+button_width))) spaceship_x-=20;

            if((x>rightKey_x)&&(x<rightKey_x+button_width)&&(y>rightKey_y)&&(x<rightKey_y+button_width)) spaceship_x+=20;

            if(event.getAction()==MotionEvent.ACTION_DOWN)
                if((x>missilebutton_x)&&(x<missilebutton_x+button_width)&&(y>missileButton_y)&&(x<missileButton_y+button_width))
                    if(myM.size()<1){
                        myM.add(new MyMissile(spaceship_x+spaceshipWidth/2 - missileWidth/2,spaceship_y));
                    }
                    return true;
        }
    }
}

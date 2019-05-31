package com.example.a0412_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class Changer extends AppCompatActivity {
    ViewFlipper viewFlipper;
   // ViewFlipper viewFlipper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnBack = findViewById(R.id.back);
        Button btnStart = findViewById(R.id.start);
        Button btnStop = findViewById(R.id.stop);
        Button btnExit = findViewById(R.id.exit);
        viewFlipper=findViewById(R.id.viewFlipper);
//        viewFlipper2=findViewById(R.id.viewFlipper1);

        viewFlipper.setFlipInterval(1000);
//        viewFlipper2.setFlipInterval(1000);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
  //              viewFlipper2.showNext();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
    //            viewFlipper2.showPrevious();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.startFlipping();
      //          viewFlipper2.startFlipping();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
        //        viewFlipper2.stopFlipping();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

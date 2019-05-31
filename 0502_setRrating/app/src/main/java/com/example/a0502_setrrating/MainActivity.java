package com.example.a0502_setrrating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final RatingBar ratingBar1 = findViewById(R.id.ratingBar2);

        Button btn_Plus = findViewById(R.id.plus);
        Button btn_Min = findViewById(R.id.min);

        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(ratingBar.getRating()+ratingBar.getStepSize());
                ratingBar1.setRating(ratingBar1.getRating()+ratingBar1.getStepSize());
            }
        });
        btn_Min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(ratingBar.getRating()-ratingBar.getStepSize());
                ratingBar1.setRating(ratingBar1.getRating()-ratingBar1.getStepSize());
            }
        });
    }
}

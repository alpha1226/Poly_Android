package com.example.midtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        final String ID = getIntent().getStringExtra("ID");

        ImageButton backtothemain = findViewById(R.id.BackToTheMain);
        backtothemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyMain.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
    }
}

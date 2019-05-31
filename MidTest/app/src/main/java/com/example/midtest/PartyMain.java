package com.example.midtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PartyMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_main);
        final EditText editText = findViewById(R.id.editText2);

        final String ID = getIntent().getStringExtra("ID");
        Toast.makeText(getApplicationContext(),ID,Toast.LENGTH_LONG).show();

        ImageButton gotoMain = findViewById(R.id.imageButton);
        gotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });

        Button btn_memo = findViewById(R.id.button4);
        btn_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MemoView.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });

        Button btn_find = findViewById(R.id.btn_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setBackgroundColor(Color.WHITE);
                editText.setVisibility(View.VISIBLE);
            }
        });
    }
}

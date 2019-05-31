package com.example.midtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText editText_id;
    EditText editText_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_id = findViewById(R.id.LoginText_ID);
        editText_pw = findViewById(R.id.LoginText_PW);

        Button login_button = findViewById(R.id.Login_btn);
        Button Signin_button = findViewById(R.id.Login_SignIn);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyMain.class);
                String IDValue = editText_id.getText().toString();
                intent.putExtra("ID",IDValue);
                startActivity(intent);
            }
        });

        Signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signin.class);
                startActivity(intent);
            }
        });

    }
}

package com.example.rnduddn.toyprojectapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button LoginButton = (Button) findViewById(R.id.Login);
        Button AccessButton = (Button) findViewById(R.id.Access);
        Button IDPWButton = (Button)findViewById(R.id.IDPW);



    }
}

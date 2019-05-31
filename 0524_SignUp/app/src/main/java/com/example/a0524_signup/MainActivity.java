package com.example.a0524_signup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHealper myDBHealper;
    EditText edtID, edtPW, edtEmail, edtBirthday;
    Button SignUP, SignIN;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.editText);
        edtPW = findViewById(R.id.editText2);
        edtEmail = findViewById(R.id.editText3);
        edtBirthday = findViewById(R.id.editText4);
        SignUP = findViewById(R.id.button);
        SignIN = findViewById(R.id.button2);
        myDBHealper = new myDBHealper(this);

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHealper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO MEMBER VALUES('"
                        +edtID.getText().toString()+"' , '"
                        +edtPW.getText().toString()+"' , '"
                        +edtEmail.getText().toString()+"','" +
                        edtBirthday.getText().toString()+"');");
                sqlDB.close();
                Toast.makeText(MainActivity.this, "회원가입이 완료되었습니다."+edtID.getText().toString()+edtPW.getText().toString()+edtEmail.getText().toString()+edtBirthday.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        SignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHealper.getReadableDatabase();
                Cursor cursor=sqlDB.rawQuery("SELECT * FROM MEMBER;",null);

                String strID = "ID : "+"\r\n"+"-------------"+"\r\n";
                String strPW = "PW : "+"\r\n"+"-------------"+"\r\n";
                String strEmail = "Email : "+"\r\n"+"-------------"+"\r\n";
                String strBirth = "BirthDay : "+"\r\n"+"-------------"+"\r\n";

                while(cursor.moveToNext()){
                    strID += cursor.getString(0)+"\r\n";
                    strPW += cursor.getString(1)+"\r\n";
                    strEmail += cursor.getString(2)+"\r\n";
                    strBirth += cursor.getString(3)+"\r\n";
                }

                Log.d("ID",strID);
                Log.d("PW",strPW);
                Log.d("Email",strEmail);
                Log.d("Birth",strBirth);

                cursor.close();
                sqlDB.close();

            }
        });

        Button createTB = findViewById(R.id.button4);
        createTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHealper.getWritableDatabase();
                sqlDB.execSQL("CREATE TABLE MEMBER(memid char(20), mempw char(20), mememail char(20), membirth char(20));");
            }
        });
        Button dropTB = findViewById(R.id.button5);
        dropTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHealper.getWritableDatabase();
                sqlDB.execSQL("DROP TABLE MEMBER;");
            }
        });
    }

    public class myDBHealper extends SQLiteOpenHelper{
        public myDBHealper(Context context){
            super(context, "groupDB",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            sqlDB.execSQL("CREATE TABLE MEMBER(memid char(20), mempw char(20), mememail char(20), membirth char(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS MEMBER");
            onCreate(db);
        }
    }
}

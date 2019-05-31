package com.example.a0524_dbsam;

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

    myDBHelper myDBHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");
        myDBHelper = new myDBHelper(this);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);
        btnInit = findViewById(R.id.btnInit); // 초기화
        btnInsert = findViewById(R.id.btnInsert); // 입력
        btnSelect = findViewById(R.id.btnSelect); // 조회

        btnInit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqlDB = myDBHelper.getWritableDatabase();
                    myDBHelper.onUpgrade(sqlDB,1,2);
                    sqlDB.close();
                }
            });
        btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqlDB=myDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO groupTBL VALUES("+edtName.getText().toString()+","+edtNumber.getText().toString()+");");
                    sqlDB.close();
                    Toast.makeText(MainActivity.this, "입력됨", Toast.LENGTH_SHORT).show();
                }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null);

                String strNames="그룹이름"+"\r\n"+"---------------"+"\r\n";
                String strNumbers="인원"+"\r\n"+"---------------"+"\r\n";

                while(cursor.moveToNext()){
                    strNames+=cursor.getString(0)+"\r\n";
                    strNumbers+=cursor.getString(1)+"\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });
    }



    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context) {
            super(context,"groupDB",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL(gName CHAR(20), gNumber INTEGER);");
            Log.d("Main","테이블 생성");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}

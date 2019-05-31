package com.example.rnduddn.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SQLiteDatabase database;

    EditText editText2;

    EditText editText3;
    EditText editText4;
    EditText editText5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                openDatabase(databaseName);
            }
        });


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TableName = editText2.getText().toString();
                createTable(TableName);
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = -1;
                try{
                    age = Integer.parseInt(ageStr);
                }catch(Exception e){}

                insertData(name,age,mobile);
            }
        });

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TableName = editText2.getText().toString();
                selectData(TableName);
            }
        });


    }

    public void openDatabase(String databaseName){
        println("openDatabase 호출됨");

        database = openOrCreateDatabase(databaseName,MODE_PRIVATE,null);
        if(database!=null){
            println("Database 오픈됨");
        }
    }

    public void createTable(String TableName){
        println("createTable 호출됨");

        if(database!=null) {
            String sql = "create table " + TableName + "(_id integer PRIMARY KEY autoincrement, Name text, age integer, mobile text)";
            database.execSQL(sql);

            println("테이블 생성됨.");
        }else{
            println("먼저 DB를 생성하세요");
        }
    }


    public void insertData(String name, int age, String mobile){
        println("insertData()  호출됨");

        if(database!=null){
            String sql = "INSERT into customer(name,age,mobile) values(?,?,?)";
            Object[] params = {name, age, mobile};

            //execSQL 실행시 sql문의 ?를 대신해 params 입력
            database.execSQL(sql,params);

            println("데이터 추가됨"+name+age+mobile);
        }
    }

    public void selectData(String TableName) {
        println("selectData() 호출됨");
        if(database!=null){
            String sql="select name, age,mobile from "+TableName;
            Cursor cursor = database.rawQuery(sql,null);
            println("조회된 데이터 갯수 : "+cursor.getCount());

            for(int i=0; i<cursor.getCount();i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#"+i+"->"+name+", "+age+", "+mobile);
            }

            cursor.close();
        }
    }


    public void println(String data){
        textView.append(data+"\n");
    }
}

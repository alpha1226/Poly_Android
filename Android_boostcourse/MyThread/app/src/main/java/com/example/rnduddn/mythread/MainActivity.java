package com.example.rnduddn.mythread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    ProgressBar progressBar;
    //int value = 0;

    ValueHandler valueHandler = new ValueHandler();

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BackgroundThread thread = new BackgroundThread();
               // thread.start();

                /*new Thread(new Runnable() {
                        int value=0;
                        boolean running=false;

                        public void run() {
                            running = true;
                            while (running) {
                                value += 1;

                                handler.post(new Runnable(){
                                    public void run() {
                                        textView.setText("현재값 : "+value);
                                    }
                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {}
                            }
                        }
                }).start();
               */

                ProgressTask task = new ProgressTask();
                task.execute("시작");

            }
        });


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        textView.setText("현재값 : "+value);
            }
        });
    }


    class ProgressTask extends AsyncTask<String, Integer, Integer> {
        int value = 0;

        @Override
        protected Integer doInBackground(String... strings) {  //스레드 안에 넣을 코드
            while (true) {
                if (value > 100) {
                    break;
                }
                value += 1;

                publishProgress(value);

                try {
                    Thread.sleep(200);
                } catch (Exception e) { }

            }
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {  //UI업데이트코드
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Integer integer) {  //완료
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(), "완료됨", Toast.LENGTH_SHORT).show();
        }

    }



    class BackgroundThread extends Thread {
        int value=0;
        boolean running=false;

        public void run(){
            running = true;
            while(running){
                value +=1;

                Message message = valueHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                valueHandler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                }catch (Exception e){}
            }
        }
    }

    class ValueHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재값 : "+value);
        }
    }

}

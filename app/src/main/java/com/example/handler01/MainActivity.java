package com.example.handler01;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String tag = "mytag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textA = findViewById(R.id.textview_A);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.v(tag,"yellow");
                textA.setText(msg.arg1+"");
                Log.v(tag,"test");
            }
        };
        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 10;

                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = 0;
                Log.v(tag,"handler.sendMessage(msg)");
                handler.sendMessage(msg);
            }
        };
        Button btn = findViewById(R.id.btn_A);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(tag,"点击触发");
                Thread workThread = new Thread(null,myWorker,"WorkThread");
                workThread.start();
            }
        });
    }
}
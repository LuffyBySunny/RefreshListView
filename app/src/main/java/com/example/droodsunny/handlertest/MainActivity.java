package com.example.droodsunny.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button button;
    Handler mainHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      button=findViewById(R.id.button);

        //给子线程发送消息
        //匿名内部类
        myThread runnable=new myThread();
        runnable.start();
        Handler handler=new Handler();
        //要在线程开启的一段时间后
        handler.postDelayed(() -> {
            Message message=new Message();
            message.what=1;
            runnable. mHandler.sendMessage(message);
        },1000);
        button.setOnClickListener(v->{

        });
    }


    class myThread extends Thread{
        Handler mHandler;
        @Override
        public void run() {
            Looper.prepare();
           mHandler =new Handler(msg -> {
               switch (msg.what){
                   case 1:
                       //子线程收到消息后给主线程应答
                       //这个handler是运行在主线程中的handler
                       mainHandler=new Handler(Looper.getMainLooper()) {
                           @Override
                           public void handleMessage(Message msg) {
                              button.setText("jj");
                           }
                       };
                       Message message1=new Message();
                       message1.what=2;
                       mainHandler.sendMessage(message1);
               }
               return true;
           });
            Looper.loop();
        }
    }
}

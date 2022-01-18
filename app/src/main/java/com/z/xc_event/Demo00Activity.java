package com.z.xc_event;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
    android.view.View.dispatchTouchEvent

    android.view.View.onTouchEvent
        onClick的打印是在onTouch的action==1(ACTION_UP)后输出的
 */
public class Demo00Activity extends AppCompatActivity {

    private static final String TAG = "Demo00Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo00);
        init();
    }

    /*
        onTouch返回值的作用
            表示此事件是否已经被消费
                true 已经被消费
                false 未被消费

        onClick与onTouch谁先回调
            onTouch先执行, onClick根据onTouch的返回值判断是否需要执行

            onTouch在哪里调用
                android.view.View.dispatchTouchEvent

        看源码之前，先搞清楚基础使用，再看源码
     */
    private void init() {

        Button btn = findViewById(R.id.btn_test);

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch: " + event.getAction());
                return false;
            }
        });

        /*
            onTouch返回true
                点击事件已经被onTouch消费掉，所以不会再触发onClick

            onTouch返回false
                点击
                    E/Demo00Activity: onTouch: 0
                    E/Demo00Activity: onTouch: 1
                    E/Demo00Activity: onClick
                压下 -> 移动 -> 抬起
                    情况一
                        E/Demo00Activity: onTouch: 0
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 1
                    情况二
                        E/Demo00Activity: onTouch: 0
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 2
                        E/Demo00Activity: onTouch: 1
                        E/Demo00Activity: onClick
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick");
            }
        });

//        btn.setEnabled();
//        btn.setOnClickListener();
//        btn.setOnLongClickListener();
//        btn.setOnContextClickListener();
//        btn.setOnTouchListener();
    }
}
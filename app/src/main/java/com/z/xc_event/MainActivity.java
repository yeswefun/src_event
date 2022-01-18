package com.z.xc_event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/*
    shift + click  关闭文件

    Ctrl＋Shift＋J 合并选择内容到一行

    Ctrl+B 快速打开光标处的类或方法

    Ctrl＋Alt＋T可以把代码包在一块内，例如try/catch

    Ctrl＋[或]可以跳到大括号的开头结尾

    Alt+ left/right 切换代码视图

    Alt+ Up/Down 在方法间快速移动定位

    Ctrl+Alt+ left/right 返回至上次浏览的位置

    Ctrl＋Shift＋Backspace可以跳转到上次编辑的地方
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.btn_demo00:
                startActivity(new Intent(this, Demo00Activity.class));
                break;
            case R.id.btn_demo01:
                startActivity(new Intent(this, Demo01Activity.class));
                break;
        }
    }
}
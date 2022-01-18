package com.z.xc_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        // 情况三
//        return false;
//    }

    /*
        内部拦截法
            谁拦截，谁处理
     */
//    private int mLastX, mLastY;
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        Log.e("fuck", "MyListView#dispatchTouchEvent: " + ev.getAction());
//
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                // 请求不要拦截，进入第二个代码块，让子View去处理
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    // 如果用户左右滑动屏幕，不进入第二个代码块，让父容器去处理
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.e("fuck", "MyListView#dispatchTouchEvent: ACTION_CANCEL");
//                break;
//        }
//
//        mLastX = x;
//        mLastY = y;
//
//        return super.dispatchTouchEvent(ev);
//    }

}

package com.z.xc_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/*
    事件分发流程
        Activity
        PhoneWindow
        DecorView#dispatchTouchEvent
            DecorView#onInterceptTouchEvent
        MyViewPager#dispatchTouchEvent
            MyViewPager#onInterceptTouchEvent
        MyListView#dispatchTouchEvent
            MyListView#onInterceptTouchEvent
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        /*
//            默认情况下
//                ViewPager左右可以滑动
//                ListView上下可以滑动
//         */
//        //return super.onInterceptTouchEvent(ev);
//
//
//        /*
//            情况一
//                ViewPager左右可以滑动
//                ListView上下不可以滑动
//         */
//        //return true;
//
//
//        /*
//            情况二
//                ViewPager左右不可以滑动
//                ListView上下可以滑动
//            没有重写MyListView#dispatchTouchEvent
//         */
//        //return false;
//
//
//        /*
//            情况三
//                ViewPager左右可以滑动
//                ListView上下不可以滑动
//            重写MyListView#dispatchTouchEvent
//                并返回false
//         */
//        return false;
//    }

    /*
        配合 内部拦截法 查看事件分发的调用顺序
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("fuck", "MyViewPager#dispatchTouchEvent: " + ev.getAction());
//        return super.dispatchTouchEvent(ev);
//    }


    /*
        内部拦截法
            谁拦截，谁处理

            内部拦截法的思路就是父容器不拦截任何事件，所有事件都传递给子元素，
                如果子元素需要此事件就直接消耗掉，否则就交由父容器处理。
                我们需要重写子元素的dispatchTouchEvent方法，
                在其中调用getParent().requestDisallowInterceptTouchEvent(true)，
                作用是告诉父view，这个触摸事件由我来处理，不要阻碍我。

            requestDisallowInterceptTouchEvent方法通过改变FLAG_DISALLOW_INTERCEPT标记位拦截事件，
                但是ACTION_DOWN事件不受这个标记位的控制

            内部拦截法
                E/fuck: MyViewPager#dispatchTouchEvent: 0
                E/fuck: MyViewPager#onInterceptTouchEvent: 0
                E/fuck: MyListView#dispatchTouchEvent: 0
                E/fuck: MyViewPager#dispatchTouchEvent: 2
                E/fuck: MyListView#dispatchTouchEvent: 2
                E/fuck: MyViewPager#dispatchTouchEvent: 2
                E/fuck: MyViewPager#onInterceptTouchEvent: 2
                E/fuck: MyListView#dispatchTouchEvent: 3
                E/fuck: MyListView#dispatchTouchEvent: ACTION_CANCEL
                E/fuck: MyViewPager#dispatchTouchEvent: 2
                E/fuck: MyViewPager#dispatchTouchEvent: 2
                E/fuck: MyViewPager#dispatchTouchEvent: 2
                E/fuck: MyViewPager#dispatchTouchEvent: 1
     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        Log.e("fuck", "MyViewPager#onInterceptTouchEvent: " + ev.getAction());
//
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            /*
//                ACTION_DOWN肯定会在ViewGroup中执行intercepted = onInterceptTouchEvent(ev);
//                    此时必须要返回false
//
//                super.onInterceptTouchEvent(ev);的作用是什么呀?
//                    默认返回true
//                    若是返回true, 就不会分发事件给子view, 而是自己处理掉
//             */
//            super.onInterceptTouchEvent(ev); // TODO: 为什么还要执行一下父类的方法呢?
//            return false;
//        }
//
//        // 子View允许拦截，则父容器就拦截，交由ViewPager -> ViewGroup去处理
//        return true;
//    }


    /*
        外部拦截法
            水平滑动, ViewPager来处理
            垂直滑动, ListView来处理

        对于ACTION_DOWN事件，父容器必须返回false，即不拦截ACTION_DOWN事件，
            这是因为一旦父容器拦截者这个事件，那么后续的ACTION_MOVE和ACTION_UP事件都会直接交由父容器处理，
            这个时候就没法传递给子元素了
        对于ACTION_UP事件，父容器也必须返回false，否则就会导致子元素无法接收到ACTION_UP事件，
            这个时候onClick事件就无法触发
        对于ACTION_MOVE事件，这个事件可以根据需要来决定是否拦截
     */
    private int mLastX, mLastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);

        //boolean intercepted = false;

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            int deltaX = x - mLastX;
            int deltaY = y - mLastY;
            if (Math.abs(deltaX) < Math.abs(deltaY)) { // 垂直滑动时，不拦截ListView的事件
                return false;
            }
            Log.e("fuck", "deltaX: " + deltaX + ", deltaY: " + deltaY);
            //Log.e("fuck", "intercepted: " + intercepted + ", deltaX: " + deltaX + ", deltaY: " + deltaY);
        }

        mLastX = x;
        mLastY = y;

        //return intercepted;

        //return true; // TODO: 为什么这里直接返回true不行呢?
        return super.onInterceptTouchEvent(ev);

        // 下面是在方法体内直接返回true/false的结果
        //return true; // 单单这一句，ViewPager可以左右滑动，但ListView不可以上下滑动
        //return false; // 单单这一句，ViewPager不可以左右滑动，但ListView可以上下滑动
    }
}

package com.z.xc_event;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.z.xc_event.secondary.MyListViewAdapter;
import com.z.xc_event.secondary.MyListViewItem;
import com.z.xc_event.secondary.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
    android.view.ViewGroup.dispatchTouchEvent

    android.view.ViewGroup.onInterceptTouchEvent

    事件流
        Event -> activity -> LinearLayout -> ViewPager -> ListView -> View
 */
public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo01);
        init();
    }

    private void init() {
        MyViewPager vp = findViewById(R.id.my_viewpager);
        vp.setAdapter(new MyPagerAdapter(getListViewList()));
    }

    private List<MyListView> getListViewList() {
        List<MyListView> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MyListView myListView = new MyListView(this);
            myListView.setAdapter(new MyListViewAdapter(this, getItemList()));
            list.add(myListView);
        }
        return list;
    }

    private List<MyListViewItem> getItemList() {
        List<MyListViewItem> itemList = new ArrayList<>();
        itemList.add(new MyListViewItem(R.drawable.img_00));
        itemList.add(new MyListViewItem(R.drawable.img_01));
        itemList.add(new MyListViewItem(R.drawable.img_02));
        itemList.add(new MyListViewItem(R.drawable.img_03));
        itemList.add(new MyListViewItem(R.drawable.img_04));
        itemList.add(new MyListViewItem(R.drawable.img_05));
        itemList.add(new MyListViewItem(R.drawable.img_06));
        itemList.add(new MyListViewItem(R.drawable.img_07));
        itemList.add(new MyListViewItem(R.drawable.img_08));
        itemList.add(new MyListViewItem(R.drawable.img_09));
        itemList.add(new MyListViewItem(R.drawable.img_10));
        itemList.add(new MyListViewItem(R.drawable.img_11));
        return itemList;
    }
}
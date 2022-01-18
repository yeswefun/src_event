package com.z.xc_event.secondary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.z.xc_event.R;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyListViewItem> mItemList;

    public MyListViewAdapter(Context context, List<MyListViewItem> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getImageResId();
    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        ViewHolder viewHolder;
        if (convertView == null) {
            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_for_listview, parent, false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.item_img);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        MyListViewItem item = mItemList.get(position);

        // 获取控件实例，并调用set...方法使其显示出来
        viewHolder.imageView.setImageResource(item.getImageResId());

        return convertView;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    static class ViewHolder {
        ImageView imageView;
    }
}

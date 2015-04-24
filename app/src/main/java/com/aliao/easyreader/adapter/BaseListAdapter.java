package com.aliao.easyreader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected Context mCtx;
    protected List<T> mDataList;

    public BaseListAdapter(Context context, List<T> list){
        mCtx = context;
        mDataList = list;
    }

    @Override
    public int getCount() {
        return  null!=mDataList?mDataList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null!=mDataList?mDataList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    abstract View getItemView(int position, View convertView, ViewGroup parent);
}

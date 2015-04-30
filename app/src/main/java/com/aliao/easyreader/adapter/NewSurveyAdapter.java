package com.aliao.easyreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.entity.Pager;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public class NewSurveyAdapter extends BaseListAdapter<Pager> {

    private List<Pager> mSurveyList;

    public NewSurveyAdapter(Context context, List<Pager> list) {
        super(context, list);
        mSurveyList = list;
    }

    @Override
    View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.listitem_new_surveys, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.tv_item_new_survey_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != mDataList) {
            holder.title.setText(mSurveyList.get(position).getsTitle());
        }

        return convertView;
    }

    class ViewHolder{
        private TextView title;
    }


}

package com.aliao.easyreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.entity.AnsweredPager;
import com.aliao.easyreader.entity.Pager;

import java.util.List;

/**
 * Created by liaolishuang on 15/5/3.
 */
public class UnfinishedSurveyAdapter extends BaseListAdapter<AnsweredPager> {

    private List<AnsweredPager> mSurveyList;

    public UnfinishedSurveyAdapter(Context context, List<AnsweredPager> list) {
        super(context, list);
        mSurveyList = list;
    }

    @Override
    View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.listitem_new_surveys, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.tv_item_new_survey_title);
            holder.time = (TextView) convertView.findViewById(R.id.tv_item_second_line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != mDataList) {
            holder.title.setText(mSurveyList.get(position).getPager().getsTitle());
            holder.time.setText(mSurveyList.get(position).getBeginTime());
        }

        return convertView;
    }

    class ViewHolder {
        private TextView title;
        private TextView time;
    }

}
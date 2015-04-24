package com.aliao.easyreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.entity.Answer;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public class AnswerOptionsAdapter extends BaseListAdapter<Answer> {

    private List<Answer> mAnswerList;

    public AnswerOptionsAdapter(Context context, List<Answer> list) {
        super(context, list);
        mAnswerList = list;
    }

    @Override
    View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.listitem_answer_option, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.tv_item_answer_option_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != mDataList) {
            holder.title.setText(mAnswerList.get(position).getOptionTitle());
        }

        return convertView;
    }

    class ViewHolder{
        private TextView title;
    }

}

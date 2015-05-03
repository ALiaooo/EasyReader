package com.aliao.easyreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aliao.easyreader.entity.AnsweredQuestion;
import com.aliao.easyreader.fragment.AnswerQuestionFragment;
import com.aliao.easyreader.fragment.UnfinishedQuestionFragment;

import java.util.List;

/**
 * Created by liaolishuang on 15/5/3.
 */
public class UnfinishedQuestionAdapter extends FragmentPagerAdapter {

    private List<AnsweredQuestion> mQuestionList;

    public UnfinishedQuestionAdapter(FragmentManager fm, List<AnsweredQuestion> list) {
        super(fm);
        mQuestionList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return UnfinishedQuestionFragment.newInstance(mQuestionList.get(position));
    }

    @Override
    public int getCount() {
        return mQuestionList.size();
    }
}

package com.aliao.easyreader.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.fragment.AnswerQuestionFragment;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public class QuestionPagerAdapter extends FragmentPagerAdapter {

    private List<Question> mQuestionList;

    public QuestionPagerAdapter(FragmentManager fm, List<Question> list) {
        super(fm);
        mQuestionList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return AnswerQuestionFragment.newInstance(mQuestionList.get(position));
    }

    @Override
    public int getCount() {
        return mQuestionList.size();
    }
}

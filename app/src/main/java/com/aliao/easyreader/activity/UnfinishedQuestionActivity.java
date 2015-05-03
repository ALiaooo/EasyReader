package com.aliao.easyreader.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.QuestionPagerAdapter;
import com.aliao.easyreader.adapter.UnfinishedQuestionAdapter;
import com.aliao.easyreader.entity.AnsweredPager;
import com.aliao.easyreader.entity.AnsweredQuestion;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaolishuang on 15/5/3.
 */
public class UnfinishedQuestionActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private UnfinishedQuestionAdapter mPagerAdapter;
    private List<AnsweredQuestion> mAnsweredQuestionList = new ArrayList<>();
    private AnsweredPager mAnsweredPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_question);

        initViews();
        initDatas();
    }


    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_unfinished_question_topbar);
        toolbar.setTitle("答题");
        setSupportActionBar(toolbar);
        mViewPager = (ViewPager) findViewById(R.id.vp_unfinished_question);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
        mAnsweredPager = (AnsweredPager) getIntent().getSerializableExtra(Contents.ANSWERED_PAGER_OBG);
        mAnsweredQuestionList = mAnsweredPager.getAnsweredQuestions();
        L.d("未完成问卷 mAnsweredQuestionList size = "+mAnsweredQuestionList.size());
        mPagerAdapter = new UnfinishedQuestionAdapter(getSupportFragmentManager(), mAnsweredQuestionList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mAnsweredQuestionList.size()-1);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

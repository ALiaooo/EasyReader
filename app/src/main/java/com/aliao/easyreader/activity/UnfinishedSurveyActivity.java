package com.aliao.easyreader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.NewSurveyAdapter;
import com.aliao.easyreader.adapter.UnfinishedSurveyAdapter;
import com.aliao.easyreader.entity.AnsweredPager;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.UserInfo;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.L;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/29.
 * 未完成问卷列表
 */
public class UnfinishedSurveyActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView mLvUnFiinshedPager;
    private UnfinishedSurveyAdapter mAdapter;
    private List<AnsweredPager> mUnfinishedPagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_unfinished_survey_topbar);
        toolbar.setTitle("未完成问卷");
        setSupportActionBar(toolbar);

        getUnfinishedSurveyDataFromDB();
        initViews();
    }

    private void getUnfinishedSurveyDataFromDB() {
        //先查出当前用户，再取出当前用户下的未完成问卷
        List<UserInfo> userInfos = DataSupport.where("iUserID = ?", "10138").find(UserInfo.class);
        UserInfo userInfo = userInfos.get(0);
        mUnfinishedPagerList = userInfo.getUnfinishedPager();
        L.d("未完成问卷数 = "+mUnfinishedPagerList.size());
//        AnsweredQuestionnaire answeredQuestionnaire = DataSupport.find(AnsweredQuestionnaire.class, 1);

    }

    private void initViews() {
        mLvUnFiinshedPager = (ListView) findViewById(R.id.lv_unfinished_surveys_list);
        mAdapter = new UnfinishedSurveyAdapter(this, mUnfinishedPagerList);
        mLvUnFiinshedPager.setAdapter(mAdapter);
        mLvUnFiinshedPager.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(UnfinishedSurveyActivity.this, UnfinishedQuestionActivity.class);
        intent.putExtra(Contents.ANSWERED_PAGER_OBG, mUnfinishedPagerList.get(position));
        startActivity(intent);

    }
}

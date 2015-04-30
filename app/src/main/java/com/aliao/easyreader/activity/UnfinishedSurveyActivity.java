package com.aliao.easyreader.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.aliao.easyreader.R;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.utils.L;

import org.litepal.crud.DataSupport;

/**
 * Created by 丽双 on 2015/4/29.
 *
 */
public class UnfinishedSurveyActivity extends ActionBarActivity {

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
        AnsweredQuestionnaire answeredQuestionnaire = DataSupport.find(AnsweredQuestionnaire.class, 1);
        L.d("title = "+answeredQuestionnaire.getQuestionTilte());
    }

    private void initViews() {

    }
}

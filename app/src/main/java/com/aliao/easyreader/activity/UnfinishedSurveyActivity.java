package com.aliao.easyreader.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.aliao.easyreader.R;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.utils.L;

import org.litepal.crud.DataSupport;

/**
 * Created by ��˫ on 2015/4/29.
 */
public class UnfinishedSurveyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_unfinished_survey_topbar);
        toolbar.setTitle("δ����ʾ�");
        setSupportActionBar(toolbar);

        getUnfinishedSurveyDataFromDB();
        initViews();
    }

    private void getUnfinishedSurveyDataFromDB() {
        //ĳ�û��µ�δ���ʾ�
        AnsweredQuestionnaire answeredQuestionnaire = DataSupport.find(AnsweredQuestionnaire.class, 1);
        L.d("title = "+answeredQuestionnaire.getQuestionTilte());
    }

    private void initViews() {

    }
}

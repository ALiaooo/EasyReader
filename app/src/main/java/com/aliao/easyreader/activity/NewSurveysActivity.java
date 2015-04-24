package com.aliao.easyreader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.NewSurveyAdapter;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.PagerResult;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public class NewSurveysActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private ListView mLvNewSurvey;
    private NewSurveyAdapter mAdapter;
    private List<Pager> mSurveyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surveys);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_new_survey_topbar);
        toolbar.setTitle("最新问卷列表");
        setSupportActionBar(toolbar);

        initViews();
        requestDatas();
    }

    private void initViews() {
        mSurveyList = new ArrayList<>();
        mLvNewSurvey = (ListView) findViewById(R.id.lv_new_surveys_list);
        mAdapter = new NewSurveyAdapter(this, mSurveyList);
        mLvNewSurvey.setAdapter(mAdapter);
        mLvNewSurvey.setOnItemClickListener(this);
    }

    private void requestDatas() {

        GsonRequest<PagerResult> request = new GsonRequest<PagerResult>(Contents.NEW_SURVEY_LIST_REQUEST_URL, PagerResult.class, new Response.Listener<PagerResult>() {
            @Override
            public void onResponse(PagerResult response) {
                mSurveyList.addAll(response.getSurveys());
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(NewSurveysActivity.this, SurveyQuestionActivity.class);
        startActivity(intent);
        /*Button beginAnswer = (Button) view.findViewById(R.id.btn_item_new_survey_begin);
        beginAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewSurveysActivity.this, SurveyQuestionActivity.class);
                startActivity(intent);
            }
        });*/
    }
}

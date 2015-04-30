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
import com.aliao.easyreader.encrypt.DES;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.PagerResult;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.DBUtility;
import com.aliao.easyreader.utils.DateUtil;
import com.aliao.easyreader.utils.L;
import com.aliao.easyreader.utils.StringUtils;
import com.aliao.easyreader.utils.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 * 先请求网络，如果没有网络，则从数据库中获取数据
 */
public class NewSurveysActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    private ListView mLvNewSurvey;
    private NewSurveyAdapter mAdapter;
    private List<Pager> mSurveyList;

    public static final String ENCODING = "UTF-8";

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
//http://csis.fdc.test.fang.com/SurveyInterface/GetSurveys.ashx?iUserID=10138

       /* iUserID:用户ID(需加密)
        encrypt:加密串(MD5)  iUserID+加密私钥(csis_app)*/

        String userId = "10138";
        //MD5加密
        String encrypt = StringUtils.getMD5Str(userId+"csis_app");
        try {
            //参数加密
            userId = URLEncoder.encode(DES.encryptDES(userId, "csis_app"), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "http://csis.fdc.test.fang.com/SurveyInterface/App_GetSurveys.ashx?iUserID="+userId+"&encrypt="+encrypt;
        L.d("url = "+url);

        GsonRequest<PagerResult> request = new GsonRequest<PagerResult>(url, PagerResult.class, new Response.Listener<PagerResult>() {
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

        Pager pager = mSurveyList.get(position);
        pager.setBeginTime(DateUtil.getCurrentDate());

        Intent intent = new Intent(NewSurveysActivity.this, SurveyQuestionActivity.class);
//        intent.putExtra("time", DateUtil.getCurrentDate());
        intent.putExtra(Contents.SURVEY_OBG, pager);
        startActivity(intent);
        /**
         * 点击开始答题的时候，将起始时间保存到已答问卷表里
         */
    }
}

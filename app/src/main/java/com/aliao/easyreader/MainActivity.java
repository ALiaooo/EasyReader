package com.aliao.easyreader;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aliao.easyreader.activity.NewSurveysActivity;
import com.aliao.easyreader.activity.UnfinishedSurveyActivity;
import com.aliao.easyreader.encrypt.DES;
import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.SurveyResult;
import com.aliao.easyreader.entity.Survey;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.DBUtility;
import com.aliao.easyreader.utils.DateUtil;
import com.aliao.easyreader.utils.L;
import com.aliao.easyreader.utils.StringUtils;
import com.aliao.easyreader.utils.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * List<Answer> answerList;
 * record answerId
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private List<Survey> mSurveyList;
    private List<Question> mQuestionList;
    private List<Answer> mAnswerOptionList;
    private List<Logic> mLogicList;
    private List<Pager> mPagerList;

    public static final String ENCODING = "UTF-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main_topbar);
        toolbar.setTitle("ALiaooo");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);

        SQLiteDatabase db = Connector.getDatabase();

        L.d("date = "+ DateUtil.getCurrentDate());
        initViews();
        requestDatas();

    }

    private void requestDatas() {

        mSurveyList = new ArrayList<>();
        mQuestionList = new ArrayList<>();
        mAnswerOptionList = new ArrayList<>();
        mLogicList = new ArrayList<>();
        mPagerList = new ArrayList<>();


//        public static final String SURVEY_DETAIL_LIST_REQUEST_URL = "http://csis.fdc.test.fang.com/SurveyInterface/GetSurveysDetail.ashx?iUserID=10138&sSurveyIDs=700";

        String userId = "10138";
        String encrypt;
        try {
            //参数加密
            userId = URLEncoder.encode(DES.encryptDES(userId, "csis_app"), ENCODING);
            L.d("userId = "+userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MD5加密
        encrypt = StringUtils.getMD5Str(userId + "csis_app");
        L.d("encrypt = "+encrypt);
        String url = "http://csis.fdc.test.fang.com/SurveyInterface/GetSurveys.ashx?iUserID="+userId+"&encrypt"+encrypt;
        L.d("url = "+url);

        GsonRequest<SurveyResult> request = new GsonRequest<SurveyResult>(Contents.SURVEY_DETAIL_LIST_REQUEST_URL, SurveyResult.class, new Response.Listener<SurveyResult>() {
            @Override
            public void onResponse(SurveyResult response) {
                mSurveyList.addAll(response.getSurveys());
                for (int i = 0; i<mSurveyList.size(); i++){
                    mQuestionList.addAll(mSurveyList.get(i).getQuestions());
                    mPagerList.add(mSurveyList.get(i).getInfo());
                }
                L.d("mQuestionList = " + mQuestionList.size());
                for (int i = 0; i<mQuestionList.size(); i++){
                    mAnswerOptionList.addAll(mQuestionList.get(i).getOptions());
                    mLogicList.addAll(mQuestionList.get(i).getLogics());
                }
                /**
                 * 缓存到数据库中
                 */
                DBUtility.saveTemplateSurvey(mSurveyList);
//                saveDatasToDB();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);

        DateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strBeginDate = dateTimeformat.format(new Date());
        L.d("date = " + strBeginDate);

    }

    /**
     * 缓存到数据库中
     */
    private void saveDatasToDB() {
        DataSupport.saveAll(mAnswerOptionList);//��
        DataSupport.saveAll(mLogicList);//����
        DataSupport.saveAll(mQuestionList);//����
        DataSupport.saveAll(mPagerList);
//        DataSupport.saveAll(mSurveyList);
    }


    private void initViews() {
        findViewById(R.id.btn_get_new_surveys).setOnClickListener(this);
        findViewById(R.id.btn_get_unfinished_surveys).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_new_surveys:
                startActivity(new Intent(MainActivity.this, NewSurveysActivity.class));
                break;
            case R.id.btn_get_unfinished_surveys:
                startActivity(new Intent(MainActivity.this, UnfinishedSurveyActivity.class));
                break;
        }
    }
}

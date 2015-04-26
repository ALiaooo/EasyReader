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
import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.SurveyResult;
import com.aliao.easyreader.entity.Surveys;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * List<Answer> answerList;
 * record answerId
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private List<Surveys> mSurveyList;
    private List<Question> mQuestionList;
    private List<Answer> mAnswerOptionList;

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

        initViews();
        requestDatas();

    }

    private void requestDatas() {

        mSurveyList = new ArrayList<>();
        mQuestionList = new ArrayList<>();

        GsonRequest<SurveyResult> request = new GsonRequest<SurveyResult>(Contents.SURVEY_DETAIL_LIST_REQUEST_URL, SurveyResult.class, new Response.Listener<SurveyResult>() {
            @Override
            public void onResponse(SurveyResult response) {
                mSurveyList.addAll(response.getSurveys());
                mQuestionList.addAll(mSurveyList.get(0).getQuestions());

                for (int i = 0; i<mQuestionList.size(); i++){
                    mAnswerOptionList.addAll(mQuestionList.get(i).getOptions());
                }
                saveDatasToDB();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);

    }

    private void saveDatasToDB() {
//        DataSupport.saveAll(mQuestionList);
//        DataSupport.saveAll(mAnswerOptionList);

        Question question = new Question();
        question.setQuestionTilte("question title 1");
        question.saveThrows();

    }


    private void initViews() {
        findViewById(R.id.btn_get_new_surveys).setOnClickListener(this);
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
        }
    }
}

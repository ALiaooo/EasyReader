package com.aliao.easyreader.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.QuestionPagerAdapter;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.SurveyResult;
import com.aliao.easyreader.entity.Survey;
import com.aliao.easyreader.fragment.AnswerQuestionFragment;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.L;
import com.aliao.easyreader.utils.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 */
public class SurveyQuestionActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener, AnswerQuestionFragment.OnAnswerQuestionFragmentInteractionListener {

    private ViewPager mViewPager;
    private QuestionPagerAdapter mPagerAdapter;
    private List<Survey> mSurveyList;
    private List<Question> mQuestionList;
    private List<Logic> mJumpLogicList;
    private Logic mJumpLogic;
    private List<Question> mRealTimeQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answer_survey);

        initViews();
        initDatas();
//        requestDatas();
    }


    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_question_topbar);
        toolbar.setTitle("答题");
        setSupportActionBar(toolbar);
        mSurveyList = new ArrayList<>();
        mQuestionList = new ArrayList<>();
        mJumpLogicList = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.vp_question);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
        Question question = DataSupport.findFirst(Question.class);
        mQuestionList.add(question);
        setDatas();
    }

    private void setDatas() {
        mPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), mQuestionList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void requestDatas() {

        GsonRequest<SurveyResult> request = new GsonRequest<SurveyResult>(Contents.SURVEY_DETAIL_LIST_REQUEST_URL, SurveyResult.class, new Response.Listener<SurveyResult>() {
            @Override
            public void onResponse(SurveyResult response) {
                mSurveyList.addAll(response.getSurveys());
                mQuestionList.addAll(mSurveyList.get(0).getQuestions());
                for (int i = 0; i<mQuestionList.size(); i++){
                    mJumpLogicList.addAll(mQuestionList.get(i).getLogics());
                }
                setDatas();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        /**滑动的时候来决定下一页要显示的是哪道题目
         * 判断逻辑要跳转到哪一道题
         * 返回回来logicList和选项，然后再遍历Logic，查找与选项对应的一项？？？这个应该是一个answer的一个属性才是啊！！！
         * 只有单选才有跳转？！？
         *
         * 实时构建答题列表
         */

        if (null != mJumpLogic){//没有逻辑跳转，继续下一题
            L.d("滑动 跳转到下一题:"+mJumpLogic.getSkipTo());
        }else {//有逻辑跳转
//            mRealTimeQuestionList.add()
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAnswerQuestionFragmentInteraction(Bundle bundle) {
        //传回来选择的id
        mJumpLogic = (Logic) bundle.getSerializable(Contents.LOGIC_OBG);
    }
}

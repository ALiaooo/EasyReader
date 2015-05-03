package com.aliao.easyreader.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.QuestionPagerAdapter;
import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.AnsweredPager;
import com.aliao.easyreader.entity.AnsweredQuestion;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.SurveyResult;
import com.aliao.easyreader.entity.Survey;
import com.aliao.easyreader.entity.UserInfo;
import com.aliao.easyreader.fragment.AnswerQuestionFragment;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.DBUtility;
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
    private Question mCurrentQuestion;
    private int mCurrentIndex = 0;//当前页卡编号,滑动到哪页，该页就是当前页
    private int mLastIndex;//做到的最后一道题的页数
    private String mTimeStamp;
    private Pager pager;
    private List<AnsweredQuestion> mAnsweredQuestionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answer_survey);
        pager = (Pager) getIntent().getSerializableExtra(Contents.SURVEY_OBG);

        mTimeStamp = getIntent().getExtras().getString("time");

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
        /**
         * 1.从数据库中取出第一道题
         */
//        mCurrentQuestion = DataSupport.findFirst(Question.class);
                /**
                 * 从数据库中取出第一道题的答案选项
                 */
        //        List<Answer> options = question.getAnswerOptions();
                /**
                 * 从数据库中取出第一道题的逻辑选项
                 */
        //        List<Logic> logics = question.getLogicList();
        /**
         * 2.添加到数据源中
         */
//        mQuestionList.add(mCurrentQuestion);

        /**
         * 3.setAdapter
         */
//        setDatas();

        /**
         * 1.从数据库中取出第一道题
         */
        mCurrentQuestion = DataSupport.findFirst(Question.class);
        AnsweredQuestion answeredQuestion = new AnsweredQuestion();
        answeredQuestion.setIQuestionID(mCurrentQuestion.getiID());
        answeredQuestion.setQuestion(mCurrentQuestion);
        mAnsweredQuestionList.add(answeredQuestion);

        setDatas();

    }

    private void setDatas() {
//        mPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), mQuestionList);
        mPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), mAnsweredQuestionList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void requestDatas() {

        GsonRequest<SurveyResult> request = new GsonRequest<SurveyResult>(Contents.SURVEY_DETAIL_LIST_REQUEST_URL, SurveyResult.class, new Response.Listener<SurveyResult>() {
            @Override
            public void onResponse(SurveyResult response) {
//                mSurveyList.addAll(response.getSurvey());
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

//        if (null != mJumpLogic){//没有逻辑跳转，继续下一题
//            L.d("滑动 跳转到下一题:"+mJumpLogic.getSkipTo());
//        }else {//有逻辑跳转
////            mRealTimeQuestionList.add()
//        }
        /**
         * 其实不用判断选择了哪个选项，直接遍历逻辑列表查找
         */
        for (int i = 0; i<mCurrentQuestion.getLogicList().size(); i++){
            //获取
            mCurrentQuestion.getLogicList().get(i);
        }

        String questionId;
        String isSelectedAnswerId;
        String skipTo;
        for (Logic logic:mCurrentQuestion.getLogicList()){
            questionId = logic.getiQuestionID();
            isSelectedAnswerId = logic.getiSelectAnswer();
            //从已答问卷表里判断做过的题目里是否有questionId且答案选项为isSelectedAnserId,如果有break
//            if ()
        }

        L.d("postion = "+position);//代表哪个页面被选中，即当前页面同mCurrentIndex

        //如果没有任何逻辑跳转，取下一题
        mCurrentIndex = position;
        L.d("mCurrentIndex = "+mCurrentIndex);
        L.d("pager mLastIndex = "+mLastIndex);

        //如果往回滑，可能是去改题，如果发现修改了答案，那么更新数据源
        //往回滑，当前的对象要变成当前页的
//        mCurrentQuestion = mQuestionList.get(position);
        L.d("往回滑到当前页，之前选的答案选项getAnswerOptionId = "+mCurrentQuestion.getAnswerOptionId());
     /*   if (){

        }*/

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAnswerQuestionFragmentInteraction(Bundle bundle) {

        //滑到前面去修改了答案，那么数据源list重新从该题开始.如果没有修改答案那么不改动list，如果有修改，则清空之后的数据源
        if (mLastIndex > mCurrentIndex){//简易版-滑到前面的题去，只要重新点击了某个选项就调整已答问卷表

            int i = mQuestionList.size()-1;
            while ( i != mCurrentIndex){
                mQuestionList.remove(i);
                i--;
            }

            L.d("修改题目后当前题目数 size = " + mQuestionList.size());
        }

        //打印每一题的答案选项位置
//        for (int i =0; i<mQuestionList.size(); i++){
//            L.d(i+"-getOptionOrder = " + mQuestionList.get(i).getOptionOrder());
//        }

        //取题-判断是否有逻辑跳转
        mJumpLogicList.addAll(mCurrentQuestion.getLogicList());
        String skipToQuestionId = null;
        for (AnsweredQuestion answeredQuestion:mAnsweredQuestionList){
            for (Logic logic:mJumpLogicList){

                L.d("逻辑循环aqId = "+answeredQuestion.getIQuestionID()+":logicId = "+logic.getiQuestionID()+"|a="+answeredQuestion.getSAnswers()+":s="+logic.getiSelectAnswer());
                if (answeredQuestion.getIQuestionID().equals(logic.getiQuestionID())
                        && answeredQuestion.getSAnswers().equals(logic.getiSelectAnswer())){
                    skipToQuestionId = logic.getiSkipTo();
                    break;
                }
            }
        }
        Question nextQuestion;
        if (TextUtils.isEmpty(skipToQuestionId)){
            //直接取当前题目的下一题
            nextQuestion = DataSupport.find(Question.class, Integer.parseInt(mCurrentQuestion.getQNum())+1);
            L.d("nextQuestion title = "+nextQuestion.getQuestionTilte());
        }else {
            //取逻辑跳转中指定的题目
            nextQuestion = DataSupport.where("iID = ?", skipToQuestionId).find(Question.class).get(0);
            L.d("jumpQuestion title = "+nextQuestion.getQuestionTilte());
        }

        //数据源添加下一题，只添加一次(直接用currentQuestion接收数据就行不用再new 一个next了)
//        Question nextQuestion = DataSupport.find(Question.class, Integer.parseInt(mCurrentQuestion.getQNum())+1);
        AnsweredQuestion answeredQuestion = new AnsweredQuestion();
        answeredQuestion.setIQuestionID(nextQuestion.getiID());
        answeredQuestion.setQuestion(nextQuestion);
        mAnsweredQuestionList.add(answeredQuestion);

        if (!mQuestionList.contains(nextQuestion))
            mQuestionList.add(nextQuestion);

        //mQuestion也换成当前
        mCurrentQuestion = nextQuestion;


//        L.d("当前的题号 = " + mCurrentQuestion.getQNum());
//        L.d("当前题目数 size = " + mQuestionList.size());


        //数据源变化，更新
        mPagerAdapter.notifyDataSetChanged();

        //自动跳到下一题
        mLastIndex = mCurrentIndex+1;
        mViewPager.setCurrentItem(mLastIndex, true);
//        L.d("click mLastIndex = " + mLastIndex);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK ){

            // 创建退出对话框

            AlertDialog isExit = new AlertDialog.Builder(this).create();

            // 设置对话框标题

            isExit.setTitle("提示");

            // 设置对话框消息

            isExit.setMessage("确定要暂停作答吗");

            // 添加选择按钮并注册监听

            isExit.setButton("确定", listener);

            isExit.setButton2("取消", listener);

            // 显示对话框

            isExit.show();

        }
        return false;

    }

    /**监听对话框里面的button点击事件*/

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){

        public void onClick(DialogInterface dialog, int which){

            switch (which){

                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序

                    //将未完成的问卷保存到数据库答卷表里
                    AnsweredPager answeredPager = new AnsweredPager();
                    answeredPager.setBeginTime(mTimeStamp);
                    answeredPager.setStatus(Contents.UNFINISHED);
                    answeredPager.setPagerId(pager.getiID());
                    answeredPager.setAnsweredQuestions(mAnsweredQuestionList);//答卷表关联答题表
                    answeredPager.save();

                    //关联用户表,添加关联以后一定要保存！！
                    List<UserInfo> userInfos = DataSupport.where("iUserID = ?", "10138").find(UserInfo.class);
                    UserInfo userInfo = userInfos.get(0);
                    userInfo.getAnsweredPagers().add(answeredPager);
                    userInfo.save();

                    //关联问卷表
                    List<Pager> pagers = DataSupport.where("iID = ?", pager.getiID()).find(Pager.class);
                    Pager pager = pagers.get(0);
                    pager.getAnsweredPagers().add(answeredPager);
                    pager.save();

                    //保存已答题目到答题表
                    DataSupport.saveAll(mAnsweredQuestionList);



                    //------------------------------------------------------------------------------
                    //将已答的题目保存到答卷表里
//                    List<AnsweredQuestionnaire> answeredQuestionnaireList = new ArrayList<>();
//                    for (Question question:mQuestionList) {
//                        AnsweredQuestionnaire aQusetion = new AnsweredQuestionnaire();
//                        aQusetion.setiID(question.getiID());
//                        aQusetion.setQNum(question.getQNum());
//                    }

                    //关联该未完成问卷
//                    for (AnsweredQuestionnaire answeredQuestionnaire:answeredQuestionnaireList){
//                        pager.getAnsweredQuestionnaires().add(answeredQuestionnaire);
//                    }

                    //将未完成的问卷保存到数据库中
//                    pager.setStatus(Contents.UNFINISHED);
//                    pager.save();

                    //先把user查出来，设置关联关系，最后再save
//                    long id = 0;
//                    Cursor cursor = DataSupport.findBySQL("select * from userinfo where iUserID=?","10138");
//                    if (cursor.moveToFirst()) {
//                        do {
//                            id = cursor.getLong(cursor.getColumnIndex("id"));
//                        } while (cursor.moveToNext());
//                    }
//
//                    UserInfo userInfo = DataSupport.find(UserInfo.class,id);
//                    userInfo.getPagers().add(pager);
//                    userInfo.save();

                    //------------------------------------------------------------------------------


                    /*for (Question question:mQuestionList) {
                        AnsweredPager answeredPager = new AnsweredPager();
                        answeredPager.setIQuestionID(question.getiID());
                        answeredPager.setSAnswers(question.getAnswerOptionId());
                    }*/
                    // select * from question where
//                    DBUtility.saveUnfinishedSurveyToDB(pager);
                  /*  UserInfo userInfo = new UserInfo();
                    userInfo.getPagers().add();*/

                    //先保存到数据库中
               /*     List<AnsweredQuestionnaire> answeredQuestionnaireList = new ArrayList<>();
                    for (Question question:mQuestionList){
                        AnsweredQuestionnaire aQusetion = new AnsweredQuestionnaire();
                        aQusetion.setiID(question.getiID());
                        aQusetion.setQNum(question.getQNum());
                        aQusetion.setQuestionType(question.getQuestionType());
                        aQusetion.setiQuestionID(question.getiQuestionID());
                        aQusetion.setsShowID(question.getsShowID());
                        aQusetion.setsQuestionTilte(question.getQuestionTilte());
                        aQusetion.setbISMust(question.getbISMust());
                        aQusetion.setiCatalogID(question.getiCatalogID());
                        aQusetion.setiScore(question.getiScore());
                        aQusetion.setiCategory(question.getiCategory());
                        aQusetion.setiTemplateID(question.getiTemplateID());
                        aQusetion.setiAnswerNumber(question.getiAnswerNumber());
                        aQusetion.setsDescription(question.getsDescription());
                        aQusetion.setOptions(question.getOptions());
                        aQusetion.setRecommendAnswers(question.getRecommendAnswers());
                        aQusetion.setLogics(question.getLogics());
                        aQusetion.setTimeStamp(mTimeStamp);
                        aQusetion.setSurvey(question.getSurvey());
                        aQusetion.setPager(question.getPager());
                        aQusetion.setAnswerOptionId(question.getAnswerOptionId());
                        aQusetion.setOptionOrder(question.getOptionOrder());
                        answeredQuestionnaireList.add(aQusetion);
                    }

                    DBUtility.saveMultyToAnsweredQeustionnaire(answeredQuestionnaireList);*/

                    finish();

                    break;

                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框

                    break;

                default:

                    break;

            }

        }

    };

}

package com.aliao.easyreader.utils;

import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.Survey;

import org.litepal.crud.DataSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ��˫ on 2015/4/28.
 */
public class DBUtility {

    /**
     * 缓存模板问卷
     * @param surveyList
     */
    public static void saveTemplateSurvey(List<Survey> surveyList){

        List<Question> questionList = new ArrayList<>();
        List<Answer> optionList  = new ArrayList<>();
        List<Pager> pagerList  = new ArrayList<>();
        List<Logic> logicList  = new ArrayList<>();
        for (int i = 0; i<surveyList.size(); i++){
            questionList.addAll(surveyList.get(i).getQuestions());
            pagerList.add(surveyList.get(i).getInfo());
        }
        for (int i = 0; i<questionList.size(); i++){
            optionList.addAll(questionList.get(i).getOptions());
            logicList.addAll(questionList.get(i).getLogics());
        }
        DataSupport.saveAll(optionList);
        DataSupport.saveAll(logicList);
        DataSupport.saveAll(questionList);
        DataSupport.saveAll(pagerList);
    }


    /**
     * 将开始答题时间保存到已答问卷里
     */
    public static void saveTimeStampToAnsweredQuestionaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
        answeredQuestionnaire.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        answeredQuestionnaire.save();
    }

    /**
     * 单道题存储到已答问卷中
     */
    public static void saveSimpleToAnsweredQeustionnaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
//        answeredQuestionnaire.;

        answeredQuestionnaire.save();
    }

    /**
     * 批量存储到已答问卷中
     */
    public static void saveMultyToAnsweredQeustionnaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
//        answeredQuestionnaire.;

        answeredQuestionnaire.save();
    }
}

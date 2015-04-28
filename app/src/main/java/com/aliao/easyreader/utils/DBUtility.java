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
 * Created by 丽双 on 2015/4/28.
 */
public class DBUtility {

    /**
     * 将获取的数据保存到数据库
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
     * 将开始答题的时间保存到已答问卷表里
     */
    public static void saveTimeStampToAnsweredQuestionaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
        answeredQuestionnaire.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//保存到开始答题时间
        answeredQuestionnaire.save();
    }

    /**
     * 保存该题到临时表里
     */
    public static void saveToAnsweredQeustionnaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
//        answeredQuestionnaire.;
        answeredQuestionnaire.save();
        answeredQuestionnaire.save();
    }
}

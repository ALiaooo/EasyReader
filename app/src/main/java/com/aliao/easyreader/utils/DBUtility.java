package com.aliao.easyreader.utils;

import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Pager;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.entity.Survey;
import com.aliao.easyreader.entity.UserInfo;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ��˫ on 2015/4/28.
 */
public class DBUtility {

    /**
     * ����ģ���ʾ�
     * @param surveyList
     */
    public static void saveTemplateSurvey(Survey survey){

        List<Question> questionList = new ArrayList<>();
        List<Answer> optionList  = new ArrayList<>();
        List<Pager> pagerList  = new ArrayList<>();
        List<Logic> logicList  = new ArrayList<>();
     /*   for (int i = 0; i<surveyList.size(); i++){
            questionList.addAll(surveyList.get(i).getQuestions());
            pagerList.add(surveyList.get(i).getInfo());
        }*/
        questionList.addAll(survey.getQuestions());
        pagerList.add(survey.getInfo());
        for (int i = 0; i<questionList.size(); i++){
            optionList.addAll(questionList.get(i).getOptions());
            logicList.addAll(questionList.get(i).getLogics());
        }
        //建立pager与question的一对多关联
        for (Question question: questionList){
            survey.getInfo().getQuestions().add(question);
        }

        //添加用户信息
        DataSupport.saveAll(optionList);
        DataSupport.saveAll(logicList);
        DataSupport.saveAll(questionList);
        DataSupport.saveAll(pagerList);
        UserInfo userInfo = new UserInfo();
        userInfo.setiUserID("10138");
        userInfo.setsUserName("adminF1");
        userInfo.setsRealName("admin访问员1(勿删)");
        userInfo.setdPermissionEndTime("2015/9/2 0:00:00");
        //建立user与pager的一对多关联
        userInfo.getPagers().add(survey.getInfo());
      /*  for (Pager pager:pagerList){
            userInfo.getPagers().add(pager);
        }*/
        userInfo.save();
    }


    /**
     * ����ʼ����ʱ�䱣�浽�Ѵ��ʾ���
     */
    public static void saveTimeStampToAnsweredQuestionaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
        answeredQuestionnaire.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        answeredQuestionnaire.save();
    }

    /**
     * ������洢���Ѵ��ʾ���
     */
    public static void saveSimpleToAnsweredQeustionnaire(){
        AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();
//        answeredQuestionnaire.;

        answeredQuestionnaire.save();
    }

    /**
     * �����洢���Ѵ��ʾ���
     */
    public static void saveMultyToAnsweredQeustionnaire(List<AnsweredQuestionnaire> answeredQuestionnaireList){
        DataSupport.saveAll(answeredQuestionnaireList);
    }

    /**
     * 将未完成的问卷保存到db中
     */
    public static void saveUnfinishedSurveyToDB(Pager pager){
        pager.save();
    }
}

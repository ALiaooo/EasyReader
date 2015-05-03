package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by liaolishuang on 15/4/23.
 */
public class Logic extends DataSupport implements Serializable {

    private long id;
    private String iID;//逻辑主键id
    private String iQuestionID;//问卷问题id------->?
    private String iSelectAnswer;//回答的选项iSelectAnswers
    private String iSkipFrom;//从哪题开始跳转
    private String iSkipTo;//跳转至哪题
    private String iLogicType;//跳转类型：1.单个跳转 3.直接结束 4.结束问卷并提交-------？
    private Question question;
    private AnsweredQuestionnaire answeredQuestionnaire;

    public AnsweredQuestionnaire getAnsweredQuestionnaire() {
        return answeredQuestionnaire;
    }

    public void setAnsweredQuestionnaire(AnsweredQuestionnaire answeredQuestionnaire) {
        this.answeredQuestionnaire = answeredQuestionnaire;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getSelectAnswer() {
        return iSelectAnswer;
    }

    public String getSkipTo() {
        return iSkipTo;
    }

    public String getiID() {
        return iID;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public String getiQuestionID() {
        return iQuestionID;
    }

    public void setiQuestionID(String iQuestionID) {
        this.iQuestionID = iQuestionID;
    }

    public String getiSelectAnswer() {
        return iSelectAnswer;
    }

    public void setiSelectAnswer(String iSelectAnswer) {
        this.iSelectAnswer = iSelectAnswer;
    }

    public String getiSkipFrom() {
        return iSkipFrom;
    }

    public void setiSkipFrom(String iSkipFrom) {
        this.iSkipFrom = iSkipFrom;
    }

    public String getiSkipTo() {
        return iSkipTo;
    }

    public void setiSkipTo(String iSkipTo) {
        this.iSkipTo = iSkipTo;
    }

    public String getiLogicType() {
        return iLogicType;
    }

    public void setiLogicType(String iLogicType) {
        this.iLogicType = iLogicType;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

package com.aliao.easyreader.entity;

import com.aliao.easyreader.utils.L;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by liaolishuang on 15/5/2.
 * 已作答的题目（答题表）
 */
public class AnsweredQuestion extends DataSupport implements Serializable {
    private long id;
    private String IQuestionID;//问题ID
    private String SAnswers;//回答选项ID
    private String SAnswersNote;//开放题或开放选项
    private String DAddTime;//问题答题时间2014-08-18 15:02:38
    private AnsweredPager answeredPager;//答卷与答题是一对多关系
    private Question question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIQuestionID() {
        return IQuestionID;
    }

    public void setIQuestionID(String IQuestionID) {
        this.IQuestionID = IQuestionID;
    }

    public String getSAnswers() {
        return SAnswers;
    }

    public void setSAnswers(String SAnswers) {
        this.SAnswers = SAnswers;
    }

    public String getSAnswersNote() {
        return SAnswersNote;
    }

    public void setSAnswersNote(String SAnswersNote) {
        this.SAnswersNote = SAnswersNote;
    }

    public String getDAddTime() {
        return DAddTime;
    }

    public void setDAddTime(String DAddTime) {
        this.DAddTime = DAddTime;
    }

    public AnsweredPager getAnsweredPager() {
        return answeredPager;
    }

    public void setAnsweredPager(AnsweredPager answeredPager) {
        this.answeredPager = answeredPager;
    }

    public Question getQuestion() {
        L.d("getQuestion - IQuestionID = "+IQuestionID);
        return DataSupport.where("iID = ?",IQuestionID).find(Question.class).get(0);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

package com.aliao.easyreader.entity;

/**
 * Created by 丽双 on 2015/4/30.
 * 答卷
 */
public class AnsweredPager {
    private long id;
    private String IQuestionID;//问题ID
    private String SAnswers;//回答选项ID
    private String SAnswersNote;//开放题或开放选项
    private String DAddTime;//问题答题时间2014-08-18 15:02:38”

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
}

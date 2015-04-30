package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/23.
 * 问卷基本信息
 */
public class Pager extends DataSupport implements Serializable {

    private long id;
    private String iID;//问卷id
    private String sTitle;//问卷标题
    private int iTypeID;//问卷类别id
    private String sTypeName;//问卷类别名称
    private String sCompanyName;//服务企业
    private String dUpdateTime;
    private List<Question> questions = new ArrayList<>();
    private List<AnsweredQuestionnaire> answeredQuestionnaires;
    private UserInfo userInfo;//与用户是多对一的关系
    private String status;//0模板问卷 1未完成 2已完成待提交
    private String beginTime;//开始答题时间

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getiID() {
        return iID;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public int getiTypeID() {
        return iTypeID;
    }

    public void setiTypeID(int iTypeID) {
        this.iTypeID = iTypeID;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsTypeName() {
        return sTypeName;
    }

    public void setsTypeName(String sTypeName) {
        this.sTypeName = sTypeName;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public void setsCompanyName(String sCompanyName) {
        this.sCompanyName = sCompanyName;
    }

    public String getdUpdateTime() {
        return dUpdateTime;
    }

    public void setdUpdateTime(String dUpdateTime) {
        this.dUpdateTime = dUpdateTime;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<AnsweredQuestionnaire> getAnsweredQuestionnaires() {
        return answeredQuestionnaires;
    }

    public void setAnsweredQuestionnaires(List<AnsweredQuestionnaire> answeredQuestionnaires) {
        this.answeredQuestionnaires = answeredQuestionnaires;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

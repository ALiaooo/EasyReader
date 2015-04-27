package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by liaolishuang on 15/4/27.
 * 用户信息
 */
public class User extends DataSupport {
    private String userId;
    private String userName;
    private List<Survey> surveys;//用户与调查问卷是一对多关系

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }
}

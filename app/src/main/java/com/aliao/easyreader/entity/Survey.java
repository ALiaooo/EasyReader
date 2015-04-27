package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/23.
 */
public class Survey extends DataSupport {
    private Pager info;//调查问卷基本信息
    private List<Question> questions;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Pager getInfo() {
        return info;
    }

    public void setInfo(Pager info) {
        this.info = info;
    }
}

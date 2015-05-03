package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/30.
 * 答卷（表/已问卷，包括未完成和待提交两种状态）
 */
public class AnsweredPager extends DataSupport implements Serializable {
    private long id;
    private String userId;//用户id
    private String pagerId;//问卷id
    private String status;//都是未提交问卷，0未完成 1完成
    private String beginTime;//开始答题时间
    private UserInfo userInfo;//用户与答卷是一对多关系
    private Pager pager;//模板问卷与答卷是一对多关系


    public List<AnsweredQuestion> getAnsweredQuestions() {
        return DataSupport.where("answeredpager_id = ?", String.valueOf(id)).find(AnsweredQuestion.class);
    }

    public void setAnsweredQuestions(List<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    private List<AnsweredQuestion> answeredQuestions = new ArrayList<>();//与答题表是一对多关系

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPagerId() {
        return pagerId;
    }

    public void setPagerId(String pagerId) {
        this.pagerId = pagerId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Pager getPager() {
        return DataSupport.where("iID = ?", pagerId).find(Pager.class).get(0);
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}

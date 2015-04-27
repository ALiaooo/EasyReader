package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/23.
 * 问卷基本信息
 */
public class Pager extends DataSupport {
//    private String pagerId;
//    private String pagerTitle;//问卷名称
//    private String pagerIntro;//问卷描述
//    private List<Question> questionList;//问题列表

    private String iID;//问卷id
    private String sTitle;//问卷标题
    private int iTypeID;//问卷类别id
    private String sTypeName;//问卷类别名称
    private String sCompanyName;//服务企业
    private String dUpdateTime;

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsTitle() {
        return sTitle;
    }

    public String getTitle() {
        return sTitle;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public String getiID() {
        return iID;
    }

    public void setiTypeID(int iTypeID) {
        this.iTypeID = iTypeID;
    }

    public int getiTypeID() {
        return iTypeID;
    }

    public void setsTypeName(String sTypeName) {
        this.sTypeName = sTypeName;
    }

    public String getsTypeName() {
        return sTypeName;
    }

    public void setsCompanyName(String sCompanyName) {
        this.sCompanyName = sCompanyName;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public void setdUpdateTime(String dUpdateTime) {
        this.dUpdateTime = dUpdateTime;
    }

    public String getdUpdateTime() {
        return dUpdateTime;
    }
}

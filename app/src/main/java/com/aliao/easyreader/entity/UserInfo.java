package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaolishuang on 15/4/27.
 * 用户信息
 */
public class UserInfo extends DataSupport {

    private long id;
    private String iUserID;
    private String sUserName;
    private String dPermissionEndTime;
    private String sRealName;
    private List<Pager> pagers = new ArrayList<>();//问卷(模板问卷，未完成，待提交)
    private List<AnsweredPager> answeredPagers = new ArrayList<>();//与答卷是一对多关系

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getiUserID() {
        return iUserID;
    }

    public void setiUserID(String iUserID) {
        this.iUserID = iUserID;
    }

    public String getdPermissionEndTime() {
        return dPermissionEndTime;
    }

    public void setdPermissionEndTime(String dPermissionEndTime) {
        this.dPermissionEndTime = dPermissionEndTime;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsRealName() {
        return sRealName;
    }

    public void setsRealName(String sRealName) {
        this.sRealName = sRealName;
    }

    public List<Pager> getPagers() {
        return pagers;
    }

    public void setPagers(List<Pager> pagers) {
        this.pagers = pagers;
    }

    public List<AnsweredPager> getAnsweredPagers() {
        return answeredPagers;
    }

    public void setAnsweredPagers(List<AnsweredPager> answeredPagers) {
        this.answeredPagers = answeredPagers;
    }

    /**
     * 获取未完成问卷
     */
    public List<AnsweredPager> getUnfinishedPager(){
//DataSupport.where("question_id = ?", String.valueOf(id)).find(Answer.class)
        return DataSupport.where("userinfo_id = ? and status = ?", String.valueOf(id), "0").find(AnsweredPager.class);
    }

    /**
     * 获取模板问卷
     * @return
     */
    public List<Pager> getTemlpatePager(){
        return DataSupport.where("userinfo_id = ?", String.valueOf(id)).find(Pager.class);
    }

}

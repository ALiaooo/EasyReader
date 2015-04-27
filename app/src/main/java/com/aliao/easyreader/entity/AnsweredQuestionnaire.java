package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by liaolishuang on 15/4/27.
 * 已作答的问卷
 */
public class AnsweredQuestionnaire extends DataSupport {
    private String iID;//问题所在问卷中的id
    private String QNum;//问题序号-页面上提示的Q2
    private String questionType;//问题类型
    private String iQuestionID;//问题所在题库中的id-------------->与iID的区别?
    private String sShowID;//问题所在题库中的编号----------------->有什么用，要显示吗?
    private String sQuestionTilte;//问题标题
    private String bISMust;//是否必填（true or false）
    private String iCatalogID;//该问题所属目录（目录ID）
    private String iSort;//问题排序
    private String iCatalogSort;//目录的排序字段（冗余一下，用不到就放着）
    private String iScore;//分值或开放回答个数（暂时无用）
    private String iCategory;//问题类型ID
    private String sCategoryText;//问题类型名称（单选题）
    private String iTemplateID;//打分题模板id（多选打分题用 目前无用）
    private String iAnswerNumber;//最大允许回答数
    private String sDescription;//问题描述（在打分题的问题后面有一段描述 如10-非常满意）
    private List<Answer> options;//问题选项
    private List<RecommendAnswer> recommendAnswers ;//开放题推荐选项
    private List<Logic> logics;
    private String timeStamp;//开始答题起始时间
    private Survey survey;

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getQuestionTilte() {
        return sQuestionTilte;
    }

    public String getQNum() {
        return QNum;
    }

    public String getCategoryText() {
        return sCategoryText;
    }

    public List<Answer> getOptions() {
        return options;
    }

    public List<Logic> getLogics() {
        return logics;
    }

    public String getbISMust() {
        return bISMust;
    }

    public String getiCatalogID() {
        return iCatalogID;
    }

    public void setsQuestionTilte(String sQuestionTilte) {
        this.sQuestionTilte = sQuestionTilte;
    }

    public String getiScore() {

        return iScore;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public void setQNum(String QNum) {
        this.QNum = QNum;
    }

    public void setiQuestionID(String iQuestionID) {
        this.iQuestionID = iQuestionID;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setsShowID(String sShowID) {
        this.sShowID = sShowID;
    }

    public void setbISMust(String bISMust) {
        this.bISMust = bISMust;
    }

    public void setiCatalogID(String iCatalogID) {
        this.iCatalogID = iCatalogID;
    }

    public void setiSort(String iSort) {
        this.iSort = iSort;
    }

    public void setiCatalogSort(String iCatalogSort) {
        this.iCatalogSort = iCatalogSort;
    }

    public void setiScore(String iScore) {
        this.iScore = iScore;
    }

    public void setiCategory(String iCategory) {
        this.iCategory = iCategory;
    }

    public void setiTemplateID(String iTemplateID) {
        this.iTemplateID = iTemplateID;
    }

    public void setsCategoryText(String sCategoryText) {
        this.sCategoryText = sCategoryText;
    }

    public void setiAnswerNumber(String iAnswerNumber) {
        this.iAnswerNumber = iAnswerNumber;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public void setRecommendAnswers(List<RecommendAnswer> recommendAnswers) {
        this.recommendAnswers = recommendAnswers;
    }

    public void setLogics(List<Logic> logics) {
        this.logics = logics;
    }

    public void setQuestionTilte(String sQuestionTilte) {
        this.sQuestionTilte = sQuestionTilte;
    }

    public String getiID() {
        return iID;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getiQuestionID() {
        return iQuestionID;
    }

    public String getsShowID() {
        return sShowID;
    }

    public String getsQuestionTilte() {
        return sQuestionTilte;
    }

    public String getiSort() {
        return iSort;
    }

    public String getiCatalogSort() {
        return iCatalogSort;
    }

    public String getiCategory() {
        return iCategory;
    }

    public String getsCategoryText() {
        return sCategoryText;
    }

    public String getiTemplateID() {
        return iTemplateID;
    }

    public String getiAnswerNumber() {
        return iAnswerNumber;
    }

    public String getsDescription() {
        return sDescription;
    }

    public List<RecommendAnswer> getRecommendAnswers() {
        return recommendAnswers;
    }





}

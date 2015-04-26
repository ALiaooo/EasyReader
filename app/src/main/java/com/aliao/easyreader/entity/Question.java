package com.aliao.easyreader.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/23.
 */
public class Question extends DataSupport implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private List<Logic> logics;//逻辑关系，如果iQuestionID选择选项isSelectAnswers则从iSkipFrom跳转至iSkipTo
//    private boolean questionRequested;//是否必答 枚举类型？enum
//    private String qIndex;//问题序号 eg: Q1，Q2
//    private String qImgUrl;//问题图片url


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

    public String getiScore() {
        return iScore;
    }

    public void setQuestionTilte(String sQuestionTilte) {
        this.sQuestionTilte = sQuestionTilte;
    }
}

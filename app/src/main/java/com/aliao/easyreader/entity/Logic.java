package com.aliao.easyreader.entity;

/**
 * Created by liaolishuang on 15/4/23.
 */
public class Logic {

    private String iID;//逻辑主键id
    private String iQuestionID;//问卷问题id------->?
    private String iSelectAnswers;//回答的选项
    private String iSkipFrom;//从哪题开始跳转
    private String iSkipTo;//跳转至哪题
    private String iLogicType;//跳转类型：1.单个跳转 3.直接结束 4.结束问卷并提交-------？


}

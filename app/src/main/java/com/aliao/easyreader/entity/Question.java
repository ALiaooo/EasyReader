package com.aliao.easyreader.entity;

/**
 * Created by 丽双 on 2015/4/23.
 */
public class Question {
    private String questionId;//问题编号
    private String questionTitle;//问题内容
    private String questionType;//问题类型
    private boolean questionRequested;//是否必答 枚举类型？enum
    private String qIndex;//问题序号 eg: Q1，Q2
    private String qImgUrl;//问题图片url
}

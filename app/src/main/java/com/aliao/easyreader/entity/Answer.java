package com.aliao.easyreader.entity;

import java.util.List;

/**
 * Created by 丽双 on 2015/4/23.
 */
public class Answer {
//    private String anserId;//答案id
//    private String answerTitle;//答案内容
//    private boolean isSelected;//是否被选择
//    private String questionOrder;//对应的题目序号-题目跳转
//    private boolean isTerminated;//是否被终止
//    private boolean isMustAnswer;//是否是必答题
//    private String aImgUrl;//选项图片url
//    private List<Question> questions;//子问题


    private String iID;//问题选项id，入库的时候存的answer就是这个id
    private String sOptionTitle;//选项名称
    private String iSort;//选项排序
    private String bISOther;//是否是开放选项（true的情况下，该选项后面有文本框让用户自己填写）
    private List<Score> scorings;//多选打分题下的打分

}

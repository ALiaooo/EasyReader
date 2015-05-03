package com.aliao.easyreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.aliao.easyreader.R;
import com.aliao.easyreader.adapter.AnswerOptionsAdapter;
import com.aliao.easyreader.entity.Answer;
import com.aliao.easyreader.entity.AnsweredPager;
import com.aliao.easyreader.entity.AnsweredQuestion;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.utils.Contents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaolishuang on 15/5/3.
 */
public class UnfinishedQuestionFragment extends Fragment implements AdapterView.OnItemClickListener{

    private AnsweredQuestion mAnsweredQuestion;
    private Question mQuestion;
    private List<Answer> mAnswerOptionList = new ArrayList<>();
    private TextView mTvQTitle;
    private ListView mLvAnswerOption;
    private AnswerOptionsAdapter mAdapter;

    public static UnfinishedQuestionFragment newInstance(AnsweredQuestion question) {
        UnfinishedQuestionFragment fragment = new UnfinishedQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(Contents.QUESTION_OBG, question);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()){
            mAnsweredQuestion = (AnsweredQuestion) getArguments().getSerializable(Contents.QUESTION_OBG);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemnt_answer_question, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mQuestion = mAnsweredQuestion.getQuestion();
        mAnswerOptionList.addAll(mQuestion.getAnswerOptions());//题目选项
        mTvQTitle = (TextView) view.findViewById(R.id.tv_question_title);
        mLvAnswerOption = (ListView) view.findViewById(R.id.lv_answer_option_list);
        //设置题目
        mTvQTitle.setText("Q"+mQuestion.getQNum()+"："+mQuestion.getQuestionTilte()+" ["+mQuestion.getCategoryText()+"]");
        //设置问题选项
        mAdapter = new AnswerOptionsAdapter(getActivity(), mAnswerOptionList);
        mLvAnswerOption.setAdapter(mAdapter);
        mLvAnswerOption.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

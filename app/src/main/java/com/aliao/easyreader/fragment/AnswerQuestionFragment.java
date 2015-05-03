package com.aliao.easyreader.fragment;

import android.app.Activity;
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
import com.aliao.easyreader.entity.AnsweredQuestion;
import com.aliao.easyreader.entity.AnsweredQuestionnaire;
import com.aliao.easyreader.entity.Logic;
import com.aliao.easyreader.entity.Question;
import com.aliao.easyreader.utils.Contents;
import com.aliao.easyreader.utils.L;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/4/24.
 * 做题
 */
public class AnswerQuestionFragment extends Fragment implements AdapterView.OnItemClickListener {

    private TextView mTvQTitle;
    private Question mQuestion;
    private AnsweredQuestion mAnsweredQuestion;
    private ListView mLvAnswerOption;
    private AnswerOptionsAdapter mAdapter;
    private OnAnswerQuestionFragmentInteractionListener mListener;
    private List<Answer> mAnswerOptionList;
    private List<Logic> mJumpLogicList;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AnswerQuestionFragment newInstance(AnsweredQuestion question) {
        AnswerQuestionFragment fragment = new AnswerQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(Contents.QUESTION_OBG, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()){
//            mQuestion = (Question) getArguments().getSerializable(Contents.QUESTION_OBG);
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
        mAnswerOptionList = new ArrayList<>();
//        mAnswerOptionList.addAll(mQuestion.getAnswerOptions());//题目选项
        mAnswerOptionList.addAll(mQuestion.getAnswerOptions());//题目选项
        mJumpLogicList = new ArrayList<>();
        mJumpLogicList.addAll(mQuestion.getLogicList());
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

        //保存该题答案和位置
        String answerOptionId = mAnswerOptionList.get(position).getiID();
        mAnsweredQuestion.setSAnswers(answerOptionId);

//        mQuestion.setOptionOrder(position);
        //将question返回
        Bundle bundle = new Bundle();
        bundle.putSerializable(Contents.QUESTION_OBG, mQuestion);
        mListener.onAnswerQuestionFragmentInteraction(bundle);

        /**
         * 确定点击了哪个选项
         */


        L.d("onItemClick-postion = "+mLvAnswerOption.getCheckedItemPosition());



        Logic logic = null;

        //选择了某个id以后，根据id开始匹配logic
//        for (int i = 0; i< mJumpLogicList.size(); i++){
//            if (mJumpLogicList.get(i).getSelectAnswers().equals(answerOptionId)){
//                logic = mJumpLogicList.get(i);
//                break;
//            }
//        }

        //保存到已答问卷表里


//        Bundle bundle = new Bundle();
//        bundle.putString(Contents.ANSWER_OPTION_ID, answerOptionId);
//        bundle.putSerializable(Contents.LOGIC_OBG, logic);


        

        //需要把题号传回去
//        mListener.onAnswerQuestionFragmentInteraction(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAnswerQuestionFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAnswerQuestionFragmentInteractionListener {
        public void onAnswerQuestionFragmentInteraction(Bundle bundle);
    }

}

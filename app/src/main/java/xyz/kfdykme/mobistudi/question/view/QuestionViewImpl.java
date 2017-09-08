package xyz.kfdykme.mobistudi.question.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.question.adapter.AnswerAdapter;
import xyz.kfdykme.mobistudi.bean.StudyQuestion;
import xyz.kfdykme.mobistudi.common.BaseViewImpl;
import xyz.kfdykme.mobistudi.common.MobiActivity;
import xyz.kfdykme.mobistudi.question.model.QuestionModelImpl;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/22 00:20.
 * Last Edit on 2017/8/22 00:20
 * 修改备注：
 */

public class QuestionViewImpl extends BaseViewImpl implements IQuestionView {

    private View rView;

    QuestionModelImpl mModel;

    RecyclerView mRvAnswer;

    AnswerAdapter aAdapter;

    TextView mTvQuesion;

    TextView mTvCheck;

    Spinner mSpinner;


    int QuestionPos =0;

    public QuestionViewImpl(MobiActivity activity) {
        super(activity);

        getActivity().getToolbar().setTitle("题目练习");


        rView = LayoutInflater.from(activity).inflate(R.layout.activity_question,null);

        mTvQuesion = (TextView) activity.findViewById(R.id.activity_question_question_description);

        mRvAnswer = (RecyclerView) activity.findViewById(R.id.activity_question_answer_rv);

        mTvCheck = (TextView) activity.findViewById(R.id.tv_check);

        mTvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setaAdapter(QuestionPos +1 == mModel.getStudyCourse().getQuestions().size()?QuestionPos:QuestionPos+1);
            }
        });

        mSpinner = (Spinner) activity.findViewById(R.id.spinner);

    }



    public QuestionModelImpl getModel() {
        return mModel;
    }


    public void setModel(QuestionModelImpl mModel) {
        this.mModel = mModel;
    }

    @Override
    public View getView() {
        return rView;
    }

    public void setaAdapter(int pos){
        getActivity().setSubTitle("这是第"+(pos+1)+"题，共有"+mModel.getStudyCourse().getQuestions().size()+"题");
        final StudyQuestion question = mModel.getStudyCourse().getQuestions().get(pos);
        QuestionPos = pos;

        aAdapter = new AnswerAdapter(getActivity(),question);
        aAdapter.setCurrentAnswerPosition(pos);
        question.setWeight(StudyQuestion.DEFAULT_WEIGHT);
        aAdapter.setItemOnClickListener(new AnswerAdapter.ItemOnClickListener() {
            @Override
            public void onClick(View view, int position) {

                boolean isCorrect = aAdapter.chooseAAnswer(view,position);

            }
        });
        mRvAnswer.setAdapter(aAdapter);
        mRvAnswer.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTvQuesion.setText(question.getDescription());
        Log.i("mTvQuestion",question.getDescription());
    }

    public RecyclerView getRvAnswer() {
        return mRvAnswer;
    }

    public void setRvAnswer(RecyclerView mRvAnswer) {
        this.mRvAnswer = mRvAnswer;
    }

    public AnswerAdapter getaAdapter() {
        return aAdapter;
    }

    public void setaAdapter(AnswerAdapter aAdapter) {
        this.aAdapter = aAdapter;
    }

    public TextView getTvQuesion() {
        return mTvQuesion;
    }

    public void setTvQuesion(TextView mTvQuesion) {
        this.mTvQuesion = mTvQuesion;
    }

    public TextView getTvCheck() {
        return mTvCheck;
    }

    public void setTvCheck(TextView mTvCheck) {
        this.mTvCheck = mTvCheck;
    }

    public Spinner getSpinner() {
        return mSpinner;
    }

    public void setSpinner(Spinner mSpinner) {
        this.mSpinner = mSpinner;
    }



}

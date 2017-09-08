package xyz.kfdykme.mobistudi.question.presenter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.common.BasePresenter;
import xyz.kfdykme.mobistudi.common.MobiActivity;
import xyz.kfdykme.mobistudi.question.model.QuestionModelImpl;
import xyz.kfdykme.mobistudi.question.view.QuestionViewImpl;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/22 15:31.
 * Last Edit on 2017/8/22 15:31
 * 修改备注：
 */

public class QuestionPresenterImpl extends BasePresenter<QuestionModelImpl,QuestionViewImpl> implements IQuestionPresenter {

    private MobiActivity activity;

    private  QuestionModelImpl mModel;

    private QuestionViewImpl mView;

    private String TAG = "QuestionPresenter" ;

    public QuestionPresenterImpl(MobiActivity activity, QuestionModelImpl model, QuestionViewImpl view) {
        super(activity);
        this.activity = activity;
        this.mModel = model;
        this.mView = view;
        mView.setModel(model);
    }

    public void onBack(){
        if(mView.getaAdapter() == null) {
            activity.finish();
            return ;
        }
        if(mView.getaAdapter().getCurrentAnswerPosition() == 0) {
            activity.finish();
            return;
        }
        if(mView.getaAdapter().getCurrentAnswerPosition()>0) mView.setaAdapter(mView.getaAdapter().getCurrentAnswerPosition()-1);
    }


    @Override
    public void getCourse(StudyCourse course) {

    }

    @Override
    protected void onAttach() {

        Log.i(TAG,"onAttach start");
        activity.showProgressDialog();
        activity.setTitle(mModel.getStudyCourse().getTitle());

        if (mModel.getStudyCourse().getQuestions()!= null)
        {
            mView.setaAdapter(0);List<String> list = new ArrayList<String>();


            for(int i =0;i<mModel.getStudyCourse().getQuestions().size();i++){
                list.add("第"+(i+1)+"题");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.spinner_item_question,list);
            adapter.setDropDownViewResource(R.layout.spinner_item_question);

            mView.getSpinner().setAdapter(adapter);

            mView.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    mView.setaAdapter(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }


        activity.dismissProgressDialog();
        Log.i(TAG,activity.getMobiProgressDialog() == null ?"null":"!null");


        Log.i(TAG,"onAttach done");
    }

    @Override
    protected void onDetach() {

    }


}

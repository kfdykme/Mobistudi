package xyz.kfdykme.mobistudi.question;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import chinaykc.mobistudi.R;
import okhttp3.Response;
import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.common.MobiActivity;
import xyz.kfdykme.mobistudi.question.model.QuestionModelImpl;
import xyz.kfdykme.mobistudi.question.presenter.QuestionPresenterImpl;
import xyz.kfdykme.mobistudi.question.view.QuestionViewImpl;

public class QuestionActivity extends MobiActivity {


    QuestionModelImpl mModel;

    QuestionViewImpl mView;

    QuestionPresenterImpl mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }






//    private void onBack(){
//        if(aAdapter == null) finish();
//        if(aAdapter.getCurrentAnswerPosition() == 0) finish();
//        if(aAdapter.getCurrentAnswerPosition()>0) setaAdapter(aAdapter.getCurrentAnswerPosition()-1);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK) mPresenter.onBack();
        return false;    
    }
//
//    private void initView(){
//
//        mTvQuesion = (TextView) findViewById(R.id.activity_question_question_description);
//
//        mRvAnswer = (RecyclerView) findViewById(R.id.activity_question_answer_rv);
//
//        mRvAnswer.setLayoutManager(new LinearLayoutManager(this));
//
//        mTvCheck = (TextView) findViewById(R.id.tv_check);
//        mSpinner = (Spinner) findViewById(R.id.spinner);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
//        mToolbar.setTitle(mCourse.getTitle());
//    }



    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetCourse(final StudyCourse course){

        if(getToolbar() == null) {
            setContentView(R.layout.activity_question);
            initToolbar((Toolbar) findViewById(R.id.toolbar));


        }

        if(mModel == null) mModel = new QuestionModelImpl(course);

        if (mView == null) mView = new QuestionViewImpl(this);

        if(mPresenter == null) {
            mPresenter = new QuestionPresenterImpl(this,mModel,mView);


        }

        mPresenter.attach();

        //mCourse = course;
        //if(mRvAnswer == null ) initView();





        //new QuestionService(getApplicationContext()).getQuestion(mCourse.getTitle());

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetQuestion(Response response){
        try {
            String body = response.body().string();
            Log.i("Question",body);
            //Toast.makeText(getApplicationContext(),body,Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Question",e.toString());
        }


    }

//    public void setaAdapter(int pos){
//        mToolbar.setSubtitle(":"+(pos+1)+"/"+mCourse.getQuestions().size());
//        final StudyQuestion question = mCourse.getQuestions().get(pos);
//
//        //mTvQuesion.setText(question.getDescription());
//        aAdapter = new AnswerAdapter(this,question);
//        aAdapter.setCurrentAnswerPosition(pos);
//        question.setWeight(StudyQuestion.DEFAULT_WEIGHT);
//        aAdapter.setItemOnClickListener(new AnswerAdapter.ItemOnClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//                boolean isCorrect = aAdapter.chooseAAnswer(view,position);
//
//                doAddData(isCorrect,question);
//            }
//        });
//        mRvAnswer.setAdapter(aAdapter);
//
//        mTvQuesion.setText(question.getDescription());
//        Log.i("mTvQuestion",question.getDescription());
//    }

//    private void doAddData(boolean isCorrect,StudyQuestion question){
//        boolean isAddedToUser = false;
//        //如果数据长度为零，就添加all和user数据
//        if(mCourse.getCourseDataPerUsers() == null) mCourse.setCourseDataPerUsers(new ArrayList<CourseDataPerUser>());
//        if(mCourse.getCourseDataPerUsers().size() == 0)
//        {
//            mCourse.getCourseDataPerUsers().add(new CourseDataPerUser(CourseDataPerUser.TYPE_ALL));
//            mCourse.getCourseDataPerUsers().add(new CourseDataPerUser(CourseDataPerUser.TYPE_USER,LocalDataStruct.readLocalData().getLoginedUser()));
//        }
//        //遍历 为all和user叠加数据
//        for(CourseDataPerUser c:mCourse.getCourseDataPerUsers())
//        {
//            if(c.getType()==CourseDataPerUser.TYPE_ALL)
//            {
//                CourseDataPerUser.Data d = c.getData();
//                d.setAllWeight(d.getAllWeight()+question.getWeight());
//                if(isCorrect){
//                    d.setGoodWegiht(d.getGoodWegiht()+question.getWeight());
//                }else{
//                    d.setBadWeight(d.getBadWeight()+question.getWeight());
//                }
//                c.setData(d);
//                Log.i("Answer","add all");
//            }
//
//            //如果为user叠加了数据，就设isAddedToUser为true
//            if(c.getUser()!= null && c.getUser().getId()
//                    .equals(LocalDataStruct.readLocalData().getLoginedUser().getId())){
//                CourseDataPerUser.Data d = c.getData();
//                d.setAllWeight(d.getAllWeight()+question.getWeight());
//                if(isCorrect){
//                    d.setGoodWegiht(d.getGoodWegiht()+question.getWeight());
//                }else{
//                    d.setBadWeight(d.getBadWeight()+question.getWeight());
//                }
//                c.setData(d);
//                isAddedToUser = true;
//                Log.i("Answer","add user "+question.getWeight());
//            }
//
//        }
//
//        if(!isAddedToUser){
//            mCourse.getCourseDataPerUsers()
//                    .add(new CourseDataPerUser(
//                            CourseDataPerUser.TYPE_USER
//                            ,LocalDataStruct.readLocalData().getLoginedUser()));
//            for(CourseDataPerUser c:mCourse.getCourseDataPerUsers())
//            {
//
//                //如果为user叠加了数据，就设isAddedToUser为true
//                if(c.getUser()!= null && c.getUser().getId()
//                        .equals(LocalDataStruct.readLocalData().getLoginedUser().getId())){
//                    CourseDataPerUser.Data d = c.getData();
//                    d.setAllWeight(d.getAllWeight()+question.getWeight());
//                    if(isCorrect){
//                        d.setGoodWegiht(d.getGoodWegiht()+question.getWeight());
//                    }else{
//                        d.setBadWeight(d.getBadWeight()+question.getWeight());
//                    }
//                    c.setData(d);
//                    isAddedToUser = true;
//                    Log.i("Answer","add user");
//                }
//
//            }
//        }
//
//        Log.i("Answer",new Gson().toJson(mCourse.getCourseDataPerUsers()));
//        MainActivity.localData.saveCourse(mCourse);
//    }
}

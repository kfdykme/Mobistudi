package xyz.kfdykme.mobistudi.question.model;

import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.bean.StudyQuestion;
import xyz.kfdykme.mobistudi.common.BaseModelImpl;
import xyz.kfdykme.mobistudi.common.IBaseModel;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/22 15:14.
 * Last Edit on 2017/8/22 15:14
 * 修改备注：
 */

public class QuestionModelImpl extends BaseModelImpl implements IQuestionModel {

    StudyCourse mCourse;

    StudyQuestion mCurrentQuestion;

    public QuestionModelImpl(StudyCourse mCourse) {
        init(mCourse);
    }

    @Override
    public void init(StudyCourse course) {
        this.mCourse = course;
    }

    @Override
    public void chooseQuestion(int pos) {
        this.mCurrentQuestion = mCourse.getQuestions().get(pos);
    }

    @Override
    public StudyCourse getStudyCourse() {
        return mCourse;
    }


}

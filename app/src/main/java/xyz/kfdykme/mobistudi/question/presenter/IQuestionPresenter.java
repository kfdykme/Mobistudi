package xyz.kfdykme.mobistudi.question.presenter;

import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.bean.StudyQuestion;
import xyz.kfdykme.mobistudi.question.model.IQuestionModel;
import xyz.kfdykme.mobistudi.question.view.IQuestionView;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/22 15:28.
 * Last Edit on 2017/8/22 15:28
 * 修改备注：
 */

public interface IQuestionPresenter<M extends IQuestionModel,V extends IQuestionView> {
    void getCourse(StudyCourse course);

}

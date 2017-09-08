package xyz.kfdykme.mobistudi.question.model;

import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.common.IBaseModel;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/22 15:10.
 * Last Edit on 2017/8/22 15:10
 * 修改备注：
 */

public interface IQuestionModel extends IBaseModel{

    void init(StudyCourse course);

    void chooseQuestion(int pos);

    StudyCourse getStudyCourse();

}

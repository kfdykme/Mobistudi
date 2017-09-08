package xyz.kfdykme.mobistudi.eventbus;

import xyz.kfdykme.mobistudi.bean.StudyCourse;

/**
 * Created by kf on 5/26/17.
 */

public class CourseDetailEvent {
    StudyCourse course;

    public CourseDetailEvent(StudyCourse course) {
        this.course = course;
    }

    public StudyCourse getCourse() {
        return course;
    }

    public void setCourse(StudyCourse course) {
        this.course = course;
    }
}

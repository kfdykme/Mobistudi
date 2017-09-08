package xyz.kfdykme.mobistudi.eventbus;

import xyz.kfdykme.mobistudi.bean.StudySubject;

/**
 * Created by kf on 5/26/17.
 */

public class CourseUntiMapEvent {

    StudySubject subject;

    public CourseUntiMapEvent(StudySubject subject) {
        this.subject = subject;
    }

    public StudySubject getSubject() {
        return subject;
    }

    public void setSubject(StudySubject subject) {
        this.subject = subject;
    }
}

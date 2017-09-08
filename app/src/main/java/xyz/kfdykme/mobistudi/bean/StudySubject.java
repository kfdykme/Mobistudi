package xyz.kfdykme.mobistudi.bean;

import java.util.List;

/**
 * Created by kf on 5/26/17.
 */

public class StudySubject {

    String id;

    String title;

    String description;

    List<String> studyCoursesId;

    List<StudyCourse> studyCourses;

    public StudySubject(String id, String title, String description, List<String> studyCoursesId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studyCoursesId = studyCoursesId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getStudyCoursesId() {
        return studyCoursesId;
    }

    public void setStudyCoursesId(List<String> studyCoursesId) {
        this.studyCoursesId = studyCoursesId;
    }

    public List<StudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(List<StudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    @Override
    public String toString() {
        return "StudySubject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", studyCoursesId=" + studyCoursesId +
                ", studyCourses=" + studyCourses +
                '}';
    }
}

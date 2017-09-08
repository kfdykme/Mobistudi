package xyz.kfdykme.mobistudi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kf on 5/26/17.
 */

public class StudyUser {

    String id;

    int typed;// 0 -- system 1 -- teacher 2 -- student

    public static int TYPE_NORMAL = 3;

    String name;

    public String getPassword() {
        return password;
    }

    String password;

    List<String> doingSubjectId = new ArrayList<String>();

    List<StudySubject> doingSubject = new ArrayList<StudySubject>();



    public List<StudySubject> getDoingSubject() {
        return doingSubject;
    }

    public void setDoingSubject(List<StudySubject> doingSubject) {
        this.doingSubject = doingSubject;
    }

    public StudyUser(String id, int typed, String name, String password) {
        this.id = id;
        this.typed = typed;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTyped() {
        return typed;
    }

    public void setTyped(int typed) {
        this.typed = typed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDoingSubjectIds() {
        return doingSubjectId;
    }

    public void setDoingSubjectIds(List<String> dongSubjectIds) {
        this.doingSubjectId = dongSubjectIds;
    }
}

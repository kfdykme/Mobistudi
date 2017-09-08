package xyz.kfdykme.mobistudi.structmap.model;

import android.util.Log;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.bean.StudySubject;
import xyz.kfdykme.mobistudi.structmap.view.StructMapView;
import xyz.kfdykme.mobistudi.structmap.view.StructView;

/**
 * Project Name: Mobistudi
 * Class Description:
 * Created by kf on 2017/9/8 21:42.
 * Last Edit on 2017/9/8 21:42
 * 修改备注：
 */

public class StructMapModel implements IStructMapModel {

    StudySubject mSubject;


    public StructMapModel(){

    }

//    public void initMap(StructView view){
//
//
//
//
//        }
//
//    }

    @Override
    public void initStructMapColor() {

    }



    @Override
    public StudySubject getSubject() {
        return mSubject;
    }

    @Override
    public void setSubject(StudySubject subject) {
        mSubject = subject;
    }
}

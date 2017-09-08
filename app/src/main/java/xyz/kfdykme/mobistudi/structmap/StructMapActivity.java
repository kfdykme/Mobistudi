package xyz.kfdykme.mobistudi.structmap;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.StudyCourse;
import xyz.kfdykme.mobistudi.bean.StudySubject;
import xyz.kfdykme.mobistudi.bean.StudyUser;
import xyz.kfdykme.mobistudi.common.MobiActivity;
import xyz.kfdykme.mobistudi.eventbus.CourseUntiMapEvent;
import xyz.kfdykme.mobistudi.model.LocalDataStruct;
import xyz.kfdykme.mobistudi.structmap.model.KfMapData;
import xyz.kfdykme.mobistudi.structmap.model.StructMapModel;
import xyz.kfdykme.mobistudi.structmap.presenter.StructMapPresenter;
import xyz.kfdykme.mobistudi.structmap.view.StructMapView;
import xyz.kfdykme.mobistudi.structmap.view.StructView;


public class StructMapActivity extends MobiActivity {



    StudyUser user;

    StudySubject subject;

    StructMapView mView;

    StructMapPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_course_unit_map);
//        initToolbar((Toolbar) findViewById(R.id.toolbar));
        user = LocalDataStruct.readLocalData().getLoginedUser();

    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onCourseUnitMapEvent(CourseUntiMapEvent e) {
        //if(mRv == null) initView();
        subject = e.getSubject();
        if(mView == null) {
            mView = new StructMapView(this);
            //mView.initView(subject);
            mPresenter = new StructMapPresenter(this);
            mPresenter.addView(mView);
            StructMapModel model = new StructMapModel();
            model.setSubject(subject);
            //model.initMap(mView.getMapView());
            mPresenter.setModel(model);
            mPresenter.attach();
        }
        //mAdapter = new CourseUnitMapAdapter(this,subject.getStudyCourses());
        //setTitle(e.getSubject().getTitle());
       // mRv.setAdapter(mAdapter);

        /*设置radioButton的状态
         * 1.读取loginedUser数据
         * 2.寻找对应subect是否是正在学习的subject
         * 3.根据结果映射
         */

        //
        //        mRBtn.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Boolean isLearnThised = false;
        //
        //
        //                if(user!= null&&user.getDoingSubjectIds()!=null)
        //                for(String id:user.getDoingSubjectIds())
        //                {
        //                    if(id.equals(subject.getId()))
        //                    {
        //                        isLearnThised = true;
        //                    }
        //                }
        //                if(!isLearnThised){
        //                    //add to studying subject
        //                    if(user.getDoingSubjectIds()== null) user.setDoingSubjectIds(new ArrayList<String>());
        //                    user.getDoingSubjectIds().add(subject.getId());
        //                    Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
        //                } else{
        //                    //remove from
        //                    for (int i = 0; i < user.getDoingSubjectIds().size();i++){
        //                        if(user.getDoingSubjectIds().get(i).equals(subject.getId()))
        //                        {
        //                            user.getDoingSubjectIds().remove(i);
        //                        }
        //                    }
        //                }
        //                LocalDataStruct ldS = LocalDataStruct.readLocalData();
        //                ldS.setLoginedUser(user);
        //                LocalDataStruct.writeLocalData(ldS);
        //                Toast.makeText(getApplicationContext(),"removed",Toast.LENGTH_SHORT).show();
        //            }
        //        });

    }
}
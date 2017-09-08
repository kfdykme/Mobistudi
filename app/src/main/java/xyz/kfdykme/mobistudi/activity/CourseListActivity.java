package xyz.kfdykme.mobistudi.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import chinaykc.mobistudi.MainActivity;
import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.adapter.CourseListAdapter;
import xyz.kfdykme.mobistudi.common.MobiActivity;
import xyz.kfdykme.mobistudi.eventbus.CourseListEvent;
import xyz.kfdykme.mobistudi.model.BasicModel;
import xyz.kfdykme.mobistudi.model.LocalData;
import xyz.kfdykme.mobistudi.model.OkhttpModelImpl;

public class CourseListActivity extends MobiActivity {

    RecyclerView mRv;

    CourseListAdapter mAdapter;

    Snackbar mSnackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar(toolbar);

        mRv = (RecyclerView) findViewById(R.id.activity_course_list_recyclerview);

        mRv.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));

        mSnackbar = Snackbar.make(mRv,"Loading",Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();

        EventBus.getDefault().postSticky(new CourseListEvent(MainActivity.localData.getLocalModules()));

        //new OkhttpModelImpl().loadAllData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onCourseListEvent(CourseListEvent e){



        mAdapter = new CourseListAdapter(this,e.getStudyModules());

        mRv.setAdapter(mAdapter);
        mRv.setItemAnimator(new DefaultItemAnimator());
//        mSnackbar.setText("Loading finished");
//        mSnackbar.setDuration(Snackbar.LENGTH_SHORT)
        mSnackbar.dismiss();

    }

}

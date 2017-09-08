package chinaykc.mobistudi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import xyz.kfdykme.mobistudi.common.AppContext;

/**
 * Created by chinaykc on 2017/3/15.
 */

public class Course_fragment extends Fragment {

    private SwipeRefreshLayout swipeRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.course,container,false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "添加课程(*^__^*)", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);



        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(AppContext.getContext(), 1);

        recyclerView.setLayoutManager(layoutManager);


        adapter = new AddedCourseAdapter(addedCourseList);

        recyclerView.setAdapter(adapter);

        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh2);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshFruits();
                initFruits();
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });





        return  view;
    }


    private void refreshFruits() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*    runOnUiThread(new Runnable() {
        @Override
        public void run() {

        }
    }); */


            }
        }).start();
    }

    private void initFruits() {
        addedCourseList.clear();
        /*for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(AddedCourses.length);
            addedCourseList.add(AddedCourses[index]);
        }*/
        for(int i=0;i<AddedCourses.length;i++){
            addedCourseList.add(AddedCourses[i]);
        }
    }


    private AddedCourse[] AddedCourses = {new AddedCourse("数据结构在生活中的应用", R.drawable.p1,1), new AddedCourse("神奇的蒙特卡洛模拟", R.drawable.p2,0.8),
            new AddedCourse("高精度算法", R.drawable.p3,0.2), new AddedCourse("震惊！这竟然是哈夫曼的真面目", R.drawable.p4,0.4),
            new AddedCourse("图论的简要概括", R.drawable.p6,0.5), new AddedCourse("你了解“队列”吗？", R.drawable.p5,0.1),
            new AddedCourse("扑克牌中的有趣问题", R.drawable.p7,0.5), new AddedCourse("你知道八皇后问题吗？", R.drawable.p8,0.9),
            new AddedCourse("马的哈密尔顿问题", R.drawable.p9,0.7), new AddedCourse("约瑟夫环", R.drawable.p10,0.6),
            new AddedCourse("电话号码查询问题", R.drawable.p11,0.4),new AddedCourse("差分约束系统", R.drawable.p12,0.8)};


    private List<AddedCourse>addedCourseList = new ArrayList<>();

    private AddedCourseAdapter adapter;




}

/*{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view=inflater.inflate(R.layout.course,container,false);
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                           // .setAction("Action", null).show();
                }
            });

            return  view;
        }



    }*/

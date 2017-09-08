package chinaykc.mobistudi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xyz.kfdykme.mobistudi.common.AppContext;

/**
 * Created by chinaykc on 2017/3/15.
 */

public class Hot_fragment extends Fragment {

    private SwipeRefreshLayout swipeRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.hot,container,false);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(AppContext.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HotCourseAdapter(hotCourseList);
        recyclerView.setAdapter(adapter);

        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
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
        hotCourseList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(hotCourses.length);
            hotCourseList.add(hotCourses[index]);
        }
    }


    private HotCourse[] hotCourses = {new HotCourse("数据结构在生活中的应用", R.drawable.p1), new HotCourse("神奇的蒙特卡洛模拟", R.drawable.p2),
            new HotCourse("高精度算法", R.drawable.p3), new HotCourse("震惊！这竟然是哈夫曼的真面目", R.drawable.p4),
            new HotCourse("图论的简要概括", R.drawable.p6), new HotCourse("你了解“队列”吗？", R.drawable.p5),
            new HotCourse("扑克牌中的有趣问题", R.drawable.p7), new HotCourse("你知道八皇后问题吗？", R.drawable.p8),
            new HotCourse("马的哈密尔顿问题", R.drawable.p9), new HotCourse("约瑟夫环", R.drawable.p10),
            new HotCourse("电话号码查询问题", R.drawable.p11),new HotCourse("差分约束系统", R.drawable.p12)};

    private List<HotCourse> hotCourseList = new ArrayList<>();

    private HotCourseAdapter adapter;


}

package chinaykc.mobistudi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cat.mobistudi.CAT;
import chinaykc.mobistudi.studyprogress.StudyProgressActivity;
import xyz.kfdykme.mobistudi.activity.CourseDetailActivity;
import xyz.kfdykme.mobistudi.activity.CourseListActivity;
import xyz.kfdykme.mobistudi.activity.DataZoneActivity;

/**
 * Created by chinaykc on 17-5-22.
 */

public class Home_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_home,container,false);

//        Button btMyLevel2 = (Button) view.findViewById(R.id.mylevel2);
//        btMyLevel2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DataZoneActivity.class);
//                startActivity(intent);
//            }
//        });

        Button btRateofProgress2 = (Button) view.findViewById(R.id.rateofprogress2);
        btRateofProgress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudyProgressActivity.class);
                startActivity(intent);
            }
        });

        Button catButton = (Button) view.findViewById(R.id.catButton);
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CAT.class);
                startActivity(intent);
            }
        });

        Button btStarttolearn = (Button) view.findViewById(R.id.starttolearn);
        btStarttolearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CourseListActivity.class);
                startActivity(intent);
            }
        });

        Button btLearnedDays = (Button) view.findViewById(R.id.learneddays);
        btLearnedDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseDetailActivity.class);

                startActivity(intent);
            }
        });

        return  view;
    }



}


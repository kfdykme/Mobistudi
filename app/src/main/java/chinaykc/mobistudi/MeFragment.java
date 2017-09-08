package chinaykc.mobistudi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xyz.kfdykme.mobistudi.activity.CourseListActivity;
import xyz.kfdykme.mobistudi.history.HistoryActivity;

/**
 * Created by chinaykc on 17-5-22.
 */

public class MeFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_me,container,false);


        Button button1 = (Button) view.findViewById(R.id.history);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button) view.findViewById(R.id.mycourse);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseListActivity.class);
                startActivity(intent);
            }
        });
        Button button3 = (Button) view.findViewById(R.id.questions);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Friends.class);
                startActivity(intent);
            }
        });
        Button button4 = (Button) view.findViewById(R.id.club);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Friends.class);
                startActivity(intent);
            }
        });





        return  view;
    }

}

package chinaykc.mobistudi.studyprogress;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.activity.CourseDetailActivity;

public class CourseStudyProgress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_study_progress);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
        Button button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseStudyProgress.this, CourseDetailActivity.class);
                startActivity(intent);
            }
        });
        Button button_11 = (Button) findViewById(R.id.peach_1);
        button_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseStudyProgress.this, CourseDetailActivity.class);
                startActivity(intent);
            }
        });
        Button button_12 = (Button) findViewById(R.id.peach_2);
        button_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseStudyProgress.this, CourseDetailActivity.class);
                startActivity(intent);
            }
        });

    }
}

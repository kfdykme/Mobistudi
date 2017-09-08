package chinaykc.mobistudi.start;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import chinaykc.mobistudi.MainActivity;
import chinaykc.mobistudi.R;

import static java.lang.Thread.sleep;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StartPage();
            }
        },2000);


        /*try {
            Thread.sleep(10000);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
    private void StartPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

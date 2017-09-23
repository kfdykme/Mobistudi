package cat.mobistudi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import chinaykc.mobistudi.R;

public class CATResult extends AppCompatActivity {

    private TextView text_catText;
    private TextView text_theat;
    private TextView text_correctNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catresult);

        text_catText = (TextView) findViewById(R.id.text_catError);
        text_theat = (TextView) findViewById(R.id.text_theat);
        text_correctNum = (TextView) findViewById(R.id.text_correctNum);

        SharedPreferences preferences = getSharedPreferences("errorContent", Context.MODE_APPEND);
        String errorText = preferences.getString("errText","");
        Log.d("提取错误试题",errorText);
        text_catText.setText(errorText);
        SharedPreferences preferences1 = getSharedPreferences("data",MODE_PRIVATE);
        String theat = preferences1.getString("theat","");
        text_theat.setText(theat);
        int correctNum = preferences1.getInt("correctNum",0);
        text_correctNum.setText(String.valueOf(correctNum));
    }
}

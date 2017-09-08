package xyz.kfdykme.mobistudi.question;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project Name: Mobistudi2
 * Class Description:
 * Created by kf on 2017/8/18 17:36.
 * Last Edit on 2017/8/18 17:36
 * 修改备注：
 */

public class QuestionService {
//    http://www.mobistudi.mobi/exercises.php?course='循环队列'

    Context context;

    OkHttpClient client = new OkHttpClient();

    String baseUrl = "http://www.mobistudi.mobi/exercises.php?courcises.php?";

    public QuestionService(Context context) {
        this.context = context;
    }

    public void getQuestion(String course){
        Log.i("Question","getQuestion "+course);
        Request request = new Request.Builder()
                .url(baseUrl+"course='"+course+"'")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.i("Question",e.toString());
               // Toast.makeText(context,"get Question failure",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                new EventBus().getDefault().postSticky(response);
            }
        });
    }
}

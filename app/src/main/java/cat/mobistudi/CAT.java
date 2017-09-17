package cat.mobistudi;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import chinaykc.mobistudi.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CAT extends AppCompatActivity {

    private int hasLearned = 0;
    private double difficulty;
    private Button next_Question;
    //private ProgressBar progress_test;
    private NumberProgressBar progress1;
    private RadioGroup radioGroup_answer;
    private String correct_answer = "";
    private RadioButton radioButton_checked;
    private TextView textContent;
    private TextView textContent_A;
    private TextView textContent_B;
    private TextView textContent_C;
    private TextView textContent_D;
    private int courseId = 1;//暂定
    private CreateDBAbility dbHelper;
    SQLiteDatabase db;
    private int correntNum = 0;
    int progress = 0;

    double y_cur = 0;
    double x = 0;//初始的能力值
    double step = 0.01;//每次迭代的步长
    private String abilityStorage = null;//缓存中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        LitePal.initialize(this);

        //创建能力值数据库
        dbHelper = new CreateDBAbility(getApplicationContext(),"Ability.db",null,4);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Ability",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Double dif = Double.valueOf(cursor.getString(cursor.getColumnIndex("difficult")).toString());
                Log.d("未删除数据库中","difficult: " + dif);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.delete("Ability",null,null);
        Cursor cursor2 = db.query("Ability",null,null,null,null,null,null);
        if (cursor2.moveToFirst()){
            do {
                Double dif = Double.valueOf(cursor2.getString(cursor2.getColumnIndex("difficult")).toString());
                Log.d("删除后数据库中 ","difficult: " + dif);
            }while (cursor2.moveToNext());
        }
        cursor2.close();

        Log.d("测试111111 ","删除数据成功并创建数据库成功");

        next_Question = (Button) findViewById(R.id.next_question);
        //progress_test = (ProgressBar) findViewById(R.id.progress_test);
        progress1 = (NumberProgressBar) findViewById(R.id.progress);
        radioGroup_answer = (RadioGroup) findViewById(R.id.radioGroup_answer);
        textContent = (TextView) findViewById(R.id.textContent);
        textContent_A = (TextView) findViewById(R.id.content_A);
        textContent_B = (TextView) findViewById(R.id.content_B);
        textContent_C = (TextView) findViewById(R.id.content_C);
        textContent_D = (TextView) findViewById(R.id.content_D);

        //询问是否学习过该门课程
        AlertDialog.Builder hasLearn = new AlertDialog.Builder(CAT.this);
        hasLearn.setTitle("CAT");
        hasLearn.setCancelable(false);
        hasLearn.setMessage("您之前学习过数据结构吗？");
        hasLearn.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hasLearned = 1;
                dialogInterface.dismiss();
            }
        });
        hasLearn.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hasLearned = 0;
                dialogInterface.dismiss();
            }
        });
        hasLearn.show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CAT.this);
        abilityStorage = preferences.getString("ability",null);
        if (!TextUtils.isEmpty(abilityStorage)){
            difficulty = Double.parseDouble(abilityStorage);
            if (difficulty < 0.0)
                difficulty = 0.0;
            requestQuestion(courseId,difficulty);
        }else if (hasLearned == 1){
            difficulty = 0.80;
            requestQuestion(courseId,difficulty);
        }
        if (!TextUtils.isEmpty(abilityStorage)){
            difficulty = Double.parseDouble(abilityStorage);
            if (difficulty < 0.0)
                difficulty = 0.0;
            requestQuestion(courseId,difficulty);
        }else
        if (hasLearned == 0){
            difficulty = 0.80;
            requestQuestion(courseId,difficulty);
        }
        Log.i("测试",""+difficulty);

        next_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int progress = progress_test.getProgress();

                DecimalFormat df = new DecimalFormat("0.00");
                df.setRoundingMode(RoundingMode.HALF_UP);
                progress = progress + 5;
                //progress_test.setProgress(progress);
                progress1.setProgress(progress);
                if (progress >= 100){
                    String abilityTemp = Double.toString(x);
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(CAT.this).edit();
                    editor.putString("ability",abilityTemp);
                    editor.commit();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(CAT.this);
                    dialog.setTitle("CAT");
                    dialog.setMessage("您已经完成了测试,能力值: " + df.format(x) + "max=5");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DataSupport.deleteAll(Question.class);
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }

                for (int i=0; i < radioGroup_answer.getChildCount();i++){

                    ContentValues values = new ContentValues();
                    radioButton_checked = (RadioButton) radioGroup_answer.getChildAt(i);

                    if (radioButton_checked.isChecked()){
                        if (radioButton_checked.getText().equals(correct_answer)){
                            Question selectedQuestions = DataSupport.findLast(Question.class);
                            values.put("discrimination",selectedQuestions.getDistinction());
                            values.put("difficult",difficulty);
                            values.put("result",1);
                            db.insert("Ability",null,values);
                            values.clear();
                            Theat theat = new Theat();
                            theat.getmin();
                            Log.d("做对之后的","能力值" + x);
                            difficulty = difficulty + 0.2;
                            Log.d("做对之后的","难度" + difficulty);
                            correntNum ++;
                            Log.d("做对试题数:" ,"" + correntNum);
                        }else{
                            Question selectedQuestions = DataSupport.findLast(Question.class);
                            values.put("discrimination",selectedQuestions.getDistinction());
                            values.put("difficult",difficulty);
                            values.put("result",0);
                            db.insert("Ability",null,values);
                            values.clear();
                            Theat theat = new Theat();
                            theat.getmin();
                            Log.d("做错之后的","能力值：" + x );
                            difficulty = difficulty - 0.1;
                            Log.d("做错之后的","难度" + difficulty);
                        }
                        //解决难度到达边界的情况
                        if (difficulty<0){
                            difficulty = 0.00;
                        }else if (difficulty > 2){
                            difficulty = 1.50;
                        }
                    }
                }
                Log.d("请求题目的难度:" , "" + difficulty);
                requestQuestion(courseId,difficulty);
                radioGroup_answer.clearCheck();
            }
        });
    }

    //计算能力值
    class Theat{

        public double derivative(double difficult, double discrimination, int result, double x){
            double temp=0.0;
            temp = temp + (0.25 - result + (0.75 * (1 / (1 + (Math.pow(Math.E,(-1.702 * discrimination) * (x - difficult)))))));
            return temp/20;
        }

        public double function(double difficult, double discrimination, double x){
            return 0.25 + 0.75 * (1 / (1 + (Math.pow(Math.E,(-1.702 * discrimination) * (x - difficult)))));
        }

        public void getmin( ){

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Ability",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    Double disc = Double.valueOf(cursor.getString(cursor.getColumnIndex("discrimination")).toString());
                    Double dif = Double.valueOf(cursor.getString(cursor.getColumnIndex("difficult")).toString());
                    int result = cursor.getInt(cursor.getColumnIndex("result"));
                    Log.d("已测验过的所有试题难度值 ","difficult: " + dif);
                    y_cur=function(dif,disc,x);
                    for(int i=0;i<2000;i++){//下降梯度的幅度变化大于误差，继续迭代
                        x=x-step*derivative(dif,disc,result,x);//沿梯度负方向移动
                        y_cur=function(dif,disc,x);  //y值跟着x移动变化，计算下一轮迭代
                    }
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    //根据courseId和difficulty请求获得测验的问题
    public void requestQuestion(final int courseId,final double difficulty){

        //Log.d("测试","请求数据");
        String courseUrl = "http://210.41.102.190/distinction.php?course_id=" + courseId + "&" + "difficulty=" +  difficulty;
        //Log.d("测试",courseUrl);
        HttpUtil.sendOKHttpRequest(courseUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CAT.this,"获取题目信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseText = response.body().string();
                //Log.d("测试1111111",responseText);
                Boolean result = false;
                result = Utility.handleQuestionResponse(1,responseText,difficulty);
                Log.d("测试222222",result.toString());
                if (result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Question question= DataSupport.findLast(Question.class);
                            //Log.d("测试3333333",question.getDescription());
                            textContent.setText(question.getDescription());
                            textContent_A.setText(question.getA());
                            textContent_B.setText(question.getB());
                            textContent_C.setText(question.getC());
                            textContent_D.setText(question.getD());
                            correct_answer = question.getCorrect();
                        }
                    });
                }
            }
        });
    }
}

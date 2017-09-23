package cat.mobistudi;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private NumberProgressBar progress1;
    private RadioGroup radioGroup_answer;
    private String correct_answer = "";
    private RadioButton radioButton_checked;
    private RadioButton radioButton_A;
    private RadioButton radioButton_B;
    private RadioButton radioButton_C;
    private RadioButton radioButton_D;
    private TextView textContent;
    private int courseId = 2;//暂定
    private CreateDBAbility dbHelper;
    SQLiteDatabase db;
    private int correctNum = 0;
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
        db.delete("Ability",null,null);
        //Log.d("测试111111 ","删除数据成功并创建数据库成功");

        next_Question = (Button) findViewById(R.id.next_question);
        progress1 = (NumberProgressBar) findViewById(R.id.progress);
        radioGroup_answer = (RadioGroup) findViewById(R.id.radioGroup_answer);
        textContent = (TextView) findViewById(R.id.textContent);
        radioButton_A = (RadioButton) findViewById(R.id.A);
        radioButton_B = (RadioButton) findViewById(R.id.B);
        radioButton_C = (RadioButton) findViewById(R.id.C);
        radioButton_D = (RadioButton) findViewById(R.id.D);

        //询问是否学习过该门课程
        AlertDialog.Builder hasLearn = new AlertDialog.Builder(CAT.this);
        hasLearn.setTitle("CAT");
        hasLearn.setCancelable(false);
        hasLearn.setMessage("您之前学习过数据结构吗？");
        hasLearn.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hasLearned = 1;
                difficulty = 1.38;
                requestQuestion(courseId,difficulty);
                dialogInterface.dismiss();
            }
        });
        hasLearn.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hasLearned = 0;
                difficulty = 0.86;
                requestQuestion(courseId,difficulty);
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
        }
        Log.i("请求试题难度",""+difficulty);

        next_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0; i < radioGroup_answer.getChildCount();i++){

                    ContentValues values = new ContentValues();
                    radioButton_checked = (RadioButton) radioGroup_answer.getChildAt(i);

                    if (radioButton_checked.isChecked()){
                        Question selectedQuestions = DataSupport.findLast(Question.class);
                        String choiceNum = String.valueOf(radioButton_checked.getText().charAt(0)).trim();
                        Log.d("选择的内容",""+radioButton_checked.getText());
                        Log.d("选择的选项",choiceNum);
                        Log.d("正确选项",correct_answer);
                        if (choiceNum.equals(correct_answer.trim())){
                            values.put("discrimination",selectedQuestions.getDistinction());
                            values.put("difficult",difficulty);
                            values.put("result",1);
                            db.insert("Ability",null,values);
                            values.clear();
                            Theat theat = new Theat();
                            theat.getmin();
                            Log.d("做对之后的","能力值" + x);
                            difficulty = difficulty + 0.08;
                            Log.d("做对之后的","难度" + difficulty);
                            correctNum ++;
                            Log.d("做对试题数:" ,"" + correctNum);
                            changeProgress();
                        }else{
                            values.put("discrimination",selectedQuestions.getDistinction());
                            values.put("difficult",difficulty);
                            values.put("result",0);
                            db.insert("Ability",null,values);
                            values.clear();
                            Theat theat = new Theat();
                            theat.getmin();
                            Log.d("做错之后的","能力值：" + x );
                            SharedPreferences.Editor editor = getSharedPreferences("errorText", Context.MODE_APPEND).edit();
                            Log.d("错误试题内容",selectedQuestions.getDescription());
                            editor.putString("errorText",selectedQuestions.getDescription());
                            editor.apply();
                            difficulty = difficulty - 0.04;
                            Log.d("做错之后的","难度" + difficulty);
                            changeProgress();
                        }
                        //解决难度到达边界的情况
                        if (difficulty<0.58){
                            difficulty = 0.78;
                        }else if (difficulty > 1.9){
                            difficulty = 1.56;
                        }
                    }
                }
                //Log.d("请求题目的难度:" , "" + difficulty);
                requestQuestion(courseId,difficulty);
                radioGroup_answer.clearCheck();
            }
        });
    }

    private void changeProgress() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        progress = progress + 5;
        if (progress > 100){
            //String abilityTemp = Double.toString(x);
            String ability = df.format(x);
            SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putString("theat",ability);
            Log.d("最后正确题目数",""+correctNum);
            editor.putInt("correctNum",correctNum);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(),CATResult.class);
            startActivity(intent);
            finish();
        }
        progress1.setProgress(progress);
    }

    //计算能力值
    private class Theat{

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

        String courseUrl = "http://202.115.29.226/distinction.php?course_id=" + courseId + "&" + "difficulty=" +  difficulty;
        Log.d("测试URL",courseUrl);
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
                Boolean result = false;
                result = Utility.handleQuestionResponse(1,responseText,difficulty);
                if (result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Question question= DataSupport.findLast(Question.class);
                            Log.d("测试试题内容",question.getDescription());
                            textContent.setText(question.getDescription());
                            radioButton_A.setText(question.getA());
                            radioButton_B.setText(question.getB()) ;
                            radioButton_C.setText(question.getC());
                            radioButton_D.setText(question.getD());
                            correct_answer = question.getCorrect();
                        }
                    });
                }
            }
        });
    }
}

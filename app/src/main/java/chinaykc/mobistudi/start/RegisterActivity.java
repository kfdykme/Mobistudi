package chinaykc.mobistudi.start;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.StudyUser;
import xyz.kfdykme.mobistudi.tool.FileHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText mEtName;
    EditText mEtId ;
    EditText mEtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    private void initView(){
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtId = (EditText) findViewById(R.id.et_id);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        TextView mTv = (TextView) findViewById(R.id.tv_yes);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    private void doRegister(){
        String name  = mEtName.getText().toString();
        String id = mEtId.getText().toString();
        String password = mEtPassword.getText().toString();

        //用户名昵称和密码都不能为空
        if(!name.isEmpty() && !id.isEmpty() && !password.isEmpty()){
            String data = null;
            try {
                data = FileHelper.readFile("users",id,"json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //如果找到已存在的id的话就是该id以被注册，没找到就进行注册
            if(data != null && data.equals(FileHelper.NOT_FIND)){
                String content= "";
                //创建新的实例并转化为json格式保存
                StudyUser registerUser = new StudyUser(id,StudyUser.TYPE_NORMAL,name,password);
                content = new Gson().toJson(registerUser);
                try {
                    FileHelper.createFile("users",content,id,"json");
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
                    return ;
                }
                Toast.makeText(this,"Register done.",Toast.LENGTH_SHORT).show();


                //when register done
                List<String> ls = new ArrayList<>();
                ls.add(id);
                ls.add(password);

                EventBus.getDefault().post(ls);
                finish();
            } else {
                Toast.makeText(this,"This id has been used.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter your information.", Toast.LENGTH_SHORT).show();
        }
    }


}

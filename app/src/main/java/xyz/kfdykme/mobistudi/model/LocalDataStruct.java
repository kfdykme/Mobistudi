package xyz.kfdykme.mobistudi.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import xyz.kfdykme.mobistudi.bean.StudyUser;
import xyz.kfdykme.mobistudi.tool.FileHelper;

/**
 * Created by kf on 2017/5/28.
 */

public class LocalDataStruct {

   StudyUser LoginedUser;

    public LocalDataStruct(StudyUser loginedUser) {
        LoginedUser = loginedUser;
    }

    public StudyUser getLoginedUser() {
        return LoginedUser;
    }

    public void setLoginedUser(StudyUser loginedUser) {
        LoginedUser = loginedUser;
    }

    public static void writeLocalData(LocalDataStruct data){
        String dataJson = new Gson().toJson(data);
        try {
            FileHelper.createFile("settings",dataJson,"local","json");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LocalDataStruct",e.toString());
        }
    }

    public static LocalDataStruct readLocalData(){

        try {
            String json = FileHelper.readFile("settings","local","json");
            return new Gson().fromJson(json,LocalDataStruct.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LocalDataStruct",e.toString());
            return null;
        }
    }
}

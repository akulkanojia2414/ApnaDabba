package example.akul.apnadabba;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;




/**
 * Created by akul on 12-07-2016.
 */
public class MyApplication extends Application {

    public void onCreate() {
        super.onCreate();

        String type = "fetch";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
        final MyDbHelper helper = MyDbHelper.getInstance(this);
       /* Backendless.initApp(this, BackendSettings.APPLICATION_ID, BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);*/
        helper.getWritableDatabase();
        helper.Delete();


                /*Log.d("Akul", String.valueOf(data.size()));
                for (int i = 0; i < data.size(); i++) {
                    helper.InsertPlease(data.get(i));
                }*/
    }
}












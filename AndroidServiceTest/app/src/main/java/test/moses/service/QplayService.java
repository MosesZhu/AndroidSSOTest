package test.moses.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Moses.Zhu on 2016-10-13.
 */
public class QplayService extends Service {
    private static final String TAG = "QplayServices" ;
    public static final String ACTION = "test.moses.service.QplayService";

    private boolean hasLogin = false;

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "QplayService onCreate");
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "QplayService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent itnt, int flags, int startId) {
        //Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
        Bundle comingBundle = itnt.getExtras();
        String BroadcastReceiverName = comingBundle.getString("BroadcastReceiverName");
        if(!hasLogin) { //未登陆则开启登陆页面
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), LoginActivity.class);
            //Bundle bundle = new Bundle();
            intent.putExtras(comingBundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else { //已登录则通过广播告知登陆信息

        }
        return super.onStartCommand(itnt, flags, startId);
    }
}

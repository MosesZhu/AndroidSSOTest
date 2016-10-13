package test.moses.service.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Moses.Zhu on 2016-10-13.
 */
public class LoginResultReceiver extends BroadcastReceiver {
    public static final String ACTION = "test.moses.service.client.LoginResultReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
    }
}

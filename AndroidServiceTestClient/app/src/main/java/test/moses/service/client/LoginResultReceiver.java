package test.moses.service.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Date;

/**
 * Created by Moses.Zhu on 2016-10-13.
 */
public class LoginResultReceiver extends BroadcastReceiver {
    public static final String ACTION = "test.moses.service.client.LoginResultReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Bundle bundle = intent.getExtras();

        String LoginId = bundle.getString("LoginId");
        String LoginPassword = bundle.getString("LoginPassword");
        Long LoginDate = bundle.getLong("LoginDate");
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putString("LoginId", LoginId);
        editor.putString("LoginPassword", LoginPassword);
        editor.putLong("LoginDate", LoginDate);
        editor.commit();

        Intent newIntent = new Intent(context, LoginActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}

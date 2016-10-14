package test.moses.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends Activity {

    EditText tbxLoginName;
    EditText tbxLoginPassword;
    Button btnLogin;
    Intent fromIntent;
    String broadcastReceiverName;

    private static final String SERVICE_ACTION = "test.moses.service.QplayService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initControls();
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent(SERVICE_ACTION);
        stopService(intent);
        System.exit(0);
        super.onStop();
    }

    boolean successLogin = false;
    private void initControls() {
        fromIntent = getIntent();
        Bundle bundle = fromIntent.getExtras();
        broadcastReceiverName = bundle.getString("BroadcastReceiverName");

        tbxLoginName = (EditText) findViewById(R.id.tbxLoginName);
        tbxLoginPassword = (EditText) findViewById(R.id.tbxLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), broadcastReceiverName, Toast.LENGTH_LONG).show();
                successLogin = true;
                Intent i = new Intent(broadcastReceiverName);
                Bundle toBundle = new Bundle();
                toBundle.putString("LoginId", tbxLoginName.getText().toString());
                toBundle.putString("LoginPassword", tbxLoginPassword.getText().toString());
                toBundle.putLong("LoginDate", (new Date()).getTime());
                i.putExtras(toBundle);
                sendBroadcast(i);
                System.exit(0);
            }
        });
    }
}

package test.moses.service.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class LoginActivity extends Activity {
    private static final String SERVICE_ACTION = "test.moses.service.QplayService";
    EditText tbxLoginName;
    EditText tbxLoginPassword;
    Button btnLogin;
    Button btnClearLoginInfo;

    SharedPreferences sharePreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initControls();
    }

    @Override
    protected void onResume() {
        checkLogin();
        super.onResume();
    }

    private void initControls() {
        sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        tbxLoginName = (EditText) findViewById(R.id.tbxLoginName);
        tbxLoginPassword = (EditText) findViewById(R.id.tbxLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnClearLoginInfo = (Button) findViewById(R.id.btnClearLoginInfo);
        btnClearLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferences.edit().clear().commit();
            }
        });
    }

    private void checkLogin() {
        String LoginId = sharePreferences.getString("LoginId", null);
        String LoginPassword = sharePreferences.getString("LoginPassword", null);
        Long LoginDate = sharePreferences.getLong("LoginDate", -1);
        Long now = (new Date()).getTime();
        if(LoginDate == -1 || LoginId == null || LoginPassword == null
                || (now - LoginDate) > 100000) {
            //Toast.makeText(getApplicationContext(), String.valueOf(now - LoginDate), Toast.LENGTH_LONG).show();
            callQplayService();
            //System.exit(0);
            this.finish();
        } else {
            tbxLoginName.setText(LoginId);
            tbxLoginPassword.setText(LoginPassword);
        }
    }

    private void callQplayService() {
        Intent intent = new Intent(SERVICE_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString("BroadcastReceiverName", LoginResultReceiver.ACTION);
        intent.putExtras(bundle);
        startService(intent);
    }
}

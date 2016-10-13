package test.moses.service.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String SERVICE_ACTION = "test.moses.service.QplayService";
    private Button btnCallService;
    SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
        checkLogin();
    }

    @Override
    protected void onResume() {
        checkLogin();
        super.onResume();
    }

    private void initControls() {
        sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        btnCallService = (Button) findViewById(R.id.btnCallService);
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        Intent intent = new Intent(SERVICE_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString("BroadcastReceiverName", LoginResultReceiver.ACTION);
        intent.putExtras(bundle);
        startService(intent);
    }
}

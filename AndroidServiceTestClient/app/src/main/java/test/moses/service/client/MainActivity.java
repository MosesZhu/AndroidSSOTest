package test.moses.service.client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

/**
 * Created by Moses.Zhu on 2016-10-14.
 */
public class MainActivity extends Activity {
    Button btnClearLoginInfo;

    SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        btnClearLoginInfo = (Button) findViewById(R.id.btnClearLoginInfo);
        btnClearLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferences.edit().clear().commit();
            }
        });
    }
}

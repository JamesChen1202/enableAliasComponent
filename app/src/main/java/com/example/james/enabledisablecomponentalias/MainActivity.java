package com.example.james.enabledisablecomponentalias;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String CATEGORY_FINGERPRINT = "com.example.james.enabledisablecomponentalias.FINGERPRINT_ENROLL";
    Button buttonEnable;
    Button buttonDisable;
    Button buttonQuery;
    Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {
        buttonEnable = findViewById(R.id.button1);
        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Log.d(TAG, "[buttonEnable]+++");
                PackageManager pm  = getApplicationContext().getPackageManager();
                ComponentName componentName = new ComponentName(getApplicationContext(),
                        "com.example.james.enabledisablecomponentalias.MySecondActivityAlias");
                pm.setComponentEnabledSetting(componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                Log.d(TAG, "[buttonEnable]---");
            }
        });
        buttonDisable = findViewById(R.id.button2);
        buttonDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Log.d(TAG, "[buttonDisable]+++");
                PackageManager pm  = getApplicationContext().getPackageManager();
                ComponentName componentName = new ComponentName(getApplicationContext(),
                        "com.example.james.enabledisablecomponentalias.MySecondActivityAlias");
                pm.setComponentEnabledSetting(componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                        PackageManager.DONT_KILL_APP);
                Log.d(TAG, "[buttonDisable]---");
            }
        });
        buttonQuery = findViewById(R.id.button3);
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Log.d(TAG, "[buttonQuery]+++");
                Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(CATEGORY_FINGERPRINT);
                List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);
                if(resolveInfoList != null) {
                    Log.d(TAG, "resolvePackageName size = " + resolveInfoList.size());
                    for(ResolveInfo info : resolveInfoList) {

                        Log.d(TAG, "Found activit: = " + info.activityInfo.toString()
                        +" for category: " + CATEGORY_FINGERPRINT);
                    }
                } else {
                    Log.d(TAG, "resolveInfoList == null");
                }
                Log.d(TAG, "[buttonQuery]---");
            }
        });

        buttonStart = findViewById(R.id.button4);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Log.d(TAG, "[buttonStart]+++");
                ComponentName componentName = new ComponentName(getApplicationContext(),
                        "com.example.james.enabledisablecomponentalias.MySecondActivityAlias");
                Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(CATEGORY_FINGERPRINT).setComponent(componentName);
                startActivity(intent);
                Log.d(TAG, "[buttonStart]---");
            }
        });
    }
}

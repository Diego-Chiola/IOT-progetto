package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startService(new Intent(this, CheckUse.class));
        if(UStats.getUsageStatsList(this).isEmpty()){
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        Button btn_stats = findViewById(R.id.stats_btn);
        btn_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UStats.printCurrentUsageStatus(MainActivity.this);
                Log.d("UStats", "end" + "\n\n\n");
            }
        });
    }


}
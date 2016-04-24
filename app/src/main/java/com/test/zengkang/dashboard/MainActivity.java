package com.test.zengkang.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DashboardView view = (DashboardView) findViewById(R.id.dv);
        view.setDegree(350);
    }
}

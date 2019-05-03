package com.syriataxi.www.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class CallingTaxiActivity extends AppCompatActivity {
TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_taxi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();
        textView2=findViewById(R.id.textView2);
      String  callingtaxiStr = intent.getStringExtra("callingtaxi");
        textView2.setText("يتم طلب التكسي : "+callingtaxiStr);

    }

}

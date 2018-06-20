package com.example.salman.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

public class DetailsActivity extends AppCompatActivity {

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        gson = new Gson();
        String target = getIntent().getStringExtra("MyObjectString");
        DonorEvent donorEvent = new DonorEvent(target);
        EventBus.getDefault().post(donorEvent);

    }
}

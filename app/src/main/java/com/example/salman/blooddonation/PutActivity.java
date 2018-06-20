package com.example.salman.blooddonation;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PutActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";
    private List<Donor> donorList = new ArrayList<>();
    NotificationManager notificationManager;
    String Id = "channel 1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);
        final EditText donorID = findViewById(R.id.donor_id);
        final EditText donor_fName = findViewById(R.id.donor_f_Name);
        final EditText donor_lName = findViewById(R.id.donor_l_Name);
        final EditText donor_FoneNo = findViewById(R.id.donor_fone_no);
        final EditText donorage = findViewById(R.id.donorAge);
        final EditText donorEmail = findViewById(R.id.donor_email_id);
        final EditText donor_blood_group_id = findViewById(R.id.donor_blood_group_id);
        Button updateDonor = findViewById(R.id.updateDonor);

        updateDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String donorId = donorID.getText().toString().trim();
                String FirstName = donor_fName.getText().toString().trim();
                String LastName = donor_lName.getText().toString().trim();
                String FoneNo = donor_FoneNo.getText().toString().trim();
                String age = donorage.getText().toString().trim();
                String EmailId = donorEmail.getText().toString().trim();
                String bloodGroupId = donor_blood_group_id.getText().toString().trim();

                Donor donor = new Donor(Integer.parseInt(donorId), FirstName, LastName, Integer.parseInt(FoneNo),
                        Integer.parseInt(age), EmailId, Integer.parseInt(bloodGroupId));
                donorList.add(donor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.8.100:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Donor> donorCall = apiInterface.updateDonor(Integer.parseInt(donorId), donor);
                donorCall.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(Call<Donor> call, Response<Donor> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        Intent intent = new Intent(getApplicationContext(), GetDonors.class);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(PutActivity.this, Id)
                                .setContentTitle("Donor Notification")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentText("Donor Updated");
                        Notification notification = builder.build();
                        notificationManager.notify(0, notification);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Donor> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }
        });


    }
}

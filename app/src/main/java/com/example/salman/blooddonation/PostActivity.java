package com.example.salman.blooddonation;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {


    private static final String TAG = "MTAG";
    //TextViews / EditText / Buttons/ Spinner variables
    EditText f_Name;
    EditText l_Name;
    EditText fone_No;
    EditText donor_Age;
    EditText mail_Id;
    EditText bloodGroup_Id;
    Spinner spinner;
    Button sendData;

    //Retrofit Variable

    Retrofit retrofit;

    // variables passed to Post Form
    String first_name;
    String last_name;
    Editable phone_number;
    Editable age;
    String email;
    Editable blood_group_id;

    // Notifications
    NotificationManager notificationManager;
    String Id = "channel 1";

    // data source for spinner
    String[] bloodgroups = new String[]{"Type 1 for AB+", "Type 2 for AB-", "Type 3 for A+", "Type 4 for A-", "Type 5 for B+",
            "Type 6 for B-", "Type 7 for O+", "Type 8 for O-"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        f_Name = findViewById(R.id.f_Name);
        l_Name = findViewById(R.id.l_Name);
        fone_No = findViewById(R.id.fone_no);
        donor_Age = findViewById(R.id.donor_Age);
        mail_Id = findViewById(R.id.mail_Id);
        bloodGroup_Id = findViewById(R.id.bloodGroup_Id);
        bloodGroup_Id.setFilters(new InputFilter[]{
                new InputFilterMinMax("1", "9")
        });
        spinner = findViewById(R.id.spinner);

        // Adapter for Spinner

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, bloodgroups);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerArrayAdapter);

        // Notifications
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sendData = findViewById(R.id.sendData);

        // OnClick Listener for Register Donor
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Validations

                if (f_Name.getText().toString().trim().equals("")) {
                    f_Name.setError("First Name is Required");
                } else {
                    first_name = f_Name.getText().toString().trim();
                }

                if (l_Name.getText().toString().trim().equals("")) {
                    l_Name.setError("Last Name is Required");
                } else {
                    last_name = l_Name.getText().toString().trim();
                }
                if (fone_No.getText().toString().equals("")) {
                    fone_No.setError("Phone Number is Required");
                } else {
                    phone_number = fone_No.getText();
                }
                if (donor_Age.getText().toString().equals("")) {
                    donor_Age.setError("Age is Required");
                } else {
                    age = donor_Age.getText();
                }
                if (mail_Id.getText().toString().trim().equals("")) {
                    mail_Id.setError("Email ID is Required");
                } else {
                    email = mail_Id.getText().toString().trim();
                }
                if (bloodGroup_Id.getText().toString().equals("")) {
                    bloodGroup_Id.setError("Blood Group is Required");
                } else {
                    blood_group_id = bloodGroup_Id.getText();
                }


                //String blood_group_id = spinner.getSelectedItem().toString().trim();

                retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.8.100:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<Donor> donorCall = apiInterface.saveDonor(first_name, last_name, phone_number,
                        age, email, blood_group_id);
                donorCall.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(Call<Donor> call, Response<Donor> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        Intent intent = new Intent(PostActivity.this, GetDonors.class);
                        Toast.makeText(PostActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(PostActivity.this, Id)
                                .setContentTitle("Donor Notification")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentText("New Donor Arrived");
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

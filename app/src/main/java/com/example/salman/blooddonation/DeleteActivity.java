package com.example.salman.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";
    Retrofit retrofit;


    Editable id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        final EditText inputID = findViewById(R.id.inputID);
        Button delete = findViewById(R.id.Delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputID.getText().toString().equals("")) {
                    inputID.setError("ID is Required");
                } else {
                    id = inputID.getText();
                }

                retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.8.100:8000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Donor> donorCall = apiInterface.deleteDonor(id);
                donorCall.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(Call<Donor> call, Response<Donor> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Donor> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }

        }); //
    }
}

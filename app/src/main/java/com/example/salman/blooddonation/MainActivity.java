package com.example.salman.blooddonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import retrofit2.http.DELETE;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    Button Go_to_Get_Activity;
    Button Go_to_Post_Activity;
    Button Go_to_Put_Activity;
    Button Go_to_Delete_Activity;
    Button logout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Go_to_Get_Activity = findViewById(R.id.ViewDonors);
        Go_to_Post_Activity = findViewById(R.id.AddDonor);
        Go_to_Put_Activity = findViewById(R.id.UpdateDonor);
        Go_to_Delete_Activity = findViewById(R.id.DeleteDonor);
        imageView = findViewById(R.id.mainScreenImage);
        logout = findViewById(R.id.logout);
        // Picasso.with(this).load("http://beyondthebinary.co.uk/wp-content/uploads/2017/05/blood.jpg")
        //       .resize(1500, 500).into(imageView);

        Go_to_Get_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GetDonors.class);
                startActivity(intent);
            }
        });
        Go_to_Post_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
        Go_to_Put_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PutActivity.class);
                startActivity(intent);
            }
        });
        Go_to_Delete_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = LoginActivity1.sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this, LoginActivity1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

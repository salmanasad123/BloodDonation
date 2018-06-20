package com.example.salman.blooddonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity1 extends AppCompatActivity {

    Button login;
    EditText uName;
    EditText Pass;
    static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        login = findViewById(R.id.login);
        uName = findViewById(R.id.username);
        Pass = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String name = sharedPreferences.getString("username","x");
        String password = sharedPreferences.getString("password","x");
        if (name!="x"&&password!="x")
        {
            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
            startActivity(intent);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uName.getText().toString().equals(Pass.getText().toString())) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", uName.getText().toString());
                    editor.putString("password", Pass.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}

package com.example.registrationanddisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity2Display extends AppCompatActivity {
    TextView userName,dateOfBirth,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_display);

        userName = findViewById(R.id.uname);
        dateOfBirth = findViewById(R.id.uDateOfBirth);
        email = findViewById(R.id.uEmail);

        // fetch data using intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String dob = intent.getStringExtra("dob");
        String Email = intent.getStringExtra("email");

        // Display Information data
        userName.setText(username);
        dateOfBirth.setText(dob);
        email.setText(Email);


    }
}
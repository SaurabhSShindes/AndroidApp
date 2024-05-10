package com.example.registrationanddisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1Registration extends AppCompatActivity {
    EditText edUsername , edEmail  , edDOB;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_registration);

        edUsername = findViewById(R.id.register_username);
        edDOB = findViewById(R.id.dob);
        edEmail = findViewById(R.id.register_email);
        btn = findViewById(R.id.register_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we convert all data to string format
                String displayUsername = edUsername.getText().toString();
                String displayDOB = edDOB.getText().toString();
                String displayEmail = edEmail.getText().toString();

                DataBase db = new DataBase(getApplicationContext(),"userRegistration",null,1);
                if(displayUsername.length()==0 || displayDOB.length()==0 || displayEmail.length()==0){
                    // when all field not fills
                    Toast.makeText(getApplicationContext(),"please fill all details",Toast.LENGTH_SHORT).show();
                } else if (displayUsername.length()<8) {
                    edUsername.setError("username must be greater than 8");
                } else if (db.checkUsername(displayUsername)!=0) {
                    edUsername.setError("username has already been taken");
                } else if (displayDOB.length()<7) {
                    edDOB.setError("enter correct date of birth");
                } else if (!displayEmail.contains("@") || !displayEmail.contains(".com")) {
                    edEmail.setError("Invalid email id");
                } else if (db.checkEmail(displayEmail)!=0) {
                    edEmail.setError("email already has been taken");
                } else if ((db.checkUsername(displayUsername)!=1) && (db.checkEmail(displayEmail)!=1)) {
                // all field correct then store database
                db.register(displayUsername,displayDOB,displayEmail);
                // if all information correct then message show
                Toast.makeText(Activity1Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                //send data using intent
                setDataToDisplayActivity(displayUsername, displayDOB,  displayEmail);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Fill Above Correct Information",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setDataToDisplayActivity(String displayUsername, String displayDOB, String displayEmail) {
        Intent intent = new Intent(this, Activity2Display.class);
        intent.putExtra("username", displayUsername);
        intent.putExtra("dob", displayDOB);
        intent.putExtra("email", displayEmail);
        startActivity(intent);
    }
}
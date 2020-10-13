package com.example.invenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhone, regPassword;
    Button signUp;
    Button callLogIn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        //Assigning values
        regName = findViewById(R.id.signup_name);
        regUsername = findViewById(R.id.signup_username);
        regEmail = findViewById(R.id.signup_email);
        regPhone = findViewById(R.id.signup_phone);
        regPassword = findViewById(R.id.signup_password);

        signUp = findViewById(R.id.signupin);
        callLogIn = findViewById(R.id.backlogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if(!validateName() | !validateUsername() | !validateEmail() | !validatePhone() | !validatePassword()){
                    return;
                }


                //getting values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                //sending data to firebase
                Userhelperclass userhelperclass = new Userhelperclass(name, username, email, phone, password);
                reference.child(username).setValue(userhelperclass);
                Toast.makeText(Signup.this,"Registration Successful",Toast.LENGTH_SHORT).show();


                //if(name.isEmpty() && username.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty()) {

                //  Toast.makeText(Signup.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                //}else{
                //  Toast.makeText(Signup.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                //}


            }
        });

        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }

    //validation code
    private boolean validateName() {
        //Validation of signup form
        String val = regName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            regName.setError("Field can not be empty ");
            return false;
        } else {

            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    //validating each field
    private boolean validateUsername() {
        //Validation of signup form
        String val = regUsername.getEditText().getText().toString();


        if (val.isEmpty()) {
            regUsername.setError("Field can not be empty ");
            return false;
        }
        else if(val.length()>20)
        {
            regUsername.setError("Username is too large");
            return false;
        }


        else {

            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        //Validation of signup form
        String val = regEmail.getEditText().getText().toString().trim();
        String checkemail="[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";


        if (val.isEmpty()) {
            regEmail.setError("Field can not be empty ");
            return false;
        }

        else if(!val.matches(checkemail))
        {
            regEmail.setError("Invalid email");
            return false;
        }

        else {

            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        //Validation of signup form
        String val = regPhone.getEditText().getText().toString().trim();




        if (val.isEmpty()) {
            regPhone.setError("Field can not be empty ");
            return false;
        }

        else {

            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        //Validation of signup form
        String val = regPassword.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regPassword.setError("Field can not be empty ");
            return false;
        }



        else {

            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


}
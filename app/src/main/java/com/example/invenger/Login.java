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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignUp;
    Button callLogIn;

    TextInputLayout loginUsername, loginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        loginUsername=findViewById(R.id.user_name);
        loginPassword=findViewById(R.id.password);


        callSignUp=findViewById(R.id.reg);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });


        callLogIn=findViewById(R.id.login);
        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateloginusername() | !validateloginpassword())
                {
                    return;
                }
                else {
                    isUser();


                    //Intent intent=new Intent(Login.this,UserProfile.class);
                    //startActivity(intent);

                }

            }
        });

    }

    private void isUser() {

        final String userEnteredusername=loginUsername.getEditText().getText().toString().trim();
        final String userEnteredpassword=loginPassword.getEditText().getText().toString().trim();

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");

        Query checkUser=reference.orderByChild("username").equalTo(userEnteredusername);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                loginUsername.setError(null);
                loginUsername.setErrorEnabled(false);


                String passwordFromDB=dataSnapshot.child(userEnteredusername).child("password").getValue(String.class);

                if(dataSnapshot.exists())
                {
                    if(passwordFromDB.equals(userEnteredpassword)){

                        loginPassword.setError(null);
                        loginPassword.setErrorEnabled(false);

                        String nameFromDB=dataSnapshot.child(userEnteredusername).child("name").getValue(String.class);
                        String usernameFromDB=dataSnapshot.child(userEnteredusername).child("username").getValue(String.class);
                        String emailFromDB=dataSnapshot.child(userEnteredusername).child("email").getValue(String.class);
                        String phoneFromDB=dataSnapshot.child(userEnteredusername).child("phone").getValue(String.class);
                        String password1FromDB=dataSnapshot.child(userEnteredusername).child("password").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phone",phoneFromDB);
                        intent.putExtra("password",password1FromDB);

                        startActivity(intent);

                    }
                    else
                    {
                        loginPassword.setError("Wrong Password");
                        loginPassword.requestFocus();

                    }
                }
                else
                {
                    loginUsername.setError("No such user exists");
                    loginUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Login.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }


    private boolean validateloginusername() {
        //Validation of sign up form
        String val = loginUsername.getEditText().getText().toString();


        if (val.isEmpty()) {
            loginUsername.setError("Field can not be empty ");
            return false;
        }

        else {

            loginUsername.setError(null);
            loginUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateloginpassword() {
        //Validation of signup form
        String val = loginPassword.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            loginPassword.setError("Field can not be empty ");
            return false;
        }

        else {

            loginPassword.setError(null);
            loginPassword.setErrorEnabled(false);
            return true;
        }
    }


}
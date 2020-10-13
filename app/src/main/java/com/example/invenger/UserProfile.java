package com.example.invenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputLayout register_username,register_email,register_phone,register_password;

    String user_name,user_username,user_email,user_phone,user_password;

    DatabaseReference reference;

    ImageView backtohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        backtohome=findViewById(R.id.backhome);

        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,HomePage.class);
                startActivity(intent);
            }
        });

        reference= FirebaseDatabase.getInstance().getReference("users");

        register_username=findViewById(R.id.reg_username);
        register_email=findViewById(R.id.reg_email);
        register_phone=findViewById(R.id.reg_phone);
        register_password=findViewById(R.id.reg_password);

        showAllData();


    }

    private void showAllData() {

        Intent intent=getIntent();
        user_name=intent.getStringExtra("name");
        user_username=intent.getStringExtra("username");
        user_email=intent.getStringExtra("email");
        user_phone=intent.getStringExtra("phone");
        user_password=intent.getStringExtra("password");

        register_username.getEditText().setText(user_name);
        register_email.getEditText().setText(user_email);
        register_phone.getEditText().setText(user_phone);
        register_password.getEditText().setText(user_password);
    }


    //Checking each field for new update

    public void update(View view){

        if(isNameChanged() || isPasswordChanged() || isPhoneChanged() || isEmailChanged() ){
            Toast.makeText(UserProfile.this,"Data has been updated",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(UserProfile.this,"Data is same can not be changed",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isEmailChanged() {

        if(!user_email.equals(register_email.getEditText().getText().toString())){

            reference.child(user_username).child("email").setValue(register_email.getEditText().getText().toString());
            user_email=register_email.getEditText().getText().toString();
            return true;

        }
        else{
            return false;
        }

    }


    private boolean isPhoneChanged() {

        if(!user_phone.equals(register_phone.getEditText().getText().toString())){

            reference.child(user_username).child("phone").setValue(register_phone.getEditText().getText().toString());
            user_phone=register_phone.getEditText().getText().toString();
            return true;

        }
        else{
            return false;
        }

    }

    private boolean isNameChanged() {

        if(!user_name.equals(register_username.getEditText().getText().toString())){

            reference.child(user_username).child("name").setValue(register_username.getEditText().getText().toString());
            user_name=register_username.getEditText().getText().toString();
            return true;

        }
        else{
            return false;
        }

    }

    private boolean isPasswordChanged() {
        if(!user_password.equals(register_password.getEditText().getText().toString())){

            reference.child(user_username).child("password").setValue(register_password.getEditText().getText().toString());
            user_password=register_password.getEditText().getText().toString();
            return true;

        }
        else{
            return false;
        }

    }

}
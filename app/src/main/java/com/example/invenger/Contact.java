package com.example.invenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact extends AppCompatActivity {

    TextInputLayout con_name,con_email,con_feedback;
    Button feedback_submit;

    FirebaseDatabase rootnode2;
    DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact);

        //Retrieving id of each field

        con_name=findViewById(R.id.contact_name);
        con_email=findViewById(R.id.contact_email);
        con_feedback=findViewById(R.id.contact_feedback);
        feedback_submit=findViewById(R.id.contact_submit);


        feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootnode2=FirebaseDatabase.getInstance();
                reference2=rootnode2.getReference("feedback");

                if(!validateName() | !validateEmail() | !validateFeedback()){
                    return;
                }

                //Get all the values
                String c_name=con_name.getEditText().getText().toString();
                String c_email=con_email.getEditText().getText().toString();
                String c_feedback=con_feedback.getEditText().getText().toString();

                Userhelperclass2 userhelperclass2= new Userhelperclass2(c_name,c_email,c_feedback);
                reference2.child(c_name).setValue(userhelperclass2);
                Toast.makeText(Contact.this,"Thank you",Toast.LENGTH_SHORT).show();

            }
        });
    }

    //validating fields

    private boolean validateName(){
        String val=con_name.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            con_name.setError("Field can not be empty");
            return false;
        }else{
            con_name.setError(null);
            con_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String val=con_email.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            con_email.setError("Field can not be empty");
            return false;
        }else{
            con_email.setError(null);
            con_email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFeedback(){
        String val=con_feedback.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            con_feedback.setError("Field can not be empty");
            return false;
        }else{
            con_feedback.setError(null);
            con_feedback.setErrorEnabled(false);
            return true;
        }
    }
}
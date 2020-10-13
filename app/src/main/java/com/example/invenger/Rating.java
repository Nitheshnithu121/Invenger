package com.example.invenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rating extends AppCompatActivity {

    Button button;
    RatingBar ratingStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rating);

        button=findViewById(R.id.ratebar_submit);
        ratingStar=findViewById(R.id.ratingBar);

        ratingStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int rate=(int)rating;
                String message=null;

                switch (rate){
                    case 1:
                        message="I hate it";
                        break;
                    case 2:
                        message="Good";
                        break;
                    case 3:
                        message="Better";
                        break;
                    case 4:
                        message="Best";
                        break;
                    case 5:
                        message="I love it";
                        break;
                }
                Toast.makeText(Rating.this,message,Toast.LENGTH_SHORT).show();


            }
        });


    }
}
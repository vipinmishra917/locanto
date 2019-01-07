package com.example.roshan.tripperstrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.content.Intent;

public class Information extends AppCompatActivity {

  public   TextView inform;

  public   ImageView imageView;

  public   TextView bestTime;
  public  TextView  reach;
  public TextView  ticket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


       inform = findViewById(R.id.city_detail);

        imageView = findViewById(R.id.city_image);


        bestTime =findViewById(R.id.best_time);
        ticket = findViewById(R.id.fare);
        reach =findViewById(R.id.how_reach);

    Intent intent = getIntent();


        String imageUrl = intent.getExtras().getString("imageurl");
        String information= intent.getExtras().getString("information");

        String best_time = intent.getExtras().getString("bestTime");

        String how_reach = intent.getExtras().getString("reach");
        String ticket_toreach = intent.getExtras().getString("ticket");


        Glide.with(this).load(imageUrl).into(imageView);

        inform.setText("About :" +information);
        bestTime.setText("Best Time :" +best_time);
        reach.setText("How To Reach : " +how_reach);

        ticket.setText("Ticket :" +ticket_toreach);






    }
}

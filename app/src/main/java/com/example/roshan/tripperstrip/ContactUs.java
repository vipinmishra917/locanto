package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.Manifest;
public class ContactUs extends AppCompatActivity {
    private static final int REQUEST_CALL =1;
    Button call,mail,feed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        init();
        mail = findViewById(R.id.mailbtn);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this,MailActivity.class);
                startActivity(intent);
            }
        });
        feed = findViewById(R.id.btnfeed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSda5RGWyLaTdhgXZez0_AbQEt8gxSUQ4kPpOSKEEMxY35a4hw/viewform"));
                startActivity(intent);


            }
        });


    }
    public void init(){

        call= (Button) findViewById(R.id.callbtn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:8586068383"));
              if(ActivityCompat.checkSelfPermission(ContactUs.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                  ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
              } else {
                  startActivity(intent);
              }
              }
              });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menulist, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Toast.makeText(getApplicationContext(), "Social App Integrations", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ContactUs.this, Home.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TrendingPlaces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_places);

    }
    public void imageone(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,TamilActivity.class);
        startActivity(in);
    }
    public void imagetwo(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,MumbaiActivity.class);
        startActivity(in);
    }
    public void imagethree(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,UttrakhandActivity.class);
        startActivity(in);
    }
    public void imagefour(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,NewDelhiActivity.class);
        startActivity(in);
    }
    public void imagefive(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,KeralaAttraction.KolkataActivity.class);
        startActivity(in);
    }
    public void imagesix(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,RajasthanActivity.class);
        startActivity(in);
    }
    public void imageseven(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,KeralaActivity.class);
        startActivity(in);
    }
    public void imageeight(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,BiharActivity.class);
        startActivity(in);
    }
    public void imagenine(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,KarnatakaActivity.class);
        startActivity(in);
    }
    public void imageten(View view)
    {
        Intent in = new Intent(TrendingPlaces.this,GoaActivity.class);
        startActivity(in);
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
            Intent intent = new Intent(TrendingPlaces.this, Home.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


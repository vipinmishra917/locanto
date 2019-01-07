package com.example.roshan.tripperstrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MizoramActivity extends AppCompatActivity{
    private TextToSpeech tts;
    private Button btn;
    private TextView editText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mizoram);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar Title");
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle("Mizoram");
        btn = findViewById(R.id.btnspeak);
        editText = findViewById(R.id.myword);

        tts = new TextToSpeech(MizoramActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void Miz(View view)
    {
        Intent in = new Intent(MizoramActivity.this,MizoramAttraction.class);
        startActivity(in);
    }
    public void mizcat(View view)
    {
        Intent in = new Intent(MizoramActivity.this,CategoryActivity.class);
        startActivity(in);
    }
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

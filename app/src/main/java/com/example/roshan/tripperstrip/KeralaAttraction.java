package com.example.roshan.tripperstrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KeralaAttraction extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener{


    private RecyclerView city_recycle;

    private List<City> city_list;

    private RecyclerAdapter cityRecyclerAdapter;

    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerala_attraction);


        firebaseFirestore = FirebaseFirestore.getInstance();

        city_recycle = findViewById(R.id.recyclerView);
        city_list = new ArrayList<>();

        cityRecyclerAdapter = new RecyclerAdapter(city_list);

        city_recycle.setLayoutManager(new LinearLayoutManager(this));

        city_recycle.setAdapter(cityRecyclerAdapter);

        cityRecyclerAdapter.setOnItemClicklistener(KeralaAttraction.this);


        firebaseFirestore.collection("Kerala").addSnapshotListener(this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for (DocumentChange doc :documentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){

                        City city = doc.getDocument().toObject(City.class);

                        city_list.add(city);

                        cityRecyclerAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

    }

    @Override
    public void onItemClick(int position) {


        Intent information = new Intent(KeralaAttraction.this,Information.class);

        City clickItem = city_list.get(position);

        information.putExtra("imageurl",clickItem.getImage_url());
        information.putExtra( "information",clickItem.getInform());

        information.putExtra("bestTime",clickItem.getBestTime());

        information.putExtra("reach",clickItem.getReach());
        information.putExtra("ticket",clickItem.getTicket());

        startActivity(information);




    }

    public static class KolkataActivity extends AppCompatActivity{
        private TextToSpeech tts;
        private Button btn;
        private TextView editText;

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kolkata);
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Toolbar Title");
            CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
            collapsingToolbar.setTitle("Kolkata");
            btn = findViewById(R.id.btnspeak);
            editText = findViewById(R.id.myword);

            tts = new TextToSpeech(KolkataActivity.this, new TextToSpeech.OnInitListener() {
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
        public void KOL(View  view)

        {
            Intent in = new Intent(KolkataActivity.this, WestBengalAttraction.class);
            startActivity(in)  ;
        }
        public void kalcat(View view)
        {
            Intent innn = new Intent(KolkataActivity.this,CategoryActivity.class);
            startActivity(innn);
        }

        public void onDestroy() {
            if (tts != null) {
                tts.stop();
                tts.shutdown();
            }
            super.onDestroy();
        }
    }
}

package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ManipurAttraction extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener{
    private RecyclerView city_recycle;

    private List<City> city_list;

    private RecyclerAdapter cityRecyclerAdapter;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipur_attraction);

        firebaseFirestore = FirebaseFirestore.getInstance();

        city_recycle = findViewById(R.id.recyclerView);
        city_list = new ArrayList<>();

        cityRecyclerAdapter = new RecyclerAdapter(city_list);

        city_recycle.setLayoutManager(new LinearLayoutManager(this));

        city_recycle.setAdapter(cityRecyclerAdapter);

        cityRecyclerAdapter.setOnItemClicklistener(ManipurAttraction.this);


        firebaseFirestore.collection("Manipure").addSnapshotListener(new EventListener<QuerySnapshot>() {
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

        Intent information = new Intent(ManipurAttraction.this,Information.class);

        City clickItem = city_list.get(position);

        information.putExtra("imageurl",clickItem.getImage_url());
        information.putExtra( "information",clickItem.getInform());

        information.putExtra("bestTime",clickItem.getBestTime());

        information.putExtra("reach",clickItem.getReach());
        information.putExtra("ticket",clickItem.getTicket());

        startActivity(information);



    }
}

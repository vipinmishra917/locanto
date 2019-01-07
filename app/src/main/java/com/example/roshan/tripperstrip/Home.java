package com.example.roshan.tripperstrip;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Boolean doubleBackToExitPressedOnce = false;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    public static final int REQUEST_INVITE = 0;
    private static final String TAG = ShareActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mONavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent it = new Intent(Home.this, Home.class);
                    startActivity(it);

                    return true;

                case R.id.navigation_about:
                    Intent intenttt = new Intent(Home.this, AboutUs.class);
                    startActivity(intenttt);
                    return true;
                case R.id.navigation_contact:
                    Intent intent = new Intent(Home.this, ContactUs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_share:

                    Intent intentt = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                            .setMessage(getString(R.string.invitation_message))
                            .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                            .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                            .setCallToActionText(getString(R.string.invitation_cta)).build();

                    startActivityForResult(Intent.createChooser(intentt, "Choose an Email Client from..."), REQUEST_INVITE);
                    return true;
                case R.id.navigation_trending:
                    Intent intt = new Intent(Home.this, TrendingPlaces.class);
                    startActivity(intt);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mONavigationItemSelectedListener);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();
        boolean autoLaunchDeepLink = true;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink).setResultCallback(new ResultCallback<AppInviteInvitationResult>() {
            @Override
            public void onResult(@NonNull AppInviteInvitationResult appInviteInvitationResult) {
                Log.d(TAG, "getInvitation:onResult:" + appInviteInvitationResult.getStatus());


            }
        });
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(Home.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed" + connectionResult);
        showMessage("Google Play Service Error");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ",requestCode=" + requestCode);
        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {

                String[] ids = AppInviteInvitation.getInvitationIds(requestCode, data);
//                Log.d(TAG, getString(R.string.sent_invitation_fmt, ids.length));
            } else {
                showMessage(getString(R.string.send_failed));
            }
        }
    }

    private void showMessage(String msg) {

        ViewGroup container = findViewById(R.id.snackbar_layout);
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {

    if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;

        }
        this.doubleBackToExitPressedOnce = true;
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tripper's Trip");
        alert.setMessage("Hey Tripper's Do You want to Exit ?");
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Really, Yes !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"We are thank you for your precious time,See you Soon",Toast.LENGTH_SHORT).show();
                finish();
            }
        }); alert.create().show();

    }


    public void andhra(View view) {

        Intent andra = new Intent(Home.this, AndhraPardeshActivity.class);
        startActivity(andra);
    }
    public void arunachal(View view) {

        Intent intent = new Intent(Home.this, ArunachalActivity.class);
        startActivity(intent);

    }

    public void assam(View view) {

        Intent intent = new Intent(Home.this, AssamActivity.class);
        startActivity(intent);

    }

    public void bihar(View view) {

        Intent intent = new Intent(Home.this, BiharActivity.class);
        startActivity(intent);

    }

    public void chhattisgarh(View view) {

        Intent intent = new Intent(Home.this, ChattisgarhActivity.class);
        startActivity(intent);

    }

    public void delhiAct(View view) {

        Intent intent = new Intent(Home.this, NewDelhiActivity.class);
        startActivity(intent);

    }

    public void go(View view) {

        Intent intent = new Intent(Home.this, GoaActivity.class);
        startActivity(intent);

    }

    public void gujara(View view) {

        Intent intent = new Intent(Home.this, GujaratActivity.class);
        startActivity(intent);

    }

    public void haryanaActivity(View view) {

        Intent intent = new Intent(Home.this, HaryanaActivity.class);
        startActivity(intent);

    }

    public void himanchal(View view) {

        Intent intent = new Intent(Home.this,HimachalActivity.class);
        startActivity(intent);

    }

    public void jammuKashmir(View view) {

        Intent intent = new Intent(Home.this,jammuActivity.class);
        startActivity(intent);

    }
    public void jharkhandActivity(View view) {

        Intent intent = new Intent(Home.this, JharkhandActivity.class);
        startActivity(intent);

    }

    public void keralaActivity(View view) {

        Intent intent = new Intent(Home.this, KeralaActivity.class);
        startActivity(intent);

    }

    public void kernataka(View view) {

        Intent intent = new Intent(Home.this, KarnatakaActivity.class);
        startActivity(intent);

    }

    public void kolkataActivity(View view) {

        Intent intent = new Intent(Home.this, KeralaAttraction.KolkataActivity.class);
        startActivity(intent);

    }

    public void mumbaiActivity(View view) {

        Intent intent = new Intent(Home.this, MumbaiActivity.class);
        startActivity(intent);

    }
    public void manipurActiviry(View view) {

        Intent intent = new Intent(Home.this, ManipurActivity.class);
        startActivity(intent);

    }

    public void meghalayaActivity(View view) {

        Intent intent = new Intent(Home.this, MeghalayaActivity.class);
        startActivity(intent);

    }

    public void mizoramActivity(View view) {

        Intent intent = new Intent(Home.this, MizoramActivity.class);
        startActivity(intent);

    }
    public void mpActivity(View view) {

        Intent intent = new Intent(Home.this,MpActivity.class);
        startActivity(intent);

    }
    public void nagalandActivity(View view) {

        Intent intent = new Intent(Home.this,NagalandActivity.class);
        startActivity(intent);

    }

    public void odhisaActivity(View view) {
        Intent intent = new Intent(Home.this, OdhisaActivity.class);
        startActivity(intent);

    }

    public void punjabActivity(View view) {

        Intent intent = new Intent(Home.this, PunjabActivity.class);
        startActivity(intent);

    }

    public void rajisthan(View view) {

        Intent intent = new Intent(Home.this, RajasthanActivity.class);
        startActivity(intent);

    }

    public void skkimActivity(View view) {

        Intent intent = new Intent(Home.this, SikkimActivity.class);
        startActivity(intent);
    }

    public void tamilActivity(View view) {

        Intent intent = new Intent(Home.this, TamilActivity.class);
        startActivity(intent);

    }

    public void trip(View view){

        Intent intent = new Intent(Home.this, TripuraActivity.class);
        startActivity(intent);
    }
    public void upAct(View view) {
        Intent intent = new Intent(Home.this, UpActivity.class);
        startActivity(intent);

    }

    public void telan(View view) {

        Intent intent = new Intent(Home.this, TelanganaActivity.class);
        startActivity(intent);
    }

    public void uttrakhand(View view) {

        Intent intent = new Intent(Home.this, UttrakhandActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.logout){
            signOut();
        }

        if(id==R.id.account)
        {
            Intent intent = new Intent(Home.this,MainActivity.class);
            startActivity(intent);
        }

        if(id==R.id.share) {
            Intent intentt = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                    .setMessage(getString(R.string.invitation_message))
                    .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                    .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                    .setCallToActionText(getString(R.string.invitation_cta)).build();

            startActivityForResult(Intent.createChooser(intentt, "Choose an Email Client from..."), REQUEST_INVITE);
        }


        return super.onOptionsItemSelected(item);
    }
    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
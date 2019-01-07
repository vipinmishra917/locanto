package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

public class ShareActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    public static final int REQUEST_INVITE = 0;
    private static final String TAG = ShareActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findViewById(R.id.btnshare).setOnClickListener(this);
        //findViewById(R.id.btnshare).setOnClickListener(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this,this)
                .build();
        boolean autoLaunchDeepLink= true;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient,this,autoLaunchDeepLink).setResultCallback(new ResultCallback<AppInviteInvitationResult>() {
            @Override
            public void onResult(@NonNull AppInviteInvitationResult appInviteInvitationResult) {
                Log.d(TAG, "getInvitation:onResult:" + appInviteInvitationResult.getStatus());

            }
        });
    }

    @Override
    public void onClick(View v) {
        onInviteClicked();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        showMessage("Google Play Service Error");
    }
    private void onInviteClicked(){
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta)).build();
        startActivityForResult(intent, REQUEST_INVITE);

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
    public boolean onCreateOptionsMenu(Menu menu) {
      //  return super.onCreateOptionsMenu(menu);
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
            Intent intent = new Intent(ShareActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

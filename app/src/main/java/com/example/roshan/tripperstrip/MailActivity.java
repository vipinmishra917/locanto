package com.example.roshan.tripperstrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MailActivity extends AppCompatActivity {

    Button b1;
    EditText e1, e2, e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        setContentView(R.layout.activity_mail);
        e1 = (EditText) findViewById(R.id.edtone);
        e2 = (EditText) findViewById(R.id.edttwo);
        e3 = (EditText) findViewById(R.id.edtthree);
        b1 = (Button) findViewById(R.id.btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
                e1.setText("");
                e2.setText("");
                e3.setText("");
            }
        });
    }
    protected void sendEmail(){

        String[] str = {e1.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, str);
        email.putExtra(Intent.EXTRA_SUBJECT, e2.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, e3.getText().toString());
        try {
            email.setType("text/plain");
            startActivity(email);

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MailActivity.this, "No email client installed", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  return super.onCreateOptionsMenu(menu);

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
            Intent intent = new Intent(MailActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

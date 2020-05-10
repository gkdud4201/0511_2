package com.example.cafemoa;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;

public class InformationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        String title = "";
        String address = "";

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            title = "error";
        }
        else {

            title = extras.getString("title");
            address = extras.getString("address");
        }

        TextView informText = (TextView) findViewById(R.id.informText);

        String str = title + '\n' + address + '\n';
        informText.setText(str);

        ImageView locationImage =(ImageView)findViewById(R.id.loginImage);


    }
}

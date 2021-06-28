package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class findcenter extends AppCompatActivity {
    ImageButton geolahore,geokarachi;
    ImageButton geobalochistan,geokpk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findcenter);
        init();
    }
    public void init()
    {
        geolahore=findViewById(R.id.geolahore3);
        geokarachi=findViewById(R.id.geokarachi4);

    }
    public void setGeolahore(View v) // Go from Main Screen to Covid Info Window
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Punjab,Pakistan");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    public void setGeokarachi(View v) // Go from Main Screen to Covid Info Window
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Sindh,Pakistan");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setGeobalochistan(View v) // Go from Main Screen to Covid Info Window
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Balochistan,Pakistan");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setGeokpk(View v) // Go from Main Screen to Covid Info Window
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Khyber Pakhthawn ,Pakistan");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnUserRegistration, btnInfo, btnFeedback, btnCenter,btnHelpline,
    btnProgress, btnTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init()
    {
        btnUserRegistration = findViewById(R.id.btnUserRegistration);
        btnInfo = findViewById(R.id.btnInfo);
        btnFeedback = findViewById(R.id.btnFeedBack);
        btnCenter = findViewById(R.id.btnCenter);
        btnHelpline = findViewById(R.id.btnHelpLine);
        btnProgress = findViewById(R.id.btnProgress);
        btnTracker = findViewById(R.id.btnTracker);
    }

    public void userRegistration(View v) // Go from Main Screen to Registration Window
    {
        Intent intent = new Intent(MainActivity.this,registration.class);
        startActivity(intent);
    }

    public void helpline(View v) // Go from Main Screen to Registration Window
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "03110230202"));
        startActivity(intent);
    }

    public void feedbackOption(View v) // Go from Main Screen to Feedback Window
    {
        Intent intent = new Intent(MainActivity.this, feedback.class);
        startActivity(intent);
    }

    public void covidInfo(View v) // Go from Main Screen to Covid Info Window
    {
        Intent intent = new Intent(MainActivity.this, covidinfo.class);
        startActivity(intent);
    }

    public void findCenter(View v) // Go from Main Screen to Find Center Window
    {
        Intent intent = new Intent(MainActivity.this, findcenter.class);
        startActivity(intent);
    }

    public void Tracker(View v) // Go from Main Screen to tracker
    {
        Intent intent = new Intent(MainActivity.this, tracker.class);
        startActivity(intent);
    }

    public void userProgress(View v) // Go from Main Screen to see progress
    {
        Intent intent = new Intent(MainActivity.this, progress.class);
        startActivity(intent);
    }

}
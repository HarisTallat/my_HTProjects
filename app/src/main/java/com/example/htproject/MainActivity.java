package com.example.htproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    Button btnUserRegistration, btnInfo, btnFeedback, btnCenter,btnHelpline,btnliveData,btnAuthorized,
    btnProgress, btnTracker;
    private InterstitialAd mInterstitialAd;// for adds
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                my_add();
            }
        });
    }
    public void my_add()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        load_ads(adRequest);
    }
    public void load_ads(AdRequest adRequest)
    {
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d("adMob", "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Intent intent3= new Intent(MainActivity.this, registration.class);
                        startActivity(intent3);
                        Toast.makeText(MainActivity.this, "The ad was dismissed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("adMob", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
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
        btnliveData = findViewById(R.id.btnliveData);
        btnAuthorized = findViewById(R.id.btnAuthorized);
    }

    public void userRegistration(View v) // Go from Main Screen to Registration Window
    {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            Toast.makeText(this, "The interstitial ad wasn't ready yet.", Toast.LENGTH_SHORT).show();

            Intent intent1= new Intent(MainActivity.this, registration.class);
            startActivity(intent1);

        }

    }

    public void helpline(View v) // Go from Main Screen to Registration Window
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1166"));
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
        Intent intent = new Intent(MainActivity.this, progress.class);
        startActivity(intent);
    }

    public void userProgress(View v) // Go from Main Screen to see progress
    {
        Intent intent = new Intent(MainActivity.this, registration_checker.class);
        startActivity(intent);
    }

    public void liveData(View v ) // Go From Main Screen to Live Data
    {
        String url = "https://covid.gov.pk/stats/pakistan";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
    public void authorization(View v)
    {
        Intent intent = new Intent(MainActivity.this, for_admin.class);
        startActivity(intent);

    }
}
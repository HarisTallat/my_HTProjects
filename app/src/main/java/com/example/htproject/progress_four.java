package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class progress_four extends AppCompatActivity {
    TextView tvNameProgress4, tvBPProgress4, tvAgeProgress4, tvDiabetesProgressFour,tvReccommendation;
    String name, bp, age,diabetesInfo,covidInfo;
    int sBP, dBP;
    ImageView ivRecommendation;
    Uri imageUri;
    ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_four);
        init();
         name=  getIntent().getExtras().getString("keyName");
         bp = getIntent().getExtras().getString("keyBP");
         age=  getIntent().getExtras().getString("keyAge");
         diabetesInfo= getIntent().getExtras().getString("keyDiabetes");
         covidInfo = getIntent().getExtras().getString("keyCovid");
         sBP = getIntent().getExtras().getInt("keySBP");
         dBP = getIntent().getExtras().getInt("keyDBP");
          imageUri = Uri.parse(getIntent().getStringExtra("image-uri"));
          ivProfile.setImageURI(imageUri);
          tvNameProgress4.setText(name);
          tvBPProgress4.setText(bp);
          tvAgeProgress4.setText(age);
          tvDiabetesProgressFour.setText(diabetesInfo);

        int agePatient = Integer.parseInt(age);
        if(agePatient > 45)
        {
            tvReccommendation.setText("The Pfizer-BioNTech vaccine is efficient upto 95%. As the age of the patient"
            + "is more than 45, the patinet will vaccinated by Pfizer-BionTech");
            ivRecommendation.setImageResource(R.drawable.pfizer);
        }
        else if(agePatient >= 12 && agePatient <=18)
        {
            tvReccommendation.setText("The Pfizer-BioNTech vaccine is efficient upto 95%. As the patient"
                    + "is a teenager, the patient will be vaccinated by Pfizer-BionTech");
            ivRecommendation.setImageResource(R.drawable.pfizer);
        }
        else if (agePatient>=18 && agePatient <=45)
        {
            tvReccommendation.setText("The sinofarm vaccine is efficient upto 79. As the herd imminuity"
                    + "of people between 18 and 45 is strong, the patient will be vaccinated by sinofarm as Pfizer-BionTech "
            + "is reserved for senior citizen and teenagers");
            ivRecommendation.setImageResource(R.drawable.sinofarm);
        }
        else
        {
            tvReccommendation.setText("The sinofarm vaccine is efficient upto 79. As the herd imminuity"
                    + "of people between 18 and 45 is strong, the patient will be vaccinated by sinofarm as Pfizer-BionTech "
                    + "is reserved for senior citizen and teenagers");
            ivRecommendation.setImageResource(R.drawable.sinofarm);
        }
    }

    private void init()
    {
        tvNameProgress4 = findViewById(R.id.tvNameProgress4);
        tvBPProgress4 = findViewById(R.id.tvBPProgress4);
        tvAgeProgress4 = findViewById(R.id.tvAgeProgress4);
        tvDiabetesProgressFour = findViewById(R.id.tvDiabetesProgressFour);
        ivRecommendation = findViewById(R.id.ivRecommendation);
        tvReccommendation = findViewById(R.id.tvRecommendation);
        ivProfile = findViewById(R.id.ivProfile);
    }
}
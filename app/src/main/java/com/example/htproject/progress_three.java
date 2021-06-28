package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class progress_three extends AppCompatActivity {
    TextView tvNameProgress3,tvBP3, tvAge3, tvDiabetes3,tvCovid3;
    ImageView ivrec;
    String name, bp,age,diabetesInfo,covidInfo,cont;
    String[] bloodPressure;
    int sBP, dBP;
    Button btnGenerateSlip;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_three);
        init();
        settingText();
        HashMap<String,Object> map = new HashMap<>();
        map.put("age",tvAge3.getText().toString().trim());
     map.put("covid",covidInfo);
     map.put("diabetes",diabetesInfo);
     map.put("blood_Pressure",bp);
     map.put("contact",cont);
     map.put("first_Name",name);


        FirebaseDatabase.getInstance().getReference().child("Patient").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(progress_three.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        ;



    }
    public void init()
    {
        tvNameProgress3= findViewById(R.id.tvNameProgress3);
        tvBP3= findViewById(R.id.tvBP3);
        tvAge3= findViewById(R.id.tvAge3);
        tvDiabetes3= findViewById(R.id.tvDiabetes3);
        tvCovid3= findViewById(R.id.tvCovid3);
        btnGenerateSlip = findViewById(R.id.btnGenerateSlip);
        ivrec=findViewById(R.id.ivrec);
        bloodPressure = new String[50];
    }

    public void generateSlip (View v)
    {
        if(bloodPressure()) {

            Intent intent = new Intent(progress_three.this, progress_four.class);
            intent.putExtra("keyName", name);
            intent.putExtra("keyBP", bp);
            intent.putExtra("keyAge", age);
            intent.putExtra("keyDiabetes", diabetesInfo);
            intent.putExtra("keyCovid", covidInfo);
            intent.putExtra("keySBP", sBP);
            intent.putExtra("keyDBP", dBP);
            intent.putExtra("image-uri", imageUri.toString());
            startActivity(intent);
        }
        else
        {
            DialogBox();
        }


    }
    public void settingText()
    {
        cont=getIntent().getExtras().getString("keycon");
        name=  getIntent().getExtras().getString("keyProgressName");
        bp = getIntent().getExtras().getString("keyBP");
        age=  getIntent().getExtras().getString("keyAge");
        diabetesInfo= getIntent().getExtras().getString("keyDI");
        covidInfo = getIntent().getExtras().getString("keyCI");
        imageUri = Uri.parse(getIntent().getStringExtra("image-uri"));
         ivrec.setImageURI(imageUri);
        tvNameProgress3.setText("Name: " + name);
        tvBP3.setText("Blood Pressure: " + bp);
        tvAge3.setText("Age: " + age);
        tvDiabetes3.setText("Diabetes: " + diabetesInfo);
        tvCovid3.setText("Covid: " +covidInfo);


    }
    private boolean bloodPressure()
    {
        if(bp.contains("-")) {
            bloodPressure = bp.split("-");
            sBP = Integer.parseInt(bloodPressure[0]);
            dBP = Integer.parseInt(bloodPressure[1]);
            if ( (sBP > 150 || dBP > 100) || covidInfo.equals("Yes") ) {

                DialogBox();
                return false;

            }
            return  true;
        }
        else
        {
            return false;
        }


    }

    private void DialogBox()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(progress_three.this).create();
        alertDialog.setTitle("Sorry Major!");
        alertDialog.setMessage("You are ineligible for the vaccine right now because of high blood pressure or recent Covid Event. Consult your physician.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
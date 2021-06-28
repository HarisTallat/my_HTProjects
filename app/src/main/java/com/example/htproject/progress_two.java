package com.example.htproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class progress_two extends AppCompatActivity {

    EditText etProgressName, etBP, etAge, etcontact;
    RadioGroup rgDiabetes, rgCovid;
    Button btnSubmitDetails, btnAttach;
    ImageView ivCamera, ivProof, ivsubmit;
    private static final int PICK_IMAGE = 1000;
    private static final int PROOF = 2000;
    Uri imageUri, imageUri2;
    RadioButton radioButton3, radioButton5, radioButton6, radioButton7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_two);
        init();
        ivsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    public void openGallery() {
        Intent g = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(g, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            ivCamera.setImageURI(imageUri);
            ivsubmit.setVisibility(View.GONE);

        } else if (resultCode == RESULT_OK && requestCode == PROOF) {
            imageUri2 = data.getData();
            ivProof.setImageURI(imageUri2);
            btnAttach.setVisibility(View.GONE);

        }
    }
    private void init() {
        etProgressName = findViewById(R.id.etProgressName);
        etBP = findViewById(R.id.etBP);
        etcontact = findViewById(R.id.etcontact);
        ivCamera = findViewById(R.id.ivCamera);
        etAge = findViewById(R.id.etAge);
        rgDiabetes = findViewById(R.id.rgDiabetes);
        rgCovid = findViewById(R.id.rgCovid);
        btnSubmitDetails = findViewById(R.id.btnSubmitDetails);
        ivsubmit = findViewById(R.id.ivSubmit);
        btnAttach = findViewById(R.id.btnAttach);
        ivProof = findViewById(R.id.ivProof);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton6 = findViewById(R.id.radioButton6);
        radioButton7 = findViewById(R.id.radioButton7);
    }
    public void submitDetails(View v) {
        if(checkValidation()) {
            String diabetesInfo;
            String covidInfo;
            RadioButton d;
            RadioButton c;
            Intent intent = new Intent(progress_two.this, progress_three.class);
            intent.putExtra("keyProgressName", etProgressName.getText().toString().trim());
            intent.putExtra("keyBP", etBP.getText().toString().trim());
            intent.putExtra("keycon", etcontact.getText().toString().trim());
            intent.putExtra("keyAge", etAge.getText().toString().trim());
            intent.putExtra("image-uri", imageUri.toString());
            int diabetes = rgDiabetes.getCheckedRadioButtonId();
            int covid = rgCovid.getCheckedRadioButtonId();
            if (diabetes != -1) {
                d = findViewById(diabetes);
                diabetesInfo = d.getText().toString().trim();
            } else {
                diabetesInfo = "Not Provided";
            }

            if (covid != -1) {
                c = findViewById(covid);
                covidInfo = c.getText().toString().trim();
            } else {
                covidInfo = "Not Provided";
            }
            intent.putExtra("keyDI", diabetesInfo);
            intent.putExtra("keyCI", covidInfo);
            startActivity(intent);
        }
    }
    public void attach(View v) {
        Intent g = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(g, PROOF);
    }

    private boolean checkValidation() // Use this function if it's needed somewhere
    {
        boolean flag = true;
        if (etProgressName.getText().toString().trim().isEmpty()) {
            etProgressName.setError("Name can't be empty");
            flag = false;
        }
        if (etBP.getText().toString().isEmpty()) {
            etBP.setError("Blood Pressure can't be empty");
            flag = false;
        }
        if (etAge.getText().toString().isEmpty()) {
            etAge.setError("Age can't be empty");
            flag = false;
        }
        if (etcontact.getText().toString().isEmpty()) {
            etcontact.setError("Phone can't be empty");
            flag = false;
        }
        if (imageUri2==null) {
            Toast.makeText(this, "Please Upload your picture", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (imageUri==null) {
            Toast.makeText(this, "Please Upload your proof", Toast.LENGTH_SHORT).show();
            flag = false;

        }
        return flag;


    }
}
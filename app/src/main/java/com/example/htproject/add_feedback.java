package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_feedback extends AppCompatActivity {
    EditText etFBName, etFBPhone, etFB;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        init();
    }
    private void init()
    {
        etFBName = findViewById(R.id.etFBName);
        etFBPhone = findViewById(R.id.etFBPhone);
        etFB = findViewById(R.id.etFB);
        btnSave = findViewById(R.id.btnSave);
    }
    public void save(View v)
    {
        Intent intent = new Intent();
        String name, phone , feedback;
        name = etFBName.getText().toString().trim();
        phone = etFBPhone.getText().toString().trim();
        feedback = etFB.getText().toString().trim();
        intent.putExtra("keyName", name);
        intent.putExtra("keyPhone", phone);
        intent.putExtra("keyFeedback", feedback);
        setResult(RESULT_OK,intent);
        finish();
    }
}
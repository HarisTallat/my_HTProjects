package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {
    EditText etFirstName, etSecondName, etCNIC, etPhone, etVaccine, etDOB;
    Button btnRegister, btnSelectDate, btnSelectVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();

    }

    private void init()
    {
        etFirstName = findViewById(R.id.etFirstName);
        etSecondName = findViewById(R.id.etSecondName);
        etCNIC  = findViewById(R.id.etCNIC);
        etPhone = findViewById(R.id.etPhone);
        etVaccine = findViewById(R.id.etVaccine);
        etDOB = findViewById(R.id.etDOB);
        btnRegister = findViewById(R.id.btnRegister);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSelectVaccine = findViewById(R.id.btnSelectVaccine);
    }

    private boolean checkValidation() // Use this function if it's needed somewhere
    {
        boolean flag = true;
        if(etFirstName.getText().toString().trim().isEmpty())  {
            etFirstName.setError("Name can't be empty");
            flag = false;
        }
        if(etSecondName.getText().toString().isEmpty())  {
            etSecondName.setError("Name can't be empty");
            flag = false;
        }
        if(etCNIC.getText().toString().isEmpty())  {
            etCNIC.setError("The CNIC field can't be empty");
            flag = false;
        }
        if(etPhone.getText().toString().isEmpty())  {
            etPhone.setError("Phone can't be empty");
            flag = false;
        }
        if(etVaccine.getText().toString().isEmpty())  {
            etVaccine.setError("This field can't be empty");
            flag = false;
        }
        return flag;
        // I have not added Date of Birth because you I think you are going to use API of DOB on the Button

    }

    public void register(View v) // Register Button on Registration Screen
    {
        if(checkValidation())
        {
            Toast.makeText(this, "You have entered right data", Toast.LENGTH_SHORT).show();
        }
    }


}
package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class registration extends AppCompatActivity {
    EditText etFirstName, etSecondName, etCNIC, etPhone, etDOB,etcab;
    Button btnRegister, btnSelectDate, btnSelectVaccine;
    TextView etVaccine;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    public void btnSelectDate(View v) // Go from Main Screen to Registration Window
    {   initDatePicker();
        datePickerDialog.show();

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date= makeDateString(day,month,year);
                etDOB.setText(date);

            }

        };
        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int day= cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }
    private String makeDateString( int day, int month, int year)
    {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";
        return "JAN";
    }


    public void btnn_ShowDialoig(View view) {
        final AlertDialog.Builder alert= new AlertDialog.Builder(registration.this);
        View mView= getLayoutInflater().inflate(R.layout.dia_box,null);
        final EditText txt_input=(EditText)mView.findViewById(R.id.txtinput);

        Button btok=(Button)mView.findViewById(R.id.btok);
        alert.setView(mView);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etVaccine.setText(txt_input.getText().toString());
                alertDialog.dismiss();

            }
        });
        alertDialog.show();

    }




    private void init() {
        etFirstName = findViewById(R.id.etFirstName);
        etSecondName = findViewById(R.id.etSecondName);
        etCNIC = findViewById(R.id.etCNIC);
        etPhone = findViewById(R.id.etPhone);
        etVaccine = findViewById(R.id.etVaccine);
        etDOB = findViewById(R.id.etDOB);
        btnRegister = findViewById(R.id.btnRegister);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSelectVaccine = findViewById(R.id.btnSelectVaccine);
        etcab= findViewById(R.id.etcab);

    }

    public void setBtnSelectVaccine(View v) // Go from Main Screen to Covid Info Window
    {
        Intent intent = new Intent(registration.this, vaccineLayout.class);
        startActivity(intent);
    }

    private boolean checkValidation() // Use this function if it's needed somewhere
    {
        boolean flag = true;
        if (etFirstName.getText().toString().trim().isEmpty()) {
            etFirstName.setError("Name can't be empty");
            flag = false;
        }
        if (etSecondName.getText().toString().isEmpty()) {
            etSecondName.setError("Name can't be empty");
            flag = false;
        }
        if (etCNIC.getText().toString().isEmpty()) {
            etCNIC.setError("The CNIC field can't be empty");
            flag = false;
        }
        if (etPhone.getText().toString().isEmpty()) {
            etPhone.setError("Phone can't be empty");
            flag = false;
        }
        if (etVaccine.getText().toString().isEmpty()) {
            etVaccine.setError("This field can't be empty");
            flag = false;
        }
        return flag;

    }

    public void register(View v) // Register Button on Registration Screen
    {
        if (checkValidation()) {
            Toast.makeText(this, "You have entered right data", Toast.LENGTH_SHORT).show();
        }
        int P_check= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(P_check== PackageManager.PERMISSION_GRANTED)

        {
            String c = etcab.getText().toString().trim();
            if (c.equals("no") || c.equals("NO"))
            {

            my_message();
        }
            else if(c.equals("yes") || c.equals("Yes"))
            {
                my_message2();

            }
        }

        else
        {// if req deny then again req
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},0);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                my_message();
            } else {
                Toast.makeText(this, "You do not have required permission to send sms", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void my_message() {
        String p = etPhone.getText().toString().trim();
        String m = "You have registered for the vaccination. Your will receive the location from where you will get vaccinated.";
        SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(p, null, m, null, null);
            Toast.makeText(this, m,
                    Toast.LENGTH_SHORT).show();

    }
    private void my_message2() {
        String p = etPhone.getText().toString().trim();
        String cab = "You have registered for the vaccination with cab service. Your will receive the location from where you will get vaccinated.";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(p, null, cab, null, null);
        Toast.makeText(this, cab, Toast.LENGTH_SHORT).show();
    }

}







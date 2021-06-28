package com.example.htproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class registration_checker extends AppCompatActivity {
    EditText reg_name,reg_contact;
    Button check_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_checker);
        init();



    }
    public void init()
    {
        reg_name=findViewById(R.id.reg_name);
        reg_contact=findViewById(R.id.reg_contact);
        check_btn =findViewById(R.id.reg_btn);
    }


    public void verify_registration(View v)
    {
        String reg_my_phone= reg_contact.getText().toString().trim();
        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Registration").orderByChild("Phone no");
        userQuery.equalTo(reg_my_phone).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()) {
                            for (DataSnapshot foodSnapshot: dataSnapshot.getChildren()) {

                                String key = foodSnapshot.getKey();

                                Intent intent = new Intent(registration_checker.this, progress_two.class);

                                startActivity(intent);

                            }
                        } else {
                            Toast.makeText(registration_checker.this, "Firstly Register YourSelf", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }
}
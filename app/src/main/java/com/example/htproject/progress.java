package com.example.htproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class progress extends AppCompatActivity {
    Button btnVerification;
    EditText etPhoneChecker;
    String phone;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        init();
    }
    private void init()
    {
        btnVerification = findViewById(R.id.btnVerification);
        etPhoneChecker = findViewById(R.id.etPhoneChecker);
    }
    public void verification (View v)
    {
        phone = etPhoneChecker.getText().toString().trim();
        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Patient").orderByChild("contact");
        userQuery.equalTo(phone).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()) {
                            for (DataSnapshot foodSnapshot: dataSnapshot.getChildren()) {
                                name = "";
                                String key = foodSnapshot.getKey();
                                FirebaseDatabase.getInstance().getReference().child("Post").
                                        child(key);
                                Intent intent = new Intent(progress.this, questions.class);
                                intent.putExtra("contchecker", etPhoneChecker.getText().toString().trim());
                                startActivity(intent);

                            }
                        } else {
                            Toast.makeText(progress.this, "Phone Number Not Found", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }

}
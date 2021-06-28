package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class for_admin extends AppCompatActivity {

    EditText a_user, a_pass;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_admin);
        a_user = findViewById(R.id.a_user);
        a_pass = findViewById(R.id.a_pass);
        check = findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();

            }
        });


    }

    private void read() {

        String r_pass = a_pass.getText().toString().trim();

        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("Password");
        userQuery.equalTo(r_pass).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {

                                String key = foodSnapshot.getKey();
                                FirebaseDatabase.getInstance().getReference().child("Admin").
                                        child(key);
                                Intent intent = new Intent(for_admin.this, all_records.class);

                                startActivity(intent);

                            }
                        } else {
                            Toast.makeText(for_admin.this, "Enter the Correct Password to see the records", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }


    private void write() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("Username", a_user.getText().toString().trim());

        map.put("Password", a_pass.getText().toString().trim());
        FirebaseDatabase.getInstance().getReference().child("Admin").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(for_admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }
}

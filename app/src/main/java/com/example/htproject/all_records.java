package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class all_records extends AppCompatActivity {
    TextView tv_all_records;
    FirebaseDatabase records;
    ListView listView;
    int count;
    String [] array;
    ArrayList arraydata;
    String[] mobileArray;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_records);
        init();
        arraydata = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this,R.layout.listview,arraydata);
        readAll();


    }


    public void  readAll()
    {
        records.getReference().child("Patient")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        String data="";
                        count =0;
                        for(DataSnapshot reg : snapshot.getChildren())
                        {
                            count ++;
                            data=data+ "\n\nPatient Number: " + count + "\n" +reg.child("first_Name").getValue(String.class)
                                    +"\n"+reg.child("age").getValue(String.class)
                                    +"\nContact: "+reg.child("contact").getValue(String.class)
                                    +"\nCovid: "+reg.child("covid").getValue(String.class)
                                    +"\nDiabetes: "+reg.child("diabetes").getValue(String.class)
                                    +"\nBlood Pressure: "+reg.child("blood_Pressure").getValue(String.class);

                        }

                        arraydata.add(data);

                        listView.setAdapter(adapter);




//                        tv_all_records.setText(data);



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }





    public void init()
    {
//        tv_all_records=findViewById(R.id.tv_all_records);
        records=FirebaseDatabase.getInstance();
        listView = findViewById(R.id.listView);
    }
}
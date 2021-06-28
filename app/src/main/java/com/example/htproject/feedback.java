package com.example.htproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class feedback extends AppCompatActivity {
    Button btnFeedback;
    RecyclerView rvFeedBack;
    RecyclerView.Adapter adapter;
    ArrayList <userFeedBackData> persons;
    RecyclerView.LayoutManager fManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
        recyclerView();
    }
    private void init()
    {
        btnFeedback = findViewById(R.id.btnAddFeedback);
        rvFeedBack = findViewById(R.id.rvFeedBack);
        persons = new ArrayList<>();
    }
    public void recyclerView()
    {
        rvFeedBack.setHasFixedSize(true);
        fManager = new LinearLayoutManager(this);
        rvFeedBack.setLayoutManager(fManager);
        persons.add(new userFeedBackData("Nabeel Sabir","Highly Recommended Application"));
        persons.add(new userFeedBackData("Talha Zahid","Excellent first experience"));
        persons.add(new userFeedBackData("Haris Tallat","Great Experience"));
        persons.add(new userFeedBackData("Rao Junaid","I loved it"));
        persons.add(new userFeedBackData("Awais Zahid","Great Application"));
        persons.add(new userFeedBackData("Saqib Hussain","Great Experience"));
        persons.add(new userFeedBackData("Rehan Saleem","Very helpful application"));
        persons.add(new userFeedBackData("Umair Zahid","Nice Application"));
        adapter = new userFeedBackAdapter(this,persons);
        rvFeedBack.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void addFeedback(View v)
    {
        Intent intent = new Intent (feedback.this, add_feedback.class);
        startActivityForResult(intent,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Toast.makeText(this, "Successfully Added your FeedBack", Toast.LENGTH_SHORT).show();
                String name = data.getStringExtra("keyName");
                String feedback= data.getStringExtra("keyFeedback");
                persons.add(new userFeedBackData(name,feedback));
                adapter.notifyDataSetChanged();
            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "Could Not Add Your Feedback", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
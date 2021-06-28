package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class patient_report extends AppCompatActivity {
    TextView p_name,p_age,p_covid,p_diabe;
    String name,contact,age,covid,diabetes,bp;
    String phone;
    float avg;
    PieChart pieChart;
    me.itangqi.waveloadingview.WaveLoadingView loadingView;
    String Question1,Question2,Question3,Question4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_report);
        init();
        Question1 = getIntent().getExtras().getString("keyQuestion1");
        Question2 = getIntent().getExtras().getString("keyQuestion2");
        Question3 = getIntent().getExtras().getString("keyQuestion3");
        Question4 = getIntent().getExtras().getString("keyQuestion4");
        avg=Float.parseFloat(Question1)+Float.parseFloat(Question2)+Float.parseFloat(Question3)+Float.parseFloat(Question4);
        avg = (float) (avg /4.0);
        readData();
        onProgressChanged((int) avg);
        setData();
}
    public void onProgressChanged( int progress) {
        int blackColorValue = Color.parseColor("#171717");
        loadingView.setProgressValue(progress);
        String title = String.valueOf(progress)+"%";
        loadingView.setBottomTitle("");
        loadingView.setCenterTitle("");
        loadingView.setTopTitle("");
        if (progress < 50) {

            loadingView.setBottomTitle(title);
            int redColorValue = Color.parseColor("#D83A56");
            loadingView.setWaveColor(redColorValue) ;
            loadingView.setBorderColor(redColorValue);
            loadingView.setCenterTitleColor(blackColorValue);

        } else if (progress == 50) {

            loadingView.setCenterTitle(title);
            int yellowColorValue = Color.parseColor("#E8E46E");
            loadingView.setWaveColor(yellowColorValue) ;
            loadingView.setBorderColor(yellowColorValue);
            loadingView.setCenterTitleColor(blackColorValue);
        } else {

            loadingView.setTopTitle(title);
            int greenColorValue = Color.parseColor("#B3E283");
            loadingView.setWaveColor(greenColorValue) ;
            loadingView.setBorderColor(greenColorValue);
            loadingView.setCenterTitleColor(blackColorValue);
        }
    }
    private void init()
    {
        loadingView = findViewById(R.id.waveLoadingView);
        p_name=findViewById(R.id.p_name);
        p_age=findViewById(R.id.p_age);
        p_covid=findViewById(R.id.p_covid);
        p_diabe=findViewById(R.id.p_diabe);
        pieChart = findViewById(R.id.piechart);

    }
    private void readData()
    {
        phone =getIntent().getExtras().getString("keyPhone");//cont
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Patient").orderByChild("contact");
        userQuery.equalTo(phone).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot foodSnapshot: dataSnapshot.getChildren()) {
                            name = "";
                            contact = "";
                            age = "";
                            covid = "";
                            bp="";
                            diabetes="";
                            String key = foodSnapshot.getKey();
                            FirebaseDatabase.getInstance().getReference().child("Post").
                                    child(key);
                            name =name+ foodSnapshot.child("first_Name").getValue(String.class);
                            contact = contact+ foodSnapshot.child("contact").getValue(String.class);
                            age = age+ foodSnapshot.child("age").getValue(String.class);
                            covid = covid + foodSnapshot.child("covid").getValue(String.class);
                            bp =bp + foodSnapshot.child("blood_Pressure").getValue(String.class);
                            diabetes =diabetes + foodSnapshot.child("diabetes").getValue(String.class);
                        }
                        p_name.setText(name);
                        p_covid.setText(bp);
                        p_age.setText(age);
                        p_diabe.setText(diabetes);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }
    private void setData()
    {
        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Headache",
                        Integer.parseInt(Question1),
                        Color.parseColor("#39A6A3")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sore Throat",
                        Integer.parseInt(Question2),
                        Color.parseColor("#F6C6EA")));
        pieChart.addPieSlice(
                new PieModel(
                        "Body Pain",
                        Integer.parseInt(Question3),
                        Color.parseColor("#FFC107")));
        pieChart.addPieSlice(
                new PieModel(
                        "Weakness",
                        Integer.parseInt(Question4),
                        Color.parseColor("#7952B3")));
        // To animate the pie chart
        pieChart.startAnimation();
    }
}

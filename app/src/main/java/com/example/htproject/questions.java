package com.example.htproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class questions extends AppCompatActivity {
    EditText etQuestion1, etQuestion2, etQuestion3, etQuestion4;
    int days;
    String phone;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        init();
        phone = getIntent().getExtras().getString("contchecker");
    }
    private void converter() {
        if (etQuestion1.getText().toString().trim().equals("Yes") || etQuestion1.getText().toString().trim().equals("yes")) {
            etQuestion1.setText("50");
        }
        if (etQuestion1.getText().toString().trim().equals("No") || etQuestion1.getText().toString().trim().equals("no")) {
            etQuestion1.setText("4");
        }
        if (etQuestion2.getText().toString().trim().equals("Yes") || etQuestion2.getText().toString().trim().equals("yes")) {
            etQuestion2.setText("50");
        }
        if (etQuestion2.getText().toString().trim().equals("No") || etQuestion2.getText().toString().trim().equals("no")) {
            etQuestion2.setText("4");
        }
    }
    private void init() {
        etQuestion1 = findViewById(R.id.etQuestion1);
        etQuestion2 = findViewById(R.id.etQuestion2);
        etQuestion4 = findViewById(R.id.etQuestion4);
        etQuestion3 = findViewById(R.id.etQuestion3);
        btnSubmit = findViewById(R.id.btnSubmitQuestions);
    }
    private boolean checkValidation()
    {
        boolean flag = true;
        if (etQuestion1.getText().toString().trim().isEmpty()) {
            etQuestion1.setError("Question One can't be empty");
            flag = false;
        }
        if (etQuestion2.getText().toString().isEmpty()) {
            etQuestion2.setError("Question Two can't be empty");
            flag = false;
        }
        if (etQuestion3.getText().toString().isEmpty()) {
            etQuestion3.setError("Question 3 can't be empty");
            flag = false;
        }
        if (etQuestion4.getText().toString().isEmpty()) {
            etQuestion4.setError("Question 4 can't be empty");
            flag = false;
        }
        return flag;
    }
    public void submitQuestion(View v) {

        if (checkValidation()) {
            converter();
            Intent intent = new Intent(questions.this, patient_report.class);
            intent.putExtra("keyQuestion1", etQuestion1.getText().toString().trim());
            intent.putExtra("keyQuestion2", etQuestion2.getText().toString().trim());
            intent.putExtra("keyQuestion3", etQuestion3.getText().toString().trim());
            intent.putExtra("keyQuestion4", etQuestion4.getText().toString().trim());
            intent.putExtra("keyPhone", phone);
            startActivity(intent);
        }

    }
}
package com.example.tp5firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp5firebase.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {

    EditText name;
    EditText gradeAndroid;
    EditText gradeJava;
    EditText gradeWeb;
    DatabaseReference databaseReference;

    Button addStudent;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        gradeAndroid = findViewById(R.id.gradeAndroid);
        gradeJava = findViewById(R.id.gradeJava);
        gradeWeb = findViewById(R.id.gradeWeb);
        addStudent = findViewById(R.id.addStudentButton);
        addStudent.setOnClickListener(v -> addStudent());

        Intent intent = getIntent();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(intent.getStringExtra("studentName"));
        student = new Student();
    }

    public void addStudent() {
        student.setName(name.getText().toString());
        student.setGradeAndroid(Float.parseFloat(gradeAndroid.getText().toString()));
        student.setGradeJava(Float.parseFloat(gradeJava.getText().toString()));
        student.setGradeWeb(Float.parseFloat(gradeWeb.getText().toString()));
        databaseReference.child(student.getName()).setValue(student);
        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
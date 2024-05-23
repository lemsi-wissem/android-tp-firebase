package com.example.tp5firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp5firebase.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> students = new ArrayList<>();
    ArrayList<String> grades = new ArrayList<>();
    private final String[] subjects = {"Android", "Java", "Web"};

    DatabaseReference databaseReference;

    String selectedStudent;

    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView and AutoCompleteTextView from the layout
        ListView listView = findViewById(R.id.listView);
        this.autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        // Create an ArrayAdapter for the ListView and set it
        this.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = ((TextView) view).getText().toString();
                MyAdapter adapter;
                getNotes(selectedStudent);
                System.out.println(grades);
                adapter = new MyAdapter(MainActivity.this, grades);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedSubject = subjects[position];
                        TextView textView = view.findViewById(R.id.note);
                        if (Integer.parseInt(textView.getText().toString()) < 10) {
                            Toast.makeText(MainActivity.this, "The student has failed in " + selectedSubject, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "The student has passed in " + selectedSubject, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        // Set an item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubject = subjects[position];
                TextView textView = view.findViewById(R.id.note);
                if (Integer.parseInt(textView.getText().toString()) < 10) {
                    Toast.makeText(MainActivity.this, "The student has failed in " + selectedSubject, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "The student has passed in " + selectedSubject, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addStudent(View v) {
        Intent intent = new Intent(this, AddStudent.class);
        intent.putExtra("studentName", "Student");
        startActivityForResult(intent, 1);
    }

    public void getNotes( String studentName) {
        //grades.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Student s;
                s = snapshot.child(studentName).getValue(Student.class);
                if (s == null) {
                    return;
                }
                grades.add(String.valueOf(s.getGradeAndroid()));
                grades.add(String.valueOf(s.getGradeJava()));
                grades.add(String.valueOf(s.getGradeWeb()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudentsName();

        ArrayAdapter ar = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, this.students);

        autoCompleteTextView.setAdapter(ar);
    }

    private void getStudentsName() {
        this.students.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Student s = ds.getValue(Student.class);

                    students.add(s.getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
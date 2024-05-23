package com.example.tp5firebase.model;

public class Student {
    private String name;

    private float gradeAndroid;

    private float gradeJava;

    private float gradeWeb;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGradeAndroid() {
        return gradeAndroid;
    }

    public void setGradeAndroid(float gradeAndroid) {
        this.gradeAndroid = gradeAndroid;
    }

    public float getGradeJava() {
        return gradeJava;
    }

    public void setGradeJava(float gradeJava) {
        this.gradeJava = gradeJava;
    }

    public float getGradeWeb() {
        return gradeWeb;
    }

    public void setGradeWeb(float gradeWeb) {
        this.gradeWeb = gradeWeb;
    }
}

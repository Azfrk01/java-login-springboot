package com.example.demo.model;

public class Student{
    private int id;
    private String name;
    private String branch;
    private int year;
    public Student(){

    }
    public Student(int id, String name, String branch, int year){
        this.id=id;
        this.name=name;
        this.branch=branch;
        this.year=year;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getBranch(){
        return branch;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
}
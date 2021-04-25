package com.Shalazary;

import java.util.Date;

public class Person {
    private int ID;
    private String Name;
    private String Gender;
    private Date BirtDate;
    private Division DivisionName;
    private double Salary;

    public Person() {

    }

    public Person(int ID, String name, String gender, Date birtDate, Division divisionName, double salary) {
        this.ID = ID;
        Name = name;
        Gender = gender;
        BirtDate = birtDate;
        DivisionName = divisionName;
        Salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirtDate() {
        return BirtDate;
    }

    public void setBirtDate(Date birtDate) {
        BirtDate = birtDate;
    }

    public Division getDivision() {
        return DivisionName;
    }

    public void setDivision(Division divisionName) {
        DivisionName = divisionName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}

package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    private List<Student> students;
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student studentToFind = null;
        for (Student student : students) {
            Double studentsGrade = student.getAverageGrade();
            Double averageGradeToSearchFor = averageGrade;
            if (studentsGrade.equals(averageGradeToSearchFor)) {
                studentToFind = student;
            }

        }
        //TODO:
        return studentToFind;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        ArrayList<Double> grades = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            grades.add(students.get(i).getAverageGrade());
        }
        return getStudentWithAverageGrade(Collections.max(grades));
    }

    public Student getStudentWithMinAverageGrade() {
        ArrayList<Double> grades = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            grades.add(students.get(i).getAverageGrade());
        }
        return getStudentWithAverageGrade(Collections.min(grades));
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public static void main(String[] args) {

    }
}
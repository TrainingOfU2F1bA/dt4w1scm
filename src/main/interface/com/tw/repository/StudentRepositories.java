package com.tw.repository;

import com.tw.bean.Student;

import java.util.List;

public interface StudentRepositories {
    public void saveStudent(Student student);
    public List<Student> findStudent(List<String> nums);
}

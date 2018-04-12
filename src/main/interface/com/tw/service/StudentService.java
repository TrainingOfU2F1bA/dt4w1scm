package com.tw.service;

import com.tw.bean.Student;

import java.util.List;

public interface StudentService {
    public boolean saveStudent(Student student);
    public List<String> generateReportList(List<Student> students);
}

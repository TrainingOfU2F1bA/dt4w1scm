package com.tw.io;

import com.tw.bean.Student;

import java.util.List;

public interface StudentInfoReader {
    public Student readStudentInfo();
    public List<Student> readNumsofStudent();
}

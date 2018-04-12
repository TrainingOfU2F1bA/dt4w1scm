package com.tw.service;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepositories studentRepositories;

    public StudentServiceImpl(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
    }

    public StudentServiceImpl() {
    }

    public StudentRepositories getStudentRepositories() {
        return studentRepositories;
    }

    public void setStudentRepositories(StudentRepositories studentRepositories) {
        this.studentRepositories = studentRepositories;
}

    @Override
    public boolean saveStudent(Student student) {
        if (studentRepositories!=null) {
            studentRepositories.saveStudent(student);
            return true;
        } else  return false;
    }

    @Override
    public List<String> generateReportList(List<Student> students) {
        return null ;
    }
}

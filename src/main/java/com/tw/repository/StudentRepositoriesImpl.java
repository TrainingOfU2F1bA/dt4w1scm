package com.tw.repository;

import com.tw.bean.Student;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepositoriesImpl implements StudentRepositories {
    HashMap<String,Student> map=new HashMap<>();

    @Override
    public void saveStudent(Student student) {
        map.put(student.getId(),student);
    }

    @Override
    public List<Student> findStudent(List<String> nums) {
        return nums.stream().map(x->map.get(x)).collect(Collectors.toList());
    }
}

package com.tw.service;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (studentRepositories != null) {
            studentRepositories.saveStudent(student);
            return true;
        } else return false;
    }

    @Override
    public List<String> generateReportList(List<Student> students) {
        List<String> strings = students.stream().map(
                x -> x.getName()
                        + "|" + x.getMap().values().stream().map(m -> m.toString()).reduce((a, b) -> a + "|" + b).get()
                        + "|" + x.getMap().values().stream().reduce((a, b) -> a + b).get()).collect(Collectors.toList()
        );
        String course = "姓名|" + students.stream().map(x -> x.getMap().keySet().stream().reduce((a, b) -> a + "|" + b).get()+"|总分").collect(Collectors.toList()).get(0);
        int n = students.get(0).getMap().size() * students.size();
        int avg = students.stream().map(x -> x.getMap().values().stream().reduce((a, b) -> a + b).get()).reduce((a, b) -> a + b).get() / n;
        int max = students.stream().map(x -> x.getMap().values().stream().reduce((a, b) -> a > b ? a : b).get()).reduce((a, b) -> a > b ? a : b).get();
        int min = students.stream().map(x -> x.getMap().values().stream().reduce((a, b) -> a < b ? a : b).get()).reduce((a, b) -> a < b ? a : b).get();
        int mid = (max + min) / 2;
        ArrayList<String> list = new ArrayList<>();
        list.add("成绩单");
//        list.add("姓名|数学|语文|英语|编程|平均分|总分");
        list.add(course);
        list.add(course.replaceAll(".","="));
        strings.stream().forEach(x -> list.add(x));
        list.add(course.replaceAll(".","="));
        list.add("全班总分平均数：" + avg);
        list.add("全班总分中位数：" + mid);
        return list;
    }
}

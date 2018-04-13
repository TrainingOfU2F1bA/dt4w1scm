package com.tw.service;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        }
        return false;
    }

    @Override
    public List<String> generateReportList(List<Student> students) {
        ArrayList<String> list = new ArrayList<>();
        String tableHeader = getTableHeader(students);
        List<String> studentScoreTable = getStudentScoreTable(students);
        list.add("成绩单");
        list.add(tableHeader);
        list.add(tableHeader.replaceAll(".", "="));
        list.addAll(studentScoreTable);
        list.add(tableHeader.replaceAll(".", "="));
        list.add("全班总分平均数：" + getAvg(students, getSubjectQuantity(students) * students.size()));
        list.add("全班总分中位数：" + (getMax(students) + (int) getMin(students)) / 2);
        return list;
    }

    private List<String> getStudentScoreTable(List<Student> students) {
        Function<Student, Stream<Integer>> mapper = x -> x.getMap().values().stream();
        return students.stream().map(
                x -> x.getName()
                        + "|" + mapper.apply(x).map(m -> m.toString()).reduce((a, b) -> a + "|" + b).get()
                        + "|" + mapper.apply(x).reduce((a, b) -> a + b).get()).collect(Collectors.toList()
        );
    }

    private String getTableHeader(List<Student> students) {
        return "姓名|" + students.stream().map(x -> getCourseNameList(x).reduce((a, b) -> a + "|" + b).get() + "|总分").collect(Collectors.toList()).get(0);
    }

    public Stream<String> getCourseNameList(Student x) {
        return x.getMap().keySet().stream();
    }

    public int getSubjectQuantity(List<Student> students) {
        return students.get(0).getMap().size();
    }

    public Integer getMin(List<Student> students) {
        Function<Student, Stream<? extends Integer>> mapper = x -> x.getMap().values().stream();
        return students.stream().flatMap(mapper).reduce((a, b) -> a < b ? a : b).get();
    }

    public Integer getMax(List<Student> students) {
        Function<Student, Stream<? extends Integer>> mapper = x -> x.getMap().values().stream();
        return students.stream().flatMap(mapper).reduce((a, b) -> a > b ? a : b).get();
    }

    public int getAvg(List<Student> students, int n) {
        Function<Student, Stream<? extends Integer>> mapper = x -> x.getMap().values().stream();
        return students.stream().flatMap(mapper).reduce((a, b) -> a + b).get() / n;
    }
}

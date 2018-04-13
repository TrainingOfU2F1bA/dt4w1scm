package com.tw.io;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentInfoReaderImpl implements StudentInfoReader {
    SystemHintPrinter systemHintPrinter;
    LibraryReader libraryReader;
    private StudentRepositories repositories;

    public LibraryReader getLibraryReader() {
        return libraryReader;
    }

    public void setLibraryReader(LibraryReader libraryReader) {
        this.libraryReader = libraryReader;
    }

    public SystemHintPrinter getSystemHintPrinter() {
        return systemHintPrinter;
    }

    public void setSystemHintPrinter(SystemHintPrinter systemHintPrinter) {
        this.systemHintPrinter = systemHintPrinter;
    }

    public StudentRepositories getRepositories() {
        return repositories;
    }

    public void setRepositories(StudentRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public Student readStudentInfo() {
        systemHintPrinter.hintInputStudentInfo();
        Pattern pattern = Pattern.compile("\\(.+,[0-9]+,([^:]*:1?[1-9]?[0-9])+\\)");
        while (true) {
            String read = libraryReader.read();
            if (pattern.matcher(read).matches()) {
                String[] strings = read.replaceAll("\\(|\\)", "").split(",");
                Map<String, Integer> map = Arrays.stream(strings, 2, strings.length).collect(
                        Collectors.toMap(
                                x -> x.split(":")[0],
                                x -> Integer.parseInt(x.split(":")[1])
                        )
                );
                return new Student(strings[0], strings[1], map);
            }
            systemHintPrinter.warningInputRightFormatStudentInfo();
        }
    }

    @Override
    public List<Student> readNumsofStudent() {
        systemHintPrinter.hintInputNumofStudent();
        Pattern pattern = Pattern.compile("\\(([0-9]*,)+([0-9]*)\\)");
        while (true) {
            String read = libraryReader.read();
            if (pattern.matcher(read).matches()) {
                List<String> collect = Arrays.stream(read.replaceAll("\\(|\\)", "").split(",")).collect(Collectors.toList());
                return repositories.findStudent(collect);
            }else systemHintPrinter.warningInputRightFormatNumofStudent();
        }
    }

}

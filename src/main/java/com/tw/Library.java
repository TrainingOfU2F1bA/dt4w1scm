package com.tw;

import com.tw.io.LibraryReader;
import com.tw.io.StudentInfoPrinter;
import com.tw.io.StudentInfoReader;
import com.tw.service.StudentService;

public class Library {
    private StudentInfoPrinter studentInfoPrinter;
    private StudentInfoReader studentInfoReader;
    private LibraryReader libraryReader;
    private StudentService studentService;

    public Library(StudentInfoPrinter studentInfoPrinter, StudentInfoReader studentInfoReader, LibraryReader libraryReader, StudentService studentService) {
        this.studentInfoPrinter = studentInfoPrinter;
        this.studentInfoReader = studentInfoReader;
        this.libraryReader = libraryReader;
        this.studentService = studentService;
    }

    public void systemBootUp() {
        while (true) {
            switch (libraryReader.readInt()) {
                case 1:
                    studentService.saveStudent(
                            studentInfoReader.readStudentInfo());
                    break;
                case 2:
                    studentInfoPrinter.printScoreReport(
                            studentService.generateReportList(
                                    studentInfoReader.readNumsofStudent()));
                    break;
                default:
                    return;
            }
        }
    }

}

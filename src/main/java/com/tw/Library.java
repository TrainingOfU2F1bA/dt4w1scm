package com.tw;

import com.tw.io.LibraryReader;
import com.tw.io.StudentInfoPrinter;
import com.tw.io.StudentInfoReader;
import com.tw.io.SystemHintPrinter;
import com.tw.service.StudentService;

public class Library {
    private StudentInfoPrinter studentInfoPrinter;
    private StudentInfoReader studentInfoReader;
    private LibraryReader libraryReader;
    private StudentService studentService;
    private SystemHintPrinter systemHintPrinter;

    public Library() {
    }

    public Library(StudentInfoPrinter studentInfoPrinter, StudentInfoReader studentInfoReader, LibraryReader libraryReader, StudentService studentService, SystemHintPrinter systemHintPrinter) {
        this.studentInfoPrinter = studentInfoPrinter;
        this.studentInfoReader = studentInfoReader;
        this.libraryReader = libraryReader;
        this.studentService = studentService;
        this.systemHintPrinter=systemHintPrinter;
    }


    public StudentInfoPrinter getStudentInfoPrinter() {
        return studentInfoPrinter;
    }

    public void setStudentInfoPrinter(StudentInfoPrinter studentInfoPrinter) {
        this.studentInfoPrinter = studentInfoPrinter;
    }

    public StudentInfoReader getStudentInfoReader() {
        return studentInfoReader;
    }

    public void setStudentInfoReader(StudentInfoReader studentInfoReader) {
        this.studentInfoReader = studentInfoReader;
    }

    public LibraryReader getLibraryReader() {
        return libraryReader;
    }

    public void setLibraryReader(LibraryReader libraryReader) {
        this.libraryReader = libraryReader;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public SystemHintPrinter getSystemHintPrinter() {
        return systemHintPrinter;
    }

    public void setSystemHintPrinter(SystemHintPrinter systemHintPrinter) {
        this.systemHintPrinter = systemHintPrinter;
    }

    public void systemBootUp() {
        systemHintPrinter.hintUsertoChooseModule();
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
            systemHintPrinter.hintUsertoChooseModule2();
        }
    }

}

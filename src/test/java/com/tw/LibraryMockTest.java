package com.tw;

import com.tw.bean.Student;
import com.tw.io.LibraryReader;
import com.tw.io.StudentInfoPrinter;
import com.tw.io.StudentInfoReader;
import com.tw.service.StudentService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class LibraryMockTest {

    private StudentInfoReader studentInfoReader;
    private StudentInfoPrinter studentInfoPrinter;
    private LibraryReader libraryReader;
    private StudentService studentService;
    private Library library;

    @Before
    public void setUp() throws Exception {
        studentInfoPrinter = mock(StudentInfoPrinter.class);
        studentInfoReader = mock(StudentInfoReader.class);
        libraryReader = mock(LibraryReader.class);
        studentService = mock(StudentService.class);
        library = new Library(studentInfoPrinter,studentInfoReader,libraryReader,studentService);
    }

    @Test
    public void testMainLoop() {
        Student student = mock(Student.class);
        ArrayList<Student> students = mock(ArrayList.class);
        students.add(student);
        ArrayList<String> strings = mock(ArrayList.class);
        when(libraryReader.readInt()).thenReturn(1, 2, 2,3);
        when(studentInfoReader.readStudentInfo()).thenReturn(student);
        when(studentService.saveStudent(student)).thenReturn(true);
        when(studentInfoReader.readNumsofStudent()).thenReturn(students);
        when(studentService.generateReportList(students)).thenReturn(strings);
        library.systemBootUp();
        verify(studentInfoReader, times(1)).readStudentInfo();
        verify(studentService,times(1)).saveStudent(student);
        verify(studentInfoReader,times(2)).readNumsofStudent();
        verify(studentService,times(2)).generateReportList(students);
        verify(studentInfoPrinter,times(2)).printScoreReport(strings);
    }
}

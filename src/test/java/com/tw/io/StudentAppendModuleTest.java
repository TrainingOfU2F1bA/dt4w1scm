package com.tw.io;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;
import com.tw.repository.StudentRepositoriesImpl;
import com.tw.service.StudentService;
import com.tw.service.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class StudentAppendModuleTest {

    private StudentInfoReaderImpl reader;
    private SystemHintPrinter systemHintPrinter;
    private LibraryReader libraryReader;
    private StudentService studentService;
    private StudentRepositories studentRepositories;

    @Before
    public void setUp() throws Exception {
        reader = new StudentInfoReaderImpl();
        systemHintPrinter = mock(SystemHintPrinter.class);
        libraryReader = mock(LibraryReader.class);
        reader.setSystemHintPrinter(systemHintPrinter);
        reader.setLibraryReader(libraryReader);
    }

    @Test
    public void testReadStudentInfo() {
        when(libraryReader.read()).thenReturn("(李云,14023220,高等数学:99,大学物理:96)");
        Student student = reader.readStudentInfo();
        verify(systemHintPrinter,times(1)).hintInputStudentInfo();
        Assert.assertEquals(student.getName(),"李云");
    }

    @Test
    public void testReadWrongStudentInfo() {
        when(libraryReader.read()).thenReturn("李云,14023220,高等数学:99,大学物理:96)","李云,14023220,高等数学:99,大学物理:96)","(李云,14023220,高等数学:99,大学物理:96)");
        Student student = reader.readStudentInfo();
        verify(systemHintPrinter,times(1)).hintInputStudentInfo();
        verify(systemHintPrinter,times(2)).warningInputRightFormatStudentInfo();
        Assert.assertEquals(student.getId(),"14023220");
    }

    @Test
    public void testSaveStudent() {
        StudentRepositories studentRepositories = mock(StudentRepositories.class);
        studentService=new StudentServiceImpl(studentRepositories);
        Student student = mock(Student.class);
        studentService.saveStudent(student);
        verify(studentRepositories,times(1)).saveStudent(student);
    }

    @Test
    public void testRespositoriesSaveStudent() {
        Student student = new Student();
        student.setId("10000");
        StudentRepositories studentRepositories = new StudentRepositoriesImpl();
        studentRepositories.saveStudent(student);
        String[] array = {"10000"};
        studentRepositories.findStudent(Arrays.stream(array).collect(Collectors.toList())).toArray().equals(array);
    }
}

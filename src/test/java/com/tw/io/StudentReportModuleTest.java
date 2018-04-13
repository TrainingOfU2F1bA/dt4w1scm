package com.tw.io;

import com.tw.bean.Student;
import com.tw.repository.StudentRepositories;
import com.tw.service.StudentService;
import com.tw.service.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class StudentReportModuleTest {

    private StudentInfoReaderImpl reader;
    private SystemHintPrinter systemHintPrinter;
    private LibraryReader libraryReader;
    private StudentService studentService;
    private StudentRepositories studentRepositories;

    @Before
    public void setUp() throws Exception {
        reader = new StudentInfoReaderImpl();
        systemHintPrinter = mock(SystemHintPrinter.class);
        studentRepositories = mock(StudentRepositories.class);
        libraryReader = mock(LibraryReader.class);
        reader.setRepositories(studentRepositories);
        reader.setSystemHintPrinter(systemHintPrinter);
        reader.setLibraryReader(libraryReader);
        when(studentRepositories.findStudent(anyList())).thenReturn(
                Arrays.asList(IntStream.rangeClosed(1, 4).mapToObj(x1 -> String.valueOf(x1)).toArray()).stream().map(x -> new Student("李璐", String.valueOf(x),
                        IntStream.of(96).boxed().collect(Collectors.toMap(
                                m -> "Math",
                                m -> m
                        )))).collect(Collectors.toList()));
    }

    @Test
    public void testReadStudentNum() {
        int[] array = IntStream.rangeClosed(1, 4).toArray();
        when(libraryReader.read()).thenReturn("(1,2,3,4)");
        List<Student> students = reader.readNumsofStudent();
        int[] obj = students.stream().mapToInt(x -> Integer.parseInt(x.getId())).sorted().toArray();
        Assert.assertArrayEquals(obj, array);
    }

    @Test
    public void testReadWrongStudentNum() {
        int[] array = IntStream.rangeClosed(1, 4).toArray();
        when(libraryReader.read()).thenReturn("(1,2,3,4)");
        when(libraryReader.read()).thenReturn("(1,2,3,4", "(2321jdsah)", "(1,2,3,4)");
        List<Student> students = reader.readNumsofStudent();
        verify(systemHintPrinter, times(1)).hintInputNumofStudent();
        verify(systemHintPrinter, times(2)).warningInputRightFormatNumofStudent();
        int[] obj = students.stream().mapToInt(x -> Integer.parseInt(x.getId())).sorted().toArray();
        Assert.assertArrayEquals(obj, array);
    }

    @Test
    public void testGenerateReport() {
        when(libraryReader.read()).thenReturn("(1,2,3,4)");
        List<Student> students = reader.readNumsofStudent();
        studentService = new StudentServiceImpl();
        List<String> strings = studentService.generateReportList(students);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StudentInfoPrinter studentInfoPrinter = new StudentInfoPrinterImpl(new LibraryPrinter());
        studentInfoPrinter.printScoreReport(strings);
        Assert.assertEquals(out.toString(), "成绩单姓名|Math|总分==========李璐|96|96李璐|96|96李璐|96|96李璐|96|96==========全班总分平均数：96全班总分中位数：96");
    }
}

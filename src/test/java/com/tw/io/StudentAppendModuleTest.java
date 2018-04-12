package com.tw.io;

import com.tw.bean.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StudentAppendModuleTest {

    private StudentInfoReaderImpl reader;
    private SystemHintPrinter systemHintPrinter;
    private LibraryReader libraryReader;

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
}

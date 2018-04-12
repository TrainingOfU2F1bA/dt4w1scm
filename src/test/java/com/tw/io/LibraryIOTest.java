package com.tw.io;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LibraryIOTest {
    private ByteArrayOutputStream out=new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(out));
    }
    @Test
    public void testLibraryPrinter() {
        LibraryPrinter printer = new LibraryPrinter();
        printer.print("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：");
        assertThat(out.toString(), new IsEqual("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）："));
    }

    @Test
    public void testLibraryReader() throws IOException {
        System.setIn(new ByteArrayInputStream("Student".getBytes()));
        LibraryReader reader = new LibraryReader();
        assertThat(reader.read(),new IsEqual("Student"));
    }
}

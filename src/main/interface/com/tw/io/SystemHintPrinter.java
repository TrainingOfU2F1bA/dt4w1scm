package com.tw.io;

import com.tw.bean.Student;

public interface SystemHintPrinter {
    public void hintUsertoChooseModule();

    void hintUsertoChooseModule2();

    public void hintInputStudentInfo();
    public void warningInputRightFormatStudentInfo();
    public void hintStudentInfohasbeenAppended();
    public void hintInputNumofStudent();
    public void warningInputRightFormatNumofStudent();
}

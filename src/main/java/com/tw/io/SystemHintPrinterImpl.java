package com.tw.io;

import com.tw.HintConfig;

public class SystemHintPrinterImpl implements SystemHintPrinter {
    private LibraryPrinter libraryPrinter;

    public SystemHintPrinterImpl(LibraryPrinter libraryPrinter) {
        this.libraryPrinter = libraryPrinter;
    }

    public SystemHintPrinterImpl() {
    }

    public LibraryPrinter getLibraryPrinter() {
        return libraryPrinter;
    }

    public void setLibraryPrinter(LibraryPrinter libraryPrinter) {
        this.libraryPrinter = libraryPrinter;
    }

    @Override
    public void hintUsertoChooseModule() {
        libraryPrinter.print(HintConfig.HINT_USER_TO_CHOOSE_MODULE);
    }
    @Override
    public void hintUsertoChooseModule2() {
        libraryPrinter.print(HintConfig.HINT_USER_TO_CHOOSE_MODULE2);
    }
    @Override
    public void hintInputStudentInfo() {
        libraryPrinter.print(HintConfig.HINT_INPUT_STUDENT_INFO);
    }

    @Override
    public void warningInputRightFormatStudentInfo() {
        libraryPrinter.print(HintConfig.WARNING_INPUT_RIGHT_FORMAT_STUDENT_INFO);
    }

    @Override
    public void hintStudentInfohasbeenAppended() {
        libraryPrinter.print(HintConfig.HINT_STUDENT_INFO_HASBEEN_APPENDED);
    }

    @Override
    public void hintInputNumofStudent() {
        libraryPrinter.print(HintConfig.HINT_INPUT_NUMOF_STUDENT);
    }

    @Override
    public void warningInputRightFormatNumofStudent() {
        libraryPrinter.print(HintConfig.WARNING_INPUT_RIGHT_FORMAT_NUMOF_STUDENT);
    }
}

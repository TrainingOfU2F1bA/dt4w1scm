package com.tw.io;

import java.util.List;

public class StudentInfoPrinterImpl implements StudentInfoPrinter {

    private LibraryPrinter libraryPrinter;

    public StudentInfoPrinterImpl(LibraryPrinter libraryPrinter) {
        this.libraryPrinter = libraryPrinter;
    }

    public StudentInfoPrinterImpl() {
    }

    public LibraryPrinter getLibraryPrinter() {
        return libraryPrinter;
    }

    public void setLibraryPrinter(LibraryPrinter libraryPrinter) {
        this.libraryPrinter = libraryPrinter;
    }

    @Override
    public void printScoreReport(List<String> stus) {
    stus.stream().forEach(x->libraryPrinter.print(x));
    }
}

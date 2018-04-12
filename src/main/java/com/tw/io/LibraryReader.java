package com.tw.io;

import java.util.Scanner;

public class LibraryReader {
    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

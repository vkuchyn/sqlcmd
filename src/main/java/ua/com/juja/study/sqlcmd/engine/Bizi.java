package ua.com.juja.study.sqlcmd.engine;

import jline.ConsoleReader;

import java.io.IOException;

// It's a mirracl

public class Bizi {
    public static void main(String[] args) throws IOException {
        ConsoleReader consoleReader = new ConsoleReader();
        String line;
        StringBuilder queryBuilder = new StringBuilder();
        while (!(line = consoleReader.readLine()).equals("\\q")) {


        }
        System.out.println("Query = " + queryBuilder.toString());
    }

}
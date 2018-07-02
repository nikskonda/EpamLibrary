package by.epam.java.training.servise.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {


    public static String readText(String fileName){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("text/book1.txt")))){
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

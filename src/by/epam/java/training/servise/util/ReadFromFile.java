package by.epam.java.training.servise.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {

    private static final Integer TOTAL_LETTER_ON_PAGE = 5000;
    private static final Character NEW_LINE = '\n';

    public static String readText(String fileName, Integer page){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))){
            int c;
            int count = 0;
            boolean flag = page==1?true:false;
            while ((c = reader.read()) != -1) {
                if (count > (page-1)*TOTAL_LETTER_ON_PAGE && (char)c == NEW_LINE) {
                    flag = true;
                }
                if (count >= (page-1)*TOTAL_LETTER_ON_PAGE && flag) {
                    sb.append((char)c);
                }
                if (count > (page)*TOTAL_LETTER_ON_PAGE && (char)c == NEW_LINE) {
                    break;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}

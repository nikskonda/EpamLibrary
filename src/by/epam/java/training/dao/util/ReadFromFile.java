package by.epam.java.training.dao.util;

import static by.epam.java.training.web.command.util.FieldNames.*;

import java.io.*;

public class ReadFromFile {

    public static String readText(String fileName, Integer page){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))){
            int c;
            int count = 0;
            boolean flag = (page==1) ? true : false;

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

    public static int calcCountOfPagesWithText(String fileName){
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))){
            while ((reader.read()) != -1) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int)Math.ceil(count/TOTAL_LETTER_ON_PAGE);
    }

}

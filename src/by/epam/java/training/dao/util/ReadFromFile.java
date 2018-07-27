package by.epam.java.training.dao.util;

import by.epam.java.training.dao.exception.DAOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

import java.io.*;

public class ReadFromFile {

    private static final int EOF = -1;

    public static String readText(String fileName, Integer page) throws DAOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))){
            int c;
            int count = 0;
            int currentPage = 0;
            boolean flag = (page==1) ? true : false;

            while ((c = reader.read()) != EOF) {
                count++;
                if (count>TOTAL_LETTER_ON_PAGE && (char)c == NEW_LINE){
                    count = 0;
                    currentPage++;
                }
                if (currentPage == page-1){
                    sb.append((char)c);
                }
                if (currentPage >= page){
                    break;
                }
            }
        } catch (IOException ex) {
            throw new DAOException("Error reading text from file.", ex);
        }
        return sb.toString();
    }

    public static int calcPagesCountText(String fileName) throws DAOException{
        int count = 0;
        int pages = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))){
            int c;
            while ((c = reader.read()) != EOF) {
                count++;
                if (count>TOTAL_LETTER_ON_PAGE && (char)c == NEW_LINE){
                    count = 0;
                    pages++;
                }
            }
        } catch (IOException ex) {
            throw new DAOException("Error calculating pages with text.", ex);
        }
        return ++pages;
    }

}

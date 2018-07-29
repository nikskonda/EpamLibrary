package by.epam.training.dao.util;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.web.command.util.FieldNameConstant;

import java.io.*;

/**
 * Class implements the ability to read from a file.
 *
 * @author  Nikita Shkonda
 */
public class ReadFromFile {

    private static final int EOF = -1;
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * Returns text from a specific page in a file.
     *
     * @param fileName - name of the file.
     * @param page - number of page.
     *
     * @return text from a specific page in a file.
     *
     * @throws DAOException  if there was an error reading from a file.
     *
     */
    public static String readText(String fileName, Integer page) throws DAOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), CHARSET_NAME))){
            int c;
            int count = 0;
            int currentPage = 0;

            while ((c = reader.read()) != EOF) {
                count++;
                if (count> FieldNameConstant.TOTAL_LETTER_ON_PAGE && (char)c == FieldNameConstant.NEW_LINE){
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

    /**
     * Returns count of pages with text in a file.
     *
     * @param fileName - name of the file.
     *
     * @return count of pages with text in a file.
     *
     * @throws DAOException  if there was an error reading from a file.
     *
     */
    public static int calcPagesCountText(String fileName) throws DAOException{
        int count = 0;
        int pages = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), CHARSET_NAME))){
            int c;
            while ((c = reader.read()) != EOF) {
                count++;
                if (count> FieldNameConstant.TOTAL_LETTER_ON_PAGE && (char)c == FieldNameConstant.NEW_LINE){
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

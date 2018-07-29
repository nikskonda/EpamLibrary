package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.dao.util.ReadFromFile;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Implementing the {@link BookService} interface for different activities with books.
 *
 * @author  Nikita Shkonda
 */
public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO = DAOFactory.getBookDAO();


    /**
     * Return information about book by id and language
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     *
     * @return information about book.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
    @Override
    public Book getBook(Integer bookId, String locale) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId))){
            return null;
        }
        try {
            return bookDAO.getBook(bookId, locale);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return text of the book for specific page and language.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     * @param path - Path to current repository.
     * @param numberOfPage - Number of page.
     *
     * @return list of paragraphs of text.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
    @Override
    public List<String> getTextOfBook(Integer bookId, String locale, String path, Integer numberOfPage) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, path)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, numberOfPage)){
            return null;
        }

        try {
            String fileName = path+bookDAO.getUrlToTextOfBook(bookId, locale);
            String bookText = ReadFromFile.readText(fileName, numberOfPage);
            if (bookText.isEmpty()){
                return null;
            }
            List<String> text = Arrays.asList(bookText.split(NEW_LINE.toString()));
            return text;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    /**
     * Return total count of page with text.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     * @param path - Path to current repository.
     *
     * @return Total count of page with text.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    @Override
    public Integer calcPagesCountText(Integer bookId, String locale, String path) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, path)){
            return null;
        }
        String fileName = null;
        try {
            fileName = path+bookDAO.getUrlToTextOfBook(bookId, locale);
            return ReadFromFile.calcPagesCountText(fileName);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    /**
     * Return list of book previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see BookPreview
     * @see PageAttribute
     *
     */
    @Override
    public List<BookPreview> getBooksPerPage(PageAttribute pageAttribute) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)){
            return null;
        }

        try {
            return bookDAO.getBooksPerPage(pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return total count of page with books.
     *
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of page with book.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    @Override
    public Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBooksOnOnePage)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }

        try {
            return  bookDAO.calcPagesCountBooks(locale, countBooksOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return list of genres for specified language.
     *
     * @param lang - Language.
     *
     * @return List of genres.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    @Override
    public List<Genre> getGenres(String lang) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return null;
        }

        try {
            return bookDAO.getGenres(lang);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}

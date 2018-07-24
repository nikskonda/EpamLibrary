package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.dao.util.ReadFromFile;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO = DAOFactory.getBookDAO();


    @Override
    public Book getBook(Integer bookId, String locale) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId))){
            return null;
        }
        return bookDAO.getBook(bookId, locale);
    }

    @Override
    public List<String> getTextOfBook(Integer bookId, String locale, String path, Integer numberOfPage) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, path)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, numberOfPage)){
            return null;
        }
        String fileName = path+bookDAO.getUrlToTextOfBook(bookId, locale);
        String bookText = ReadFromFile.readText(fileName, numberOfPage);
        if (bookText.isEmpty()){
            return null;
        }
        List<String> text = Arrays.asList(bookText.split(NEW_LINE.toString()));
        return text;
    }

    @Override
    public Integer calcPagesCountText(Integer bookId, String locale, String path) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, path)){
            return null;
        }
        String fileName = path+bookDAO.getUrlToTextOfBook(bookId, locale);
        return ReadFromFile.calcPagesCountText(fileName);
    }

    @Override
    public List<BookPreview> getBooksPerPage(PageAttributes pageAttributes) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttributes)){
            return null;
        }
        return bookDAO.getBooksPerPage(pageAttributes);
    }

    @Override
    public Integer calcPagesCountBooks(String locale, Integer countBooksOnPage) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBooksOnPage)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return  bookDAO.calcPagesCountBooks(locale, countBooksOnPage);
    }

    @Override
    public List<Genre> getGenres(String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return null;
        }
        return bookDAO.getGenres(lang);
    }
}

package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.dao.util.ReadFromFile;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import static by.epam.java.training.web.command.util.FieldNames.*;
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
    public List<String> getTextOfBook(Integer bookId, String locale, String path, Integer page) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, path)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, page)){
            return null;
        }

        String fileName = bookDAO.getUrlToTextOfBook(bookId, locale);
        String bookText = ReadFromFile.readText(path+fileName, page);
        if (bookText.isEmpty()){
            return null;
        }
        List<String> text = Arrays.asList(bookText.split(NEW_LINE.toString()));
        return text;
    }

    @Override
    public List<BookCover> getBooksByPage(PageAttributes pageData) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageData)){
            return null;
        }
        return bookDAO.getBooksPerPage(pageData);
    }

    @Override
    public Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBooksOnOnePage)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return  bookDAO.calcPagesCountBooks(locale, countBooksOnOnePage);
    }

    @Override
    public List<Genre> getListOfGenre(String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return null;
        }
        return bookDAO.getListOfGenre(lang);
    }
}

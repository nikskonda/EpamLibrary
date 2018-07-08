package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.util.ReadFromFile;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO = DAOFactory.getBookDAO();
    private final ValidatorManager validator = new ValidatorManager();


    @Override
    public Book getBook(Integer bookId, String locale) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return bookDAO.getBook(bookId, locale);
    }

    @Override
    public List<String> getTextOfBook(Integer bookId, String locale, String path, Integer page) {
        String fileName = bookDAO.getUrlToTextOfBook(bookId, locale);
        String bookText = ReadFromFile.readText(path+fileName, page);
        if (bookText.isEmpty()){
            return null;
        }
        List<String> text = Arrays.asList(bookText.split("\n"));
        return text;
    }

    @Override
    public List<BookCover> getBooksByPage(String locale, Integer countOnPage, Integer numberOfPage) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return bookDAO.getBooksByPage(locale, countOnPage, numberOfPage);
    }

    @Override
    public Integer calcTotalPages(String locale, Integer countBooksOnOnePage) {
        if (false){
            return null;
        }
        return  bookDAO.calcTotalPages(locale, countBooksOnOnePage);
    }
}

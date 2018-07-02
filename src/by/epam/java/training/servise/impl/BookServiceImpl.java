package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private final ValidatorManager validator = new ValidatorManager();

    @Override
    public List<BookCover> getAllBooks(String locale) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return bookDAO.getAllBooks(locale);
    }

    @Override
    public Book getBook(Integer bookId, String locale) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return bookDAO.getBook(bookId, locale);
    }

    @Override
    public String getTextOfBook(Integer bookId, String locale) {
        String fileName = bookDAO.getUrlToTextOfBook(bookId, locale);

        try{
            File uploadetFile = new File("qwerty.txt");
            if (!uploadetFile.exists())
                uploadetFile.createNewFile();
        } catch (Exception ex){

        }

        return ReadFromFile.readText(fileName);
    }
}

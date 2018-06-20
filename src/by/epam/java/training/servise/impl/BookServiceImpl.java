package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.AuthorizationForm;
import by.epam.java.training.model.user.RegistrationForm;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private final ValidatorManager validator = new ValidatorManager();

    private static final String LOCALE_EN = "en";

    @Override
    public List<Book> getBooks(String locale) {

        if (locale == null || locale.equals(LOCALE_EN)){
            return bookDAO.getBooks();
        } else {
            return bookDAO.getBooks(locale);
        }

    }
}

package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.servise.BookSearchService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class BookSearchServiceImpl implements BookSearchService {
    private static final Logger logger = Logger.getLogger(BookSearchServiceImpl.class);

    private final BookSearchDAO bookSearchDAO = DAOFactory.getBookSearchDAO();
    private final ValidatorManager validator = new ValidatorManager();

    @Override
    public List<BookCover> getBooksByPage(String locale, String search, Integer countOnPage, Integer numberOfPage) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return bookSearchDAO.getBooksByPage(locale, search, countOnPage, numberOfPage);
    }

    @Override
    public Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage) {
        if (false){
            return null;
        }
        return  bookSearchDAO.calcTotalPages(locale, search, countBooksOnOnePage);
    }

}

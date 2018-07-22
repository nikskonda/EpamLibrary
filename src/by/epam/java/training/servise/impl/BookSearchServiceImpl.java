package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.servise.BookSearchService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class BookSearchServiceImpl implements BookSearchService {
    private static final Logger logger = Logger.getLogger(BookSearchServiceImpl.class);

    private final BookSearchDAO bookSearchDAO = DAOFactory.getBookSearchDAO();


    @Override
    public List<BookCover> findBooksByPage(String search, PageAttributes pageData) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageData) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }
        return bookSearchDAO.getBooksByPage(search, pageData);
    }

    @Override
    public Integer calcPagesCountBookSearchResult(String locale, String search, Integer countBooksOnOnePage) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBooksOnOnePage)){
            return null;
        }
        return  bookSearchDAO.calcTotalPages(locale, search, countBooksOnOnePage);
    }

}

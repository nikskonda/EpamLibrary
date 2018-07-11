package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.transaction.ModeratorTransaction;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.dao.transaction.TransactionFactory;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger logger = Logger.getLogger(ModeratorServiceImpl.class);

    private final ModeratorTransaction moderatorTransaction = TransactionFactory.getModeratorTransaction();


    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NEWS_VALIDATOR, defNews)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, translatedNews)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorTransaction.addNews(defNews, translatedNews, lang);
    }

    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.BOOK_VALIDATOR, defBook)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, translatedBook)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorTransaction.addBook(defBook, translatedBook, lang);
    }
}

package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.ModeratorDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.impl.ModeratorDAOImpl;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger logger = Logger.getLogger(ModeratorServiceImpl.class);

    private final ModeratorDAO moderatorDAO = DAOFactory.getModeratorDAO();


    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NEWS_VALIDATOR, defNews)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, translatedNews)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorDAO.addNews(defNews, translatedNews, lang);
    }

    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NEWS_VALIDATOR, defNews)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, translatedNews)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorDAO.editNews(defNews, translatedNews, lang);
    }

    @Override
    public boolean delNews(Integer newsId) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, newsId)){
            return false;
        }
        return moderatorDAO.delNews(newsId);
    }

    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.BOOK_VALIDATOR, defBook)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, translatedBook)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorDAO.addBook(defBook, translatedBook, lang);
    }

    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.BOOK_VALIDATOR, defBook)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, translatedBook)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        return moderatorDAO.editBook(defBook, translatedBook, lang);
    }

    @Override
    public boolean delBook(Integer bookId) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, bookId)){
            return false;
        }
        return moderatorDAO.delBook(bookId);
    }


    @Override
    public boolean isModerator(String login) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        return moderatorDAO.isModerator(login);
    }
}

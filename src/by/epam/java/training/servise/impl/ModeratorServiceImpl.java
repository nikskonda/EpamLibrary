package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.ModeratorDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger logger = Logger.getLogger(ModeratorServiceImpl.class);

    private final ModeratorDAO moderatorDAO = DAOFactory.getModeratorDAO();


    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NEWS_VALIDATOR, defNews)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, translatedNews)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }
        try{
            return moderatorDAO.addNews(defNews, translatedNews, lang);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NEWS_VALIDATOR, defNews)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, translatedNews)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }

        try{
            return moderatorDAO.editNews(defNews, translatedNews, lang);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delNews(Integer newsId) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, newsId)){
            return false;
        }

        try{
            return moderatorDAO.delNews(newsId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.BOOK_VALIDATOR, defBook)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, translatedBook)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }

        try{
            return moderatorDAO.addBook(defBook, translatedBook, lang);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.BOOK_VALIDATOR, defBook)
                || !ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, translatedBook)
                || !ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, lang)){
            return false;
        }

        try{
            return moderatorDAO.editBook(defBook, translatedBook, lang);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delBook(Integer bookId) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookId)){
            return false;
        }

        try{
            return moderatorDAO.delBook(bookId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }


    @Override
    public boolean isModerator(String login) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }

        try{
            return moderatorDAO.isModerator(login);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}

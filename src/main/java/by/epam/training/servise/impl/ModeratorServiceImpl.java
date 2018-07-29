package by.epam.training.servise.impl;

import by.epam.training.dao.DAOFactory;
import by.epam.training.dao.ModeratorDAO;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.book.Book;
import by.epam.training.model.news.News;
import by.epam.training.servise.ModeratorService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

/**
 * Implementing the {@link ModeratorService} interface for various moderator actions.
 *
 * @author  Nikita Shkonda
 */
public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger logger = Logger.getLogger(ModeratorServiceImpl.class);

    private final ModeratorDAO moderatorDAO = DAOFactory.getModeratorDAO();

    /**
     * Add new news in system.
     *
     * @param defNews - Information about news on English.
     * @param lang - Language of translated news.
     * @param translatedNews - Information about translated news on lang.
     *
     * @return <tt>true<tt/> if added was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see News
     *
     */
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

    /**
     * Edits existing news.
     *
     * @param defNews - Information about news on English.
     * @param lang - Language of translated news.
     * @param translatedNews - Information about translated news on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see News
     *
     */
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

    /**
     * Delete the news by id.
     *
     * @param newsId - Id of the news
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see News
     *
     */
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

    /**
     * Add new book in system.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if added was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
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

    /**
     * Edits existing news.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
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

    /**
     * Delete the book by id.
     *
     * @param bookId - Id of the book.
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see News
     *
     */
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

    /**
     * Return true if the login belongs to the moderator.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login belongs to the moderator.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
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

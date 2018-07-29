package by.epam.java.training.servise;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.exception.ServiceException;

/**
 * The interface defines methods that allow you to moderate information on the service.
 *
 * @author  Nikita Shkonda
 */
public interface ModeratorService {

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
    boolean addNews(News defNews, News translatedNews, String lang) throws ServiceException;

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
    boolean editNews(News defNews, News translatedNews, String lang) throws ServiceException;

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
    boolean delNews(Integer newsId) throws ServiceException;

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
    boolean addBook(Book defBook, Book translatedBook, String lang) throws ServiceException;

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
    boolean editBook(Book defBook, Book translatedBook, String lang) throws ServiceException;

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
    boolean delBook(Integer bookId) throws ServiceException;

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
    boolean isModerator(String login) throws ServiceException;
}

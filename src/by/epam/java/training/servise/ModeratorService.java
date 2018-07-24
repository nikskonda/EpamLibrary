package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;

import java.sql.SQLException;
import java.util.List;

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
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    boolean addNews(News defNews, News translatedNews, String lang) throws DAOException;

    /**
     * Edits existing news.
     *
     * @param defNews - Information about news on English.
     * @param lang - Language of translated news.
     * @param translatedNews - Information about translated news on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    boolean editNews(News defNews, News translatedNews, String lang) throws DAOException;

    /**
     * Delete the news by id.
     *
     * @param newsId - Id of the news
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    boolean delNews(Integer newsId) throws DAOException;

    /**
     * Add new book in system.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if added was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException;

    /**
     * Edits existing news.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException;

    /**
     * Delete the book by id.
     *
     * @param bookId - Id of the book.
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    boolean delBook(Integer bookId) throws DAOException;

    /**
     * Return true if the login belongs to the moderator.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login belongs to the moderator.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    boolean isModerator(String login) throws DAOException;
}

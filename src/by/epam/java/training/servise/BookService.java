package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.model.book.constituents.Genre;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with book.
 *
 * @author  Nikita Shkonda
 */
public interface BookService {

    /**
     * Return information about book by id and language
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     *
     * @return information about book.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    Book getBook(Integer bookId, String locale) throws DAOException;

    /**
     * Return text of the book for specific page and language.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     * @param path - Path to current repository.
     * @param numberOfPage - Number of page.
     *
     * @return list of paragraphs of text.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    List<String> getTextOfBook(Integer bookId, String locale, String path, Integer numberOfPage) throws DAOException;

    /**
     * Return total count of page with text.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     * @param path - Path to current repository.
     *
     * @return Total count of page with text.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountText(Integer bookId, String locale, String path) throws DAOException;

    /**
     * Return list of book previews for specified page.
     *
     * @param pageAttributes - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see BookPreview
     * @see PageAttributes
     *
     */
    List<BookPreview> getBooksPerPage(PageAttributes pageAttributes) throws DAOException;

    /**
     * Return total count of page with books.
     *
     * @param locale - Language.
     * @param countBooksOnPage - Count books on one page.
     *
     * @return Total count of page with book.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountBooks(String locale, Integer countBooksOnPage) throws DAOException;

    /**
     * Return list of genres for specified language.
     *
     * @param lang - Language.
     *
     * @return List of genres.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    List<Genre> getGenres(String lang) throws DAOException;
}


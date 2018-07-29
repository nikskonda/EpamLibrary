package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.BookPreview;
import by.epam.training.model.book.constituents.Genre;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with book in database.
 *
 * @author  Nikita Shkonda
 */
public interface BookDAO {

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
     * Return url text of the book for specific language.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     *
     * @return list of paragraphs of text.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException;

    /**
     * Return list of book previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see BookPreview
     * @see PageAttribute
     *
     */
    List<BookPreview> getBooksPerPage(PageAttribute pageAttribute) throws DAOException;

    /**
     * Return total count of page with books.
     *
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of page with book.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws DAOException;

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

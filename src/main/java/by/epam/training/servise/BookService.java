package by.epam.training.servise;

import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.BookPreview;
import by.epam.training.model.book.constituents.Genre;
import by.epam.training.servise.exception.ServiceException;

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
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
    Book getBook(Integer bookId, String locale) throws ServiceException;

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
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Book
     *
     */
    List<String> getTextOfBook(Integer bookId, String locale, String path, Integer numberOfPage) throws ServiceException;

    /**
     * Return total count of page with text.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     * @param path - Path to current repository.
     *
     * @return Total count of page with text.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    Integer calcPagesCountText(Integer bookId, String locale, String path) throws ServiceException;

    /**
     * Return list of book previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see BookPreview
     * @see PageAttribute
     *
     */
    List<BookPreview> getBooksPerPage(PageAttribute pageAttribute) throws ServiceException;

    /**
     * Return total count of page with books.
     *
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of page with book.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws ServiceException;

    /**
     * Return list of genres for specified language.
     *
     * @param lang - Language.
     *
     * @return List of genres.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    List<Genre> getGenres(String lang) throws ServiceException;
}


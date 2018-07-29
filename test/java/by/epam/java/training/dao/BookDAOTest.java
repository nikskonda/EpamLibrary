package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookDAOTest {

    private static final BookDAO dao = DAOFactory.getBookDAO();


    @Test
    public void getBook_badId_returnNull() throws DAOException {
        Integer id = 0;
        String locale = "en";

        Book book = dao.getBook(id, locale);

        assertNull(book);
    }

    @Test
    public void getBook_badLocale_returnNull()throws DAOException  {
        Integer id = 20;
        String locale = "qw";

        Book book = dao.getBook(id, locale);

        assertNull(book);
    }

    @Test
    public void getBook_GoodValue_returnBook() throws DAOException {
        Integer id = 38;
        String locale = "en";
        String expected = "MyBook1";

        Book book = dao.getBook(id, locale);

        assertEquals(expected, book.getName());
    }


    @Test
    public void getBooksByPage_badAttributes_returnNull() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(-1);
        pa.setNumberOfPage(-10);

        List<BookPreview> list = dao.getBooksPerPage(pa);

        assertNull(list);
    }

    @Test
    public void getBooksByPage_goodAttributes_returnListOfNews() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 10;

        List<BookPreview> list = dao.getBooksPerPage(pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPages_badCount_returnNull() throws DAOException {
        Integer countOnOnePage = -10;
        String locale = "en";

        Integer totalPages = dao.calcPagesCountBooks(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "qw";

        Integer totalPages = dao.calcPagesCountBooks(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "en";
        Integer expected = 2;

        Integer totalPages = dao.calcPagesCountBooks(locale, countOnOnePage);

        assertEquals(expected, totalPages);
    }

    @Test
    public void getListOfGenre_badLang_returnNull() throws DAOException{
        String lang = "qw";

        List<Genre> genres = dao.getGenres(lang);

        assertNull(genres);
    }

    @Test
    public void getListOfGenre_goodResult_returnListOfGenres() throws DAOException{
        String lang = "en";
        int expected = 12;

        List<Genre> genres = dao.getGenres(lang);

        assertEquals(expected, genres.size());
    }
}
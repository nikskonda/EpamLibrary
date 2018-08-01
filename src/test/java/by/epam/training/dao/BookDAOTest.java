package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.BookPreview;
import by.epam.training.model.book.constituents.Genre;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookDAOTest {

    private static final BookDAO dao = DAOFactory.getBookDAO();


    @Test (expected = NullPointerException.class)
    public void getBook_badId_returnEx() throws DAOException {
        Integer id = null;
        String locale = null;

        Book book = dao.getBook(id, locale);
    }

    @Test
    public void getBook_GoodValue_returnBook() throws DAOException {
        Integer id = 38;
        String locale = "en";
        String expected = "MyBook1";

        Book book = dao.getBook(id, locale);

        assertEquals(expected, book.getName());
    }


    @Test (expected = NullPointerException.class)
    public void getBooksByPage_badAttributes_returnEx() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(null);
        pa.setNumberOfPage(null);

        List<BookPreview> list = dao.getBooksPerPage(pa);
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

    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badCount_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;

        Integer totalPages = dao.calcPagesCountBooks(locale, countOnOnePage);
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
    public void getListOfGenre_goodResult_returnListOfGenres() throws DAOException{
        String lang = "en";
        int expected = 12;

        List<Genre> genres = dao.getGenres(lang);

        assertEquals(expected, genres.size());
    }
}
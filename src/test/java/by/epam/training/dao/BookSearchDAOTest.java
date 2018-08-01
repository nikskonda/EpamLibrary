package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.BookPreview;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookSearchDAOTest {

    private static final BookSearchDAO dao = DAOFactory.getBookSearchDAO();

    @Test (expected = NullPointerException.class)
    public void findBooksByPage_badPageAttribute_returnEx() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(null);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";

        List<BookPreview> list = dao.findBooksPerPage(search, pa);
    }

    @Test (expected = NullPointerException.class)
    public void findBooksByPage_badSearch_returnNull() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(null);
        pa.setNumberOfPage(null);
        pa.setLocale(null);
        String search = null;

        List<BookPreview> list = dao.findBooksPerPage(search, pa);
    }

    @Test
    public void findBooksByPage_notFound_returnEmptyList() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "cqwe";
        int expected = 0;

        List<BookPreview> list = dao.findBooksPerPage(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void findBooksByPage_goodValue_returnList() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";
        int expected = 1;

        List<BookPreview> list = dao.findBooksPerPage(search, pa);

        assertEquals(expected, list.size());
    }


    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badCount_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;
        String search = null;

        Integer totalPages = dao.calcPagesCountBookSearchResults(locale, search, countOnOnePage);
    }

    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badSearch_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;
        String search = null;

        Integer totalPages = dao.calcPagesCountBookSearchResults(locale, search, countOnOnePage);
    }

    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badLocale_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;
        String search = null;

        Integer totalPages = dao.calcPagesCountBookSearchResults(locale, search, countOnOnePage);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "en";
        String search = "collector";
        Integer expected = 1;

        Integer totalPages = dao.calcPagesCountBookSearchResults(locale, search, countOnOnePage);

        assertEquals(expected, totalPages);
    }
}
package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.BookCover;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookSearchServiceTest {

    private static final BookSearchService service = ServiceFactory.getBookSearchService();

    @Test
    public void findBooksByPage_badPageAttribute_returnNull() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(-10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";

        List<BookCover> list = service.findBooksByPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findBooksByPage_badSearch_returnNull() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "";

        List<BookCover> list = service.findBooksByPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findBooksByPage_notFound_returnEmptyList() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "cqwe";
        int expected = 0;

        List<BookCover> list = service.findBooksByPage(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void findBooksByPage_goodValue_returnList() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";
        int expected = 1;

        List<BookCover> list = service.findBooksByPage(search, pa);

        assertEquals(expected, list.size());
    }


    @Test
    public void calcTotalPages_badCount_returnNull() throws DAOException {
        Integer countOnOnePage = -10;
        String locale = "en";
        String search = "collector";

        Integer totalPages = service.calcTotalPages(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badSearch_returnNull() throws DAOException {
        Integer countOnOnePage = -10;
        String locale = "en";
        String search = "";

        Integer totalPages = service.calcTotalPages(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "qw";
        String search = "collector";

        Integer totalPages = service.calcTotalPages(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "en";
        String search = "collector";
        Integer expected = 1;

        Integer totalPages = service.calcTotalPages(locale, search, countOnOnePage);

        assertEquals(expected, totalPages);
    }
}
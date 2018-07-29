package by.epam.training.servise;

import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.BookPreview;
import by.epam.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookSearchServiceTest {

    private static final BookSearchService service = ServiceFactory.getBookSearchService();

    @Test
    public void findBooksByPage_badPageAttribute_returnNull() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(-10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";

        List<BookPreview> list = service.findBooksPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findBooksByPage_badSearch_returnNull() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "";

        List<BookPreview> list = service.findBooksPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findBooksByPage_notFound_returnEmptyList() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "cqwe";
        int expected = 0;

        List<BookPreview> list = service.findBooksPerPage(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void findBooksByPage_goodValue_returnList() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        String search = "collector";
        int expected = 1;

        List<BookPreview> list = service.findBooksPerPage(search, pa);

        assertEquals(expected, list.size());
    }


    @Test
    public void calcTotalPages_badCount_returnNull() throws ServiceException {
        Integer countOnOnePage = -10;
        String locale = "en";
        String search = "collector";

        Integer totalPages = service.calcPagesCountBookSearchResult(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badSearch_returnNull() throws ServiceException {
        Integer countOnOnePage = -10;
        String locale = "en";
        String search = "";

        Integer totalPages = service.calcPagesCountBookSearchResult(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws ServiceException {
        Integer countOnOnePage = 10;
        String locale = "qw";
        String search = "collector";

        Integer totalPages = service.calcPagesCountBookSearchResult(locale, search, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws ServiceException {
        Integer countOnOnePage = 10;
        String locale = "en";
        String search = "collector";
        Integer expected = 1;

        Integer totalPages = service.calcPagesCountBookSearchResult(locale, search, countOnOnePage);

        assertEquals(expected, totalPages);
    }
}
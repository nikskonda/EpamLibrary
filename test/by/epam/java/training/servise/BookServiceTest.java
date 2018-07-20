package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.constituents.Genre;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private static final BookService service = ServiceFactory.getBookService();


    @Test
    public void getBook_badId_returnNull() throws DAOException {
        Integer id = 0;
        String locale = "en";

        Book book = service.getBook(id, locale);

        assertNull(book);
    }

    @Test
    public void getBook_badLocale_returnNull()throws DAOException  {
        Integer id = 20;
        String locale = "qw";

        Book book = service.getBook(id, locale);

        assertNull(book);
    }

    @Test
    public void getBook_GoodValue_returnBook() throws DAOException {
        Integer id = 38;
        String locale = "en";
        String expected = "MyBook1";

        Book book = service.getBook(id, locale);

        assertEquals(expected, book.getName());
    }


    @Test
    public void getBooksByPage_badAttributes_returnNull() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(-1);
        pa.setNumberOfPage(-10);

        List<BookCover> list = service.getBooksByPage(pa);

        assertNull(list);
    }

    @Test
    public void getBooksByPage_goodAttributes_returnListOfNews() throws DAOException {
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 10;

        List<BookCover> list = service.getBooksByPage(pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPages_badCount_returnNull() throws DAOException {
        Integer countOnOnePage = -10;
        String locale = "en";

        Integer totalPages = service.calcTotalPages(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "qw";

        Integer totalPages = service.calcTotalPages(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "en";
        Integer expected = 2;

        Integer totalPages = service.calcTotalPages(locale, countOnOnePage);

        assertEquals(expected, totalPages);
    }

    @Test
    public void getListOfGenre_badLang_returnNull() throws DAOException{
        String lang = "qw";

        List<Genre> genres = service.getListOfGenre(lang);

        assertNull(genres);
    }

    @Test
    public void getListOfGenre_goodResult_returnListOfGenres() throws DAOException{
        String lang = "en";
        int expected = 12;

        List<Genre> genres = service.getListOfGenre(lang);

        assertEquals(expected, genres.size());
    }
}
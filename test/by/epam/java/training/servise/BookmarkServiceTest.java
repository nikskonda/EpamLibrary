package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookmarkServiceTest {

    private static final BookmarkService service = ServiceFactory.getBookmarkService();

    @Test
    public void getBookmark_badBookmark_returnNull() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);

        Integer page = service.getBookmark(bookmark);

        assertNull(page);
    }

    @Test
    public void getBookmark_goodBookmark_returnPageNumber() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        Integer expected = 1;

        Integer page = service.getBookmark(bookmark);

        assertEquals(expected, page);
    }

    @Test
    public void setBookmark_badBookmark_returnFalse() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);
        bookmark.setPageNumber(-100);

        boolean result = service.setBookmark(bookmark);

        assertFalse(result);
    }

    @Test
    public void setBookmark_goodBookmark_returnTrue() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        bookmark.setPageNumber(100);

        boolean result = service.setBookmark(bookmark);

        assertTrue(result);

        service.deleteBookmark(bookmark);
    }

    @Test
    public void getListOfBooksWithBookmark_badId_returnNull() throws DAOException{
        Integer id = -10;
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");

        List<Book> list = service.getListOfBooksWithBookmark(id, pa);

        assertNull(list);
    }

    @Test
    public void getListOfBooksWithBookmark_badPageAtt_returnNull() throws DAOException{
        Integer id = 1;
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(-10);
        pa.setNumberOfPage(-1);
        pa.setLocale("qwe");

        List<Book> list = service.getListOfBooksWithBookmark(id, pa);

        assertNull(list);
    }

    @Test
    public void getListOfBooksWithBookmark_noBookmarks_returnEmptyList() throws DAOException{
        Integer id = 20;
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 0;

        List<Book> list = service.getListOfBooksWithBookmark(id, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void getListOfBooksWithBookmark_GoodValue_returnList() throws DAOException{
        Integer id = 1;
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(2);
        pa.setNumberOfPage(2);
        pa.setLocale("en");
        int expected = 1;

        List<Book> list = service.getListOfBooksWithBookmark(id, pa);

        assertEquals(expected, list.size());
    }

//    @Test(expected = DAOException.class)
//    public void getListOfBooksWithBookmark_BadId_returnException() throws DAOException{
//        Integer id = 100;
//        PageAttributes pa = new PageAttributes();
//        pa.setCountOnPage(10);
//        pa.setNumberOfPage(1);
//        pa.setLocale("en");
//
//        List<Book> list = service.getListOfBooksWithBookmark(id, pa);
//    }

    @Test
    public void deleteBookmark_badBookMark_returnFalse() throws DAOException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-17);
        bookmark.setPageNumber(-100);

        boolean result = service.deleteBookmark(bookmark);

        assertFalse(result);
    }

    @Test
    public void deleteBookmark_goodValue_deleted() throws DAOException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(20);
        bookmark.setPageNumber(100);

        Integer id = 1;
        PageAttributes pa = new PageAttributes();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 3;

        service.setBookmark(bookmark);
        assertEquals(expected+1, service.getListOfBooksWithBookmark(id, pa).size());

        service.deleteBookmark(bookmark);
        assertEquals(expected, service.getListOfBooksWithBookmark(id, pa).size());
    }
}
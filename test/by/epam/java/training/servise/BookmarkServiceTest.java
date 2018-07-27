package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookmarkServiceTest {

    private static final BookmarkService service = ServiceFactory.getBookmarkService();

    @Test
    public void getBookmark_badBookmark_returnNull() throws ServiceException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);

        Integer page = service.getBookmark(bookmark);

        assertNull(page);
    }

    @Test
    public void getBookmark_goodBookmark_returnPageNumber() throws ServiceException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        Integer expected = 1;

        Integer page = service.getBookmark(bookmark);

        assertEquals(expected, page);
    }

    @Test
    public void setBookmark_badBookmark_returnFalse() throws ServiceException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);
        bookmark.setPageNumber(-100);

        boolean result = service.setBookmark(bookmark);

        assertFalse(result);
    }

    @Test
    public void setBookmark_goodBookmark_returnTrue() throws ServiceException {
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
    public void getListOfBooksWithBookmark_badId_returnNull() throws ServiceException{
        Integer id = -10;
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");

        List<Book> list = service.getBooksWithBookmark(id, pa);

        assertNull(list);
    }

    @Test
    public void getListOfBooksWithBookmark_badPageAtt_returnNull() throws ServiceException{
        Integer id = 1;
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(-10);
        pa.setNumberOfPage(-1);
        pa.setLocale("qwe");

        List<Book> list = service.getBooksWithBookmark(id, pa);

        assertNull(list);
    }

    @Test
    public void getListOfBooksWithBookmark_noBookmarks_returnEmptyList() throws ServiceException{
        Integer id = 20;
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 0;

        List<Book> list = service.getBooksWithBookmark(id, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void getListOfBooksWithBookmark_GoodValue_returnList() throws ServiceException{
        Integer id = 1;
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(2);
        pa.setNumberOfPage(2);
        pa.setLocale("en");
        int expected = 1;

        List<Book> list = service.getBooksWithBookmark(id, pa);

        assertEquals(expected, list.size());
    }

//    @Test(expected = DAOException.class)
//    public void getListOfBooksWithBookmark_BadId_returnException() throws ServiceException{
//        Integer id = 100;
//        PageAttribute pa = new PageAttribute();
//        pa.setCountOnPage(10);
//        pa.setNumberOfPage(1);
//        pa.setLocale("en");
//
//        List<Book> list = service.getBooksWithBookmark(id, pa);
//    }

    @Test
    public void deleteBookmark_badBookMark_returnFalse() throws ServiceException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-17);
        bookmark.setPageNumber(-100);

        boolean result = service.deleteBookmark(bookmark);

        assertFalse(result);
    }

    @Test
    public void deleteBookmark_goodValue_deleted() throws ServiceException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(20);
        bookmark.setPageNumber(100);

        Integer id = 1;
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int expected = 3;

        service.setBookmark(bookmark);
        assertEquals(expected+1, service.getBooksWithBookmark(id, pa).size());

        service.deleteBookmark(bookmark);
        assertEquals(expected, service.getBooksWithBookmark(id, pa).size());
    }
}
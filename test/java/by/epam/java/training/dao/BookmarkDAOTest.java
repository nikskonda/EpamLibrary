package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.BookmarkService;
import by.epam.java.training.servise.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookmarkDAOTest {

    private static final BookmarkDAO dao = DAOFactory.getBookmarkDAO();

    @Test
    public void getBookmark_badBookmark_returnNull() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);

        Integer page = dao.getBookmark(bookmark);

        assertNull(page);
    }

    @Test
    public void getBookmark_goodBookmark_returnPageNumber() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        Integer expected = 1;

        Integer page = dao.getBookmark(bookmark);

        assertEquals(expected, page);
    }

    @Test
    public void setBookmark_badBookmark_returnFalse() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-10);
        bookmark.setPageNumber(-100);

        boolean result = dao.setBookmark(bookmark);

        assertFalse(result);
    }

    @Test
    public void setBookmark_goodBookmark_returnTrue() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        bookmark.setPageNumber(100);

        boolean result = dao.setBookmark(bookmark);

        assertTrue(result);

        dao.deleteBookmark(bookmark);
    }



    @Test
    public void deleteBookmark_badBookMark_returnFalse() throws DAOException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(0);
        bookmark.setBookId(-17);
        bookmark.setPageNumber(-100);

        boolean result = dao.deleteBookmark(bookmark);

        assertFalse(result);
    }
}
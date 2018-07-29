package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.book.Bookmark;
import org.junit.Test;

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
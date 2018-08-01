package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.book.Bookmark;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookmarkDAOTest {

    private static final BookmarkDAO dao = DAOFactory.getBookmarkDAO();

    @Test (expected = NullPointerException.class)
    public void getBookmark_badBookmark_returnEx() throws DAOException {
        Bookmark bookmark = null;

        Integer page = dao.getBookmark(bookmark);
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

    @Test (expected = NullPointerException.class)
    public void setBookmark_badBookmark_returnEx() throws DAOException {
        Bookmark bookmark = null;

        boolean result = dao.setBookmark(bookmark);
    }


    @Test
    public void setBookmark_goodBookmark_returnTrue() throws DAOException {
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale("en");
        bookmark.setUserId(1);
        bookmark.setBookId(17);
        bookmark.setPageNumber(100);

        boolean result = dao.setBookmark(bookmark);

        dao.deleteBookmark(bookmark);
    }



    @Test (expected = NullPointerException.class)
    public void deleteBookmark_badBookMark_returnEx() throws DAOException{
        Bookmark bookmark = new Bookmark();
        bookmark.setLocale(null);
        bookmark.setUserId(null);
        bookmark.setBookId(null);
        bookmark.setPageNumber(-100);

        boolean result = dao.deleteBookmark(bookmark);

    }
}
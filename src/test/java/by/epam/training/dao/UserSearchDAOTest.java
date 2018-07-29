package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserSearchDAOTest {

    private static final UserSearchDAO dao = DAOFactory.getUserSearchDAO();


    @Test
    public void findUsersByPages_badSearch_returnNull() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(10);
        pa.setCountOnPage(100);
        String search = "";

        List<User> list = dao.findUsersPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_badValue_returnNull() throws DAOException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);
        String search = "admin";

        List<User> list = dao.findUsersPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_goodValue_returnList() throws DAOException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        String search = "admin";
        int expected = 1;

        List<User> list = dao.findUsersPerPage(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badSearch_returnNull() throws DAOException{
        Integer count = 10;
        String search = "";

        Integer pages = dao.calcPagesCountUserSearchResults(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badValue_returnNull() throws DAOException{
        Integer count = -10;
        String search = "admin";

        Integer pages = dao.calcPagesCountUserSearchResults(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_goodValue_returnPages() throws DAOException{
        Integer count = 10;
        String search = "admin";
        Integer expected = 1;

        Integer pages = dao.calcPagesCountUserSearchResults(search, count);

        assertEquals(expected, pages);
    }
}
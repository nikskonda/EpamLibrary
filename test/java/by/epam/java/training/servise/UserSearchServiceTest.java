package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserSearchServiceTest {

    UserSearchService service = ServiceFactory.getUserSearchService();


    @Test
    public void findUsersByPages_badSearch_returnNull() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(10);
        pa.setCountOnPage(100);
        String search = "";

        List<User> list = service.findUsersPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_badValue_returnNull() throws ServiceException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);
        String search = "admin";

        List<User> list = service.findUsersPerPage(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_goodValue_returnList() throws ServiceException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        String search = "admin";
        int expected = 1;

        List<User> list = service.findUsersPerPage(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badSearch_returnNull() throws ServiceException{
        Integer count = 10;
        String search = "";

        Integer pages = service.calcPagesCountUserSearchResults(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badValue_returnNull() throws ServiceException{
        Integer count = -10;
        String search = "admin";

        Integer pages = service.calcPagesCountUserSearchResults(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_goodValue_returnPages() throws ServiceException{
        Integer count = 10;
        String search = "admin";
        Integer expected = 1;

        Integer pages = service.calcPagesCountUserSearchResults(search, count);

        assertEquals(expected, pages);
    }
}
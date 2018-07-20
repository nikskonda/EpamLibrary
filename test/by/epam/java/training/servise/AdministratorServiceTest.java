package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorServiceTest {
    private static final AdministratorService service = ServiceFactory.getAdministratorService();

    @Test
    public void isAdministrator_badLogin_returnFalse() throws DAOException {
        String login = "";

        boolean result = service.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_moderLogin_returnFalse() throws DAOException {
        String login = "qwerty";

        boolean result = service.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_adminLogin_returnTrue() throws DAOException {
        String login = "admin";

        boolean result = service.isAdministrator(login);

        assertTrue(result);
    }

    @Test
    public void getUser_badId_returnNull() throws DAOException{
        Integer id = -10;

        User user = service.getUser(id);

        assertNull(user);
    }

    @Test
    public void getUser_goodValue_returnUser() throws DAOException{
        Integer id = 1;
        String expected = "admin";

        User user = service.getUser(id);

        assertEquals(expected, user.getLogin());
    }

    @Test
    public void getUsersByPages_badValue_returnNull() throws DAOException{
        PageAttributes pa = new PageAttributes();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);

        List<User> list = service.getUsersByPages(pa);

        assertNull(list);
    }

    @Test
    public void getUsersByPages_goodValue_returnList() throws DAOException{
        PageAttributes pa = new PageAttributes();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        int expected = 10;

        List<User> list = service.getUsersByPages(pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsers_badValue_returnNull() throws DAOException{
        Integer count = -10;

        Integer pages = service.calcTotalPagesWithUsers(count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsers_goodValue_returnPages() throws DAOException{
        Integer count = 10;
        Integer expected = 2;

        Integer pages = service.calcTotalPagesWithUsers(count);

        assertEquals(expected, pages);
    }

    @Test
    public void findUsersByPages_badSearch_returnNull() throws DAOException{
        PageAttributes pa = new PageAttributes();
        pa.setLocale("en");
        pa.setNumberOfPage(10);
        pa.setCountOnPage(100);
        String search = "";

        List<User> list = service.FindUsersByPages(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_badValue_returnNull() throws DAOException{
        PageAttributes pa = new PageAttributes();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);
        String search = "admin";

        List<User> list = service.FindUsersByPages(search, pa);

        assertNull(list);
    }

    @Test
    public void findUsersByPages_goodValue_returnList() throws DAOException{
        PageAttributes pa = new PageAttributes();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        String search = "admin";
        int expected = 1;

        List<User> list = service.FindUsersByPages(search, pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badSearch_returnNull() throws DAOException{
        Integer count = 10;
        String search = "";

        Integer pages = service.calcTotalPagesWithUsersSearch(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_badValue_returnNull() throws DAOException{
        Integer count = -10;
        String search = "admin";

        Integer pages = service.calcTotalPagesWithUsersSearch(search, count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsersSearch_goodValue_returnPages() throws DAOException{
        Integer count = 10;
        String search = "admin";
        Integer expected = 1;

        Integer pages = service.calcTotalPagesWithUsersSearch(search, count);

        assertEquals(expected, pages);
    }

    @Test
    public void getRoles() throws DAOException{
        int expected = 3;

        List<Role> roles = service.getRoles();

        assertEquals(expected, roles.size());
    }

    @Test
    public void changeRole_badId_returnFalse() throws DAOException {
        String role = "Administrator";
        Integer id = -20;

        boolean result = service.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_badRole_returnFalse() throws DAOException {
        String role = "";
        Integer id = -20;

        boolean result = service.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_goodValue_returnTrue() throws DAOException {
        String role = "Moderator";
        String user = "User";
        Integer id = 20;

        boolean result = service.changeRole(id, role);
        assertTrue(result);

        result = service.changeRole(id, user);
        assertTrue(result);
    }

    @Test
    public void deleteUser_badId_returnFalse() throws DAOException {
        Integer id = -100;

        boolean result = service.deleteUser(id);
        assertFalse(result);
    }
}
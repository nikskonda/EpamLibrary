package by.epam.java.training.servise;

import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorServiceTest {
    private static final AdministratorService service = ServiceFactory.getAdministratorService();

    @Test
    public void isAdministrator_badLogin_returnFalse() throws ServiceException {
        String login = "";

        boolean result = service.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_moderLogin_returnFalse() throws ServiceException {
        String login = "qwerty";

        boolean result = service.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_adminLogin_returnTrue() throws ServiceException {
        String login = "admin";

        boolean result = service.isAdministrator(login);

        assertTrue(result);
    }

    @Test
    public void getUser_badId_returnNull() throws ServiceException{
        Integer id = -10;

        User user = service.getUser(id);

        assertNull(user);
    }

    @Test
    public void getUser_goodValue_returnUser() throws ServiceException{
        Integer id = 1;
        String expected = "admin";

        User user = service.getUser(id);

        assertEquals(expected, user.getLogin());
    }

    @Test
    public void getUsersByPages_badValue_returnNull() throws ServiceException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);

        List<User> list = service.getUsersPerPage(pa);

        assertNull(list);
    }

    @Test
    public void getUsersByPages_goodValue_returnList() throws ServiceException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        int expected = 10;

        List<User> list = service.getUsersPerPage(pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsers_badValue_returnNull() throws ServiceException{
        Integer count = -10;

        Integer pages = service.calcPagesCountUsers(count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsers_goodValue_returnPages() throws ServiceException{
        Integer count = 10;
        Integer expected = 2;

        Integer pages = service.calcPagesCountUsers(count);

        assertEquals(expected, pages);
    }

    @Test
    public void getRoles() throws ServiceException{
        int expected = 3;

        List<Role> roles = service.getRoles();

        assertEquals(expected, roles.size());
    }

    @Test
    public void changeRole_badId_returnFalse() throws ServiceException {
        String role = "Administrator";
        Integer id = -20;

        boolean result = service.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_badRole_returnFalse() throws ServiceException {
        String role = "";
        Integer id = -20;

        boolean result = service.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_goodValue_returnTrue() throws ServiceException {
        String role = "Moderator";
        String user = "User";
        Integer id = 20;

        boolean result = service.changeRole(id, role);
        assertTrue(result);

        result = service.changeRole(id, user);
        assertTrue(result);
    }

    @Test
    public void deleteUser_badId_returnFalse() throws ServiceException {
        Integer id = -100;

        boolean result = service.deleteUser(id);
        assertFalse(result);
    }
}
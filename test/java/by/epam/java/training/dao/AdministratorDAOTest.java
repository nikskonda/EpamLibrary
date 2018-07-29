package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorDAOTest {
    private static final AdministratorDAO dao = DAOFactory.getAdministratorDAO();

    @Test
    public void isAdministrator_badLogin_returnFalse() throws DAOException {
        String login = "";

        boolean result = dao.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_moderLogin_returnFalse() throws DAOException {
        String login = "qwerty";

        boolean result = dao.isAdministrator(login);

        assertFalse(result);
    }

    @Test
    public void isAdministrator_adminLogin_returnTrue() throws DAOException {
        String login = "admin";

        boolean result = dao.isAdministrator(login);

        assertTrue(result);
    }

    @Test
    public void getUser_badId_returnNull() throws DAOException{
        Integer id = -10;

        User user = dao.getUser(id);

        assertNull(user);
    }

    @Test
    public void getUser_goodValue_returnUser() throws DAOException{
        Integer id = 1;
        String expected = "admin";

        User user = dao.getUser(id);

        assertEquals(expected, user.getLogin());
    }

    @Test
    public void getUsersByPages_badValue_returnNull() throws DAOException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(-10);
        pa.setCountOnPage(-100);

        List<User> list = dao.getUsersPerPage(pa);

        assertNull(list);
    }

    @Test
    public void getUsersByPages_goodValue_returnList() throws DAOException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(10);
        int expected = 10;

        List<User> list = dao.getUsersPerPage(pa);

        assertEquals(expected, list.size());
    }

    @Test
    public void calcTotalPagesWithUsers_badValue_returnNull() throws DAOException{
        Integer count = -10;

        Integer pages = dao.calcPagesCountUsers(count);

        assertNull(pages);
    }

    @Test
    public void calcTotalPagesWithUsers_goodValue_returnPages() throws DAOException{
        Integer count = 10;
        Integer expected = 2;

        Integer pages = dao.calcPagesCountUsers(count);

        assertEquals(expected, pages);
    }

    @Test
    public void getRoles() throws DAOException{
        int expected = 3;

        List<Role> roles = dao.getRoles();

        assertEquals(expected, roles.size());
    }

    @Test
    public void changeRole_badId_returnFalse() throws DAOException {
        String role = "Administrator";
        Integer id = -20;

        boolean result = dao.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_badRole_returnFalse() throws DAOException {
        String role = "";
        Integer id = -20;

        boolean result = dao.changeRole(id, role);

        assertFalse(result);
    }

    @Test
    public void changeRole_goodValue_returnTrue() throws DAOException {
        String role = "Moderator";
        String user = "User";
        Integer id = 20;

        boolean result = dao.changeRole(id, role);
        assertTrue(result);

        result = dao.changeRole(id, user);
        assertTrue(result);
    }

    @Test
    public void deleteUser_badId_returnFalse() throws DAOException {
        Integer id = -100;

        boolean result = dao.deleteUser(id);
        assertFalse(result);
    }
}
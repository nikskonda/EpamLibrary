package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.User;
import by.epam.training.model.user.constituents.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorDAOTest {
    private static final AdministratorDAO dao = DAOFactory.getAdministratorDAO();

    @Test
    public void isAdministrator_adminLogin_returnTrue() throws DAOException {
        String login = "admin";

        boolean result = dao.isAdministrator(login);

        assertTrue(result);
    }

    @Test (expected = NullPointerException.class)
    public void getUser_badId_returnEx() throws DAOException{
        Integer id = null;

        User user = dao.getUser(id);
    }

    @Test
    public void getUser_goodValue_returnUser() throws DAOException{
        Integer id = 1;
        String expected = "admin";

        User user = dao.getUser(id);

        assertEquals(expected, user.getLogin());
    }

    @Test (expected = NullPointerException.class)
    public void getUsersByPages_badValue_returnEx() throws DAOException{
        PageAttribute pa = new PageAttribute();
        pa.setLocale(null);
        pa.setNumberOfPage(null);
        pa.setCountOnPage(10);

        List<User> list = dao.getUsersPerPage(pa);
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

    @Test (expected = NullPointerException.class)
    public void calcTotalPagesWithUsers_badValue_returnEx() throws DAOException{
        Integer count = null;

        Integer pages = dao.calcPagesCountUsers(count);
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
    public void changeRole_goodValue_returnTrue() throws DAOException {
        String role = "Moderator";
        String user = "User";
        Integer id = 20;

        boolean result = dao.changeRole(id, role);
        assertTrue(result);

        result = dao.changeRole(id, user);
        assertTrue(result);
    }

    @Test (expected = NullPointerException.class)
    public void deleteUser_badId_returnEx() throws DAOException {
        Integer id = null;

        boolean result = dao.deleteUser(id);
    }
}
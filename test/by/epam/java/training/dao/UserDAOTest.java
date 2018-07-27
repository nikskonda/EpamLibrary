package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.util.EncriptionMD5;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    private static final UserDAO dao = DAOFactory.getUserDAO();

    @Test
    public void isExistUser_badValue_returnFalse() throws DAOException {
        SignInForm signin = new SignInForm();


        boolean result = dao.isUserExist(signin);

        assertFalse(result);
    }

    @Test
    public void isExistUser_goodValue_returnTrue() throws DAOException {
        SignInForm signin = new SignInForm();
        signin.setLogin("admin");
        signin.setPassword(EncriptionMD5.encrypt("admin1"));

        boolean result = dao.isUserExist(signin);

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
    public void addUser_badValue_returnNull() throws DAOException {
        SignUpForm signUpForm = initUser();
        signUpForm.setLogin(null);

        ActiveUser user = dao.addUser(signUpForm);

        assertNull(user);

    }

    @Test
    public void addUser_goodValue_returnUser() throws DAOException {
        SignUpForm signUpForm = initUser();
        String expected = "qwe123qwe";
        signUpForm.setLogin(expected);

        ActiveUser user = dao.addUser(signUpForm);

        assertEquals(expected, user.getLogin());

        AdministratorDAO ad = DAOFactory.getAdministratorDAO();
        ad.deleteUser(user.getId());
    }

    @Test
    public void getActiveUser_badLogin_returnNull() throws DAOException{
        String login = "";

        ActiveUser user = dao.getActiveUser(login);

        assertNull(user);
    }

    @Test
    public void getActiveUser_goodLogin_returnUser() throws DAOException{
        String login = "admin";
        Integer expected = 1;

        ActiveUser user = dao.getActiveUser(login);

        assertEquals(expected, user.getId());
    }

    @Test
    public void isFreeLogin_badLogin_returnFalse() throws DAOException{
        String login = "";

        boolean result = dao.isFreeLogin(login);

        assertFalse(result);
    }

    @Test
    public void isFreeLogin_busyLogin_returnFalse() throws DAOException{
        String login = "admin";

        boolean result = dao.isFreeLogin(login);

        assertFalse(result);
    }

    @Test
    public void isFreeLogin_freeLogin_returnTrue() throws DAOException{
        String login = "qwe123qwe";

        boolean result = dao.isFreeLogin(login);

        assertTrue(result);
    }


    private SignUpForm initUser(){
        SignUpForm signUpForm = new SignUpForm();

        signUpForm.setLogin("qwe123qwe");
        signUpForm.setEmail("mail@mail.con");
        signUpForm.setFirstName("Name");
        signUpForm.setLastName("LastName");
        signUpForm.setPassword(EncriptionMD5.encrypt("password"));
        signUpForm.setConfirmPassword(EncriptionMD5.encrypt("password"));

        return signUpForm;
    }

}
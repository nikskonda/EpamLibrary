package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.web.command.impl.user.SignUp;
import by.epam.java.training.web.util.EncriptionMD5;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private static final UserService service = ServiceFactory.getUserService();

    @Test
    public void isExistUser_badValue_returnFalse() throws DAOException {
        SignInForm signin = new SignInForm();


        boolean result = service.isExistUser(signin);

        assertFalse(result);
    }

    @Test
    public void isExistUser_goodValue_returnTrue() throws DAOException {
        SignInForm signin = new SignInForm();
        signin.setLogin("admin");
        signin.setPassword(EncriptionMD5.encrypt("admin1"));

        boolean result = service.isExistUser(signin);

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
    public void addUser_badValue_returnNull() throws DAOException {
        SignUpForm signUpForm = initUser();
        signUpForm.setLogin(null);

        ActiveUser user = service.addUser(signUpForm);

        assertNull(user);

    }

    @Test
    public void addUser_goodValue_returnUser() throws DAOException {
        SignUpForm signUpForm = initUser();
        String expected = "qwe123qwe";
        signUpForm.setLogin(expected);

        ActiveUser user = service.addUser(signUpForm);

        assertEquals(expected, user.getLogin());

        AdministratorService as = ServiceFactory.getAdministratorService();
        as.deleteUser(user.getId());
    }

    @Test
    public void getActiveUser_badLogin_returnNull() throws DAOException{
        String login = "";

        ActiveUser user = service.getActiveUser(login);

        assertNull(user);
    }

    @Test
    public void getActiveUser_goodLogin_returnUser() throws DAOException{
        String login = "admin";
        Integer expected = 1;

        ActiveUser user = service.getActiveUser(login);

        assertEquals(expected, user.getId());
    }

    @Test
    public void isFreeLogin_badLogin_returnFalse() throws DAOException{
        String login = "";

        boolean result = service.isFreeLogin(login);

        assertFalse(result);
    }

    @Test
    public void isFreeLogin_busyLogin_returnFalse() throws DAOException{
        String login = "admin";

        boolean result = service.isFreeLogin(login);

        assertFalse(result);
    }

    @Test
    public void isFreeLogin_freeLogin_returnTrue() throws DAOException{
        String login = "qwe123qwe";

        boolean result = service.isFreeLogin(login);

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
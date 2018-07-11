package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.Page.*;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class SignUp extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignUp.class);

    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";

    private static final String ERROR_EXIST = "error_exist";
    private static final String SIGN_UP_FORM = "signUpForm";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            SignUpForm signUpForm = new SignUpForm();


            signUpForm.setLogin(request.getParameter(LOGIN));
            signUpForm.setPassword(EncriptionMD5.encrypt(request.getParameter(PASSWORD)));
            signUpForm.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
            signUpForm.setEmail(request.getParameter(EMAIL));
            signUpForm.setFirstName(request.getParameter(FIRST_NAME));
            signUpForm.setLastName(request.getParameter(LAST_NAME));

            UserService userService = ServiceFactory.getUserService();

            if (!userService.isFreeLogin(signUpForm.getLogin())) {
                request.setAttribute(SIGN_UP_FORM, signUpForm);
                request.setAttribute(ERROR_EXIST, true);
                forward(request, response, SIGN_UP);
                return;
            }
            request.setAttribute(ERROR_EXIST, false);
            ActiveUser user = userService.addUser(signUpForm);
            if (user == null) {
                redirect(response, ERROR);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(USER, user);

            executeLastAction(request, response);
        } catch (DAOException ex){
            logger.warn("Problem with database", ex);
            request.setAttribute(ERROR_DATABASE, true);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }
    }

}

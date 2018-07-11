package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.CommandManager;
import by.epam.java.training.web.command.CommandName;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;
import static by.epam.java.training.web.command.Page.*;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class SignIn extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignIn.class);



    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String USER = "user";
    private static final String ERROR_EXIST = "error_exist";
    private static final String SIGN_IN_FORM = "signInForm";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            SignInForm authForm = new SignInForm();

            authForm.setLogin(request.getParameter(LOGIN));
            authForm.setPassword(EncriptionMD5.encrypt(request.getParameter(PASSWORD)));

            UserService userService = ServiceFactory.getUserService();

            if (!userService.isExistLoginAndPassword(authForm)){
                request.setAttribute(ERROR_EXIST, true);
                request.setAttribute(SIGN_IN_FORM, authForm);
                forward(request, response, SIGN_IN);
                return;
            }
            request.setAttribute(ERROR_EXIST, false);
            ActiveUser user = userService.getActiveUser(authForm.getLogin());
            HttpSession session = request.getSession();
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

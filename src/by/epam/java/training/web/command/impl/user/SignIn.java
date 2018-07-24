package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstants.*;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class SignIn extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignIn.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            UserService userService = ServiceFactory.getUserService();
            HttpSession session = request.getSession();
            SignInForm authForm = new SignInForm();
            authForm.setLogin(request.getParameter(LOGIN));
            authForm.setPassword(EncriptionMD5.encrypt(request.getParameter(PASSWORD)));

            if (!userService.isUserExist(authForm)){
                request.setAttribute(ERROR_EXIST, true);
                request.setAttribute(SIGN_IN_FORM, authForm);
                forward(request, response, SIGN_IN);
                return;
            }

            request.setAttribute(ERROR_EXIST, false);
            ActiveUser user = userService.getActiveUser(authForm.getLogin());
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

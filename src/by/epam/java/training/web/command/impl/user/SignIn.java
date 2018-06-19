package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.ActiveUser;
import by.epam.java.training.model.AuthorizationForm;
import by.epam.java.training.model.User;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.impl.UserServiceImpl;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages.*;

public class SignIn extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignIn.class);


    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_EXIST = "error_exist";

    private static boolean TRUE = true;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            AuthorizationForm authForm = new AuthorizationForm();

            authForm.setLogin(request.getParameter(LOGIN));
            authForm.setPassword(request.getParameter(PASSWORD));

            UserService userService = new UserServiceImpl();

            if (!userService.isExistLoginAndPassword(authForm)){
                request.setAttribute(ERROR_EXIST, TRUE);
                forward(request, response, SIGN_IN.getPage());
                return;
            }

            ActiveUser user = userService.getActiveUser(authForm.getLogin());
            HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            redirect(response, START_PAGE.getPage());
            return;
        } catch (IOException ex){

        } catch (Exception ex) {

        }



    }
}

package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class SignUp extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignUp.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            UserService userService = ServiceFactory.getUserService();
            SignUpForm signUpForm = new SignUpForm();
            signUpForm.setLogin(request.getParameter(LOGIN));
            signUpForm.setPassword(EncriptionMD5.encrypt(request.getParameter(PASSWORD)));
            signUpForm.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
            signUpForm.setEmail(request.getParameter(EMAIL));
            signUpForm.setFirstName(request.getParameter(FIRST_NAME));
            signUpForm.setLastName(request.getParameter(LAST_NAME));

            ActiveUser user = userService.addUser(signUpForm);

            session.setAttribute(USER, user);

            executeLastAction(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }

}
package by.epam.training.web.command.impl.user;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignUpForm;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.PageConstant;
import by.epam.training.web.util.EncriptionMD5;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to register a user.
 *
 * @author  Nikita Shkonda
 */
public class SignUp extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignUp.class);


    /**
     * Registers a new user.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            UserService userService = ServiceFactory.getUserService();
            SignUpForm signUpForm = new SignUpForm();
            signUpForm.setLogin(request.getParameter(FieldNameConstant.LOGIN));
            signUpForm.setPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD)));
            signUpForm.setConfirmPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.CONFIRM_PASSWORD)));
            signUpForm.setEmail(request.getParameter(FieldNameConstant.EMAIL));
            signUpForm.setFirstName(request.getParameter(FieldNameConstant.FIRST_NAME));
            signUpForm.setLastName(request.getParameter(FieldNameConstant.LAST_NAME));

            ActiveUser user = userService.addUser(signUpForm);

            if (user == null){
                forward(request, response, PageConstant.ERROR);
                return;
            }

            session.setAttribute(FieldNameConstant.USER, user);

            executeLastAction(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }

}

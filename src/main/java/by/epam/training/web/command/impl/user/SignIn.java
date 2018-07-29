package by.epam.training.web.command.impl.user;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.util.EncriptionMD5;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to authorize the user.
 *
 * @author  Nikita Shkonda
 */
public class SignIn extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(SignIn.class);

    /**
     * Authorizes the user.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            UserService userService = ServiceFactory.getUserService();
            HttpSession session = request.getSession();
            SignInForm authForm = new SignInForm();
            authForm.setLogin(request.getParameter(FieldNameConstant.LOGIN));
            authForm.setPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD)));

            if (!userService.isUserExist(authForm)){
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                request.setAttribute(FieldNameConstant.SIGN_IN_FORM, authForm);
                forward(request, response, PageConstant.SIGN_IN);
                return;
            }

            request.setAttribute(FieldNameConstant.ERROR_EXIST, false);
            ActiveUser user = userService.getActiveUser(authForm.getLogin());
            session.setAttribute(FieldNameConstant.USER, user);

            executeLastAction(request, response);

        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}

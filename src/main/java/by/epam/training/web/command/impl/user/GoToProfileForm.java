package by.epam.training.web.command.impl.user;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.ProfileForm;
import by.epam.training.model.user.User;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to go to the profile constructor.
 *
 * @author  Nikita Shkonda
 */
public class GoToProfileForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToProfileForm.class);

    /**
     * Go to profile.
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
            rememberLastAction(request);

            UserService userService = ServiceFactory.getUserService();
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser) session.getAttribute(FieldNameConstant.USER);

            User user = userService.getUser(activeUser.getId());

            ProfileForm profile = new ProfileForm(user.getLogin(),
                    user.getFirstName(), user.getLastName(), user.getEmail());

            request.setAttribute(FieldNameConstant.USER_PROFILE, profile);

            forward(request, response, PageConstant.PROFILE);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}

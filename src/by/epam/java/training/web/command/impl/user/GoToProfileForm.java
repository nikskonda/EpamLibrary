package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.*;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class GoToProfileForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToProfileForm.class);




    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);

            UserService userService = ServiceFactory.getUserService();
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser) session.getAttribute(USER);

            User user = userService.getUser(activeUser.getId());

            ProfileForm profile = new ProfileForm(user.getLogin(),
                    user.getFirstName(), user.getLastName(), user.getEmail());

            request.setAttribute(USER_PROFILE, profile);

            forward(request, response, PROFILE);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}

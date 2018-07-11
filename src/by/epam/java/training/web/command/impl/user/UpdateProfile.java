package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.form.ProfileForm;
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
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.ERROR;
import static by.epam.java.training.web.command.Page.*;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_DATABASE;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_PATH;
import static by.epam.java.training.web.command.util.FieldNames.ERROR_UNKNOWN;

public class UpdateProfile extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(UpdateProfile.class);

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";

    private static final String USER_PROFILE = "profile";
    private static final String ERROR_MATCH = "error_match";
    private static final String ERROR_CONFIRM = "error_confirm";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            ProfileForm profile = new ProfileForm();

            profile.setLogin(request.getParameter(LOGIN));
            profile.setPassword(EncriptionMD5.encrypt(request.getParameter(OLD_PASSWORD)));

            if (!request.getParameter(NEW_PASSWORD).isEmpty()){
                profile.setNewPassword(EncriptionMD5.encrypt(request.getParameter(NEW_PASSWORD)));
            }
            if (!request.getParameter(CONFIRM_PASSWORD).isEmpty()){
                profile.setConfirmPassword(EncriptionMD5.encrypt(request.getParameter(CONFIRM_PASSWORD)));
            }

            profile.setEmail(request.getParameter(EMAIL));
            profile.setFirstName(request.getParameter(FIRST_NAME));
            profile.setLastName(request.getParameter(LAST_NAME));

            UserService userService = ServiceFactory.getUserService();

            if (!userService.isExistLoginAndPassword(profile)){
                request.setAttribute(ERROR_MATCH, true);
                request.setAttribute(USER_PROFILE, profile);
                forward(request, response, PROFILE);
                return;
            }
            request.setAttribute(ERROR_MATCH, false);

            if (!(profile.getPassword()==null
                    || profile.getConfirmPassword()==null)) {
                if (!profile.getNewPassword().equals(profile.getConfirmPassword())){
                    request.setAttribute(ERROR_CONFIRM, true);
                    forward(request, response, ERROR);
                    return;
                } else {
                    request.setAttribute(ERROR_CONFIRM, false);
                    profile.setPassword(profile.getNewPassword());
                }
            }

            if (!userService.updateUser(profile)){
                forward(request, response, ERROR);
                return;
            }

            CommandFactory.getCommand(CommandName.OPEN_PROFILE).execute(request, response);
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

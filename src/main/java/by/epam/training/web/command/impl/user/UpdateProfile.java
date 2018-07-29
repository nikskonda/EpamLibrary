package by.epam.training.web.command.impl.user;

import by.epam.training.model.user.form.ProfileForm;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.command.CommandConstants;
import by.epam.training.web.util.EncriptionMD5;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.training.web.command.CommandConstants.ERROR;

/**
 * Used to edit a profile.
 *
 * @author  Nikita Shkonda
 */
public class UpdateProfile extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(UpdateProfile.class);


    /**
     * Editing a profile.
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
            ProfileForm profile = new ProfileForm();
            profile.setLogin(request.getParameter(FieldNameConstant.LOGIN));
            profile.setPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.OLD_PASSWORD)));
            if (!request.getParameter(FieldNameConstant.NEW_PASSWORD).isEmpty()){
                profile.setNewPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.NEW_PASSWORD)));
            }
            if (!request.getParameter(FieldNameConstant.CONFIRM_PASSWORD).isEmpty()){
                profile.setConfirmPassword(EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.CONFIRM_PASSWORD)));
            }
            profile.setEmail(request.getParameter(FieldNameConstant.EMAIL));
            profile.setFirstName(request.getParameter(FieldNameConstant.FIRST_NAME));
            profile.setLastName(request.getParameter(FieldNameConstant.LAST_NAME));

            if (!userService.isUserExist(profile)){
                request.setAttribute(FieldNameConstant.ERROR_MATCH, true);
                request.setAttribute(FieldNameConstant.USER_PROFILE, profile);
                forward(request, response, PageConstant.PROFILE);
                return;
            }

            request.setAttribute(FieldNameConstant.ERROR_MATCH, false);

            if (!(profile.getPassword()==null
                    || profile.getConfirmPassword()==null)) {
                if (!profile.getNewPassword().equals(profile.getConfirmPassword())){
                    request.setAttribute(FieldNameConstant.ERROR_CONFIRM, true);
                    forward(request, response, ERROR);
                    return;
                } else {
                    request.setAttribute(FieldNameConstant.ERROR_CONFIRM, false);
                    profile.setPassword(profile.getNewPassword());
                }
            }

            if (!userService.updateUser(profile)){
                forward(request, response, ERROR);
                return;
            }

            CommandFactory.getCommand(CommandConstants.GO_TO_PROFILE_FORM).execute(request, response);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}

package by.epam.training.web.command.impl.admin;

import by.epam.training.model.user.User;
import by.epam.training.servise.AdministratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;

import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to get a information about user.
 *
 * @author  Nikita Shkonda
 */
public class TakeUser extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeUser.class);

    /**
     * Getting information about user.
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
            Integer userId = Integer.parseInt(request.getParameter(FieldNameConstant.USER_ID));
            AdministratorService userService = ServiceFactory.getAdministratorService();
            User user = userService.getUser(userId);

            request.setAttribute(FieldNameConstant.USER_PROFILE, user);
            request.setAttribute(FieldNameConstant.ROLE_LIST, userService.getRoles());

            forward(request, response, PageConstant.ADMINISTRATION_USER);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }
}

package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import static by.epam.java.training.web.command.util.PageConstant.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class TakeUser extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            Integer userId = Integer.parseInt(request.getParameter(USER_ID));
            AdministratorService userService = ServiceFactory.getAdministratorService();
            User user = userService.getUser(userId);

            request.setAttribute(USER_PROFILE, user);
            request.setAttribute(ROLE_LIST, userService.getRoles());

            forward(request, response, ADMINISTRATION_USER);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }
}

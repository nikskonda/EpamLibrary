package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import static by.epam.java.training.web.command.CommandName.*;
import static by.epam.java.training.web.command.util.FieldNames.*;

import by.epam.java.training.web.command.util.FieldNames;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeUserRole extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ChangeUserRole.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(USER);
            String password = EncriptionMD5.encrypt(request.getParameter(PASSWORD));
            Integer userId = Integer.parseInt(request.getParameter(USER_ID));
            String roleName = request.getParameter(ROLE);
            UserService userService = ServiceFactory.getUserService();
            AdministratorService administratorService = ServiceFactory.getAdministratorService();

            if (userService.isUserExist(new SignInForm(user.getLogin(), password))){
                if (administratorService.changeRole(userId, roleName)){
                    CommandFactory.getCommand(TAKE_USER).execute(request, response);
                    return;
                }
                request.setAttribute(FieldNames.ERROR, true);
                CommandFactory.getCommand(TAKE_USER).execute(request, response);
                return;
            }else{
                request.setAttribute(ERROR_EXIST, true);
                CommandFactory.getCommand(TAKE_USER).execute(request, response);
                return;
            }
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
